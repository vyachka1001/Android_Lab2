package com.rpodmp.viarbitski.rpodmp2.helpers;

import android.util.Log;

import com.rpodmp.viarbitski.rpodmp2.db.entity.Artist;
import com.rpodmp.viarbitski.rpodmp2.db.entity.Track;

import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class ApiHelper {
    private static final String API_KEY = "b4e6a27caf7d3d660b56dcfdd8c2e9ff";
    private static final String API_URL = "http://ws.audioscrobbler.com/2.0";

    private static final String GET_TOP_TRACKS = "chart.getTopTracks";
    private static final String GET_TOP_ARTISTS = "chart.getTopArtists";

    private static final Map<String, String> DEFAULT_PARAMS = new HashMap<String, String>() {
        {
            put("api_key", API_KEY);
            put("format", "json");
            put("lang", "en");
        }
    };

    private static final String ITEMS_LIMIT = "50";
    private static int tracks_page = 0;
    private static int artists_page = 0;

    public static List<Track> getTopTracks() {
        tracks_page += 1;
        List<Track> data = new ArrayList<>();
        try {
            for (JSONObject item : fetchData(GET_TOP_TRACKS, new String[]{"tracks", "track"}, tracks_page)) {
                data.add(createTrack(item));
            }
        } catch (JSONException ex) {
            Log.e("JSONEx:", ex.getMessage());
        }
        return data;
    }

    public static List<Artist> getTopArtists() {
        artists_page += 1;
        List<Artist> data = new ArrayList<>();
        try {
            for (JSONObject item : fetchData(GET_TOP_ARTISTS, new String[]{"artists", "artist"}, artists_page)) {
                data.add(createArtist(item));
            }
        } catch (JSONException ex) {
            Log.e("JSONEx:", ex.getMessage());
        }
        return data;
    }

    private static URL buildUrl(Map<String, String> params) {
        URL url = null;
        try {
            URIBuilder uriBuilder = new URIBuilder(API_URL);
            params.forEach(uriBuilder::addParameter);
            url = uriBuilder.build().toURL();
        } catch (URISyntaxException | MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    private static Map<String, String> buildQueryParams(Map<String, String> additionalParams) {
        HashMap<String, String> resultParams = new HashMap<>(DEFAULT_PARAMS);
        resultParams.putAll(additionalParams);
        return resultParams;
    }

    private static Track createTrack(JSONObject trackJson) throws JSONException {
        return new Track(trackJson.getString("name"),
                trackJson.getJSONObject("artist").getString("name"),
                Integer.parseInt(trackJson.getString("duration")),
                Integer.parseInt(trackJson.getString("playcount")),
                Integer.parseInt(trackJson.getString("listeners")),
                trackJson.getJSONArray("image").getJSONObject(0).getString("#text")
        );
    }

    private static Artist createArtist(JSONObject artistJson) throws JSONException {
        return new Artist(artistJson.getString("name"),
                Integer.parseInt(artistJson.getString("playcount")),
                artistJson.getJSONArray("image").getJSONObject(0).getString("#text")
        );
    }

    private static List<JSONObject> fetchData(String method, String[] path, int page) {
        List<JSONObject> data = new ArrayList<>();
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.execute(() -> {
            HttpURLConnection connection = null;
            try {
                Map<String, String> params = buildQueryParams(new HashMap<String, String>() {
                    {
                        put("method", method);
                        put("limit", ITEMS_LIMIT);
                        put("page", "" + page);
                    }
                });

                connection = (HttpURLConnection) buildUrl(params).openConnection();

                StringBuffer responseContent = doQuery(connection);

                JSONObject obj = new JSONObject(responseContent.toString());
                JSONArray array = obj.getJSONObject(path[0]).getJSONArray(path[1]);

                for (int i = 0; i < array.length(); i++) {
                    data.add(array.getJSONObject(i));
                }

            } catch (IOException | JSONException e) {
                e.printStackTrace();
            } finally {
                connection.disconnect();
            }
        });

        try {
            executor.shutdown();
            executor.awaitTermination(3, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return data;
    }

    private static StringBuffer doQuery(HttpURLConnection connection) throws IOException {
        connection.setRequestMethod("POST");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        int status = connection.getResponseCode();
        Log.e("CONNECTION STATUS", String.valueOf(status));

        BufferedReader reader;
        String line;
        StringBuffer responseContent = new StringBuffer();

        if (status > 299) {
            reader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        }
        while ((line = reader.readLine()) != null) {
            responseContent.append(line);
        }
        reader.close();
        Log.e("JSON RESPONSE", responseContent.toString());

        return responseContent;
    }
}
