package com.rpodmp.viarbitski.rpodmp2.ui.tracks;

import androidx.lifecycle.ViewModel;

import com.rpodmp.viarbitski.rpodmp2.db.dao.TrackDao;
import com.rpodmp.viarbitski.rpodmp2.db.entity.Track;
import com.rpodmp.viarbitski.rpodmp2.helpers.ApiHelper;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

public class TracksChartViewModel extends ViewModel {

    private final List<Track> tracks = new ArrayList<>();

    public TracksChartViewModel() {
    }

    public List<Track> getTracks(TrackDao trackDao)
    {
        setTracks(trackDao.getAll());
        return tracks;
    }

    public List<Track> fetchTracks(TrackDao trackDao) throws ConnectException {
        List<Track> fetched = ApiHelper.getTopTracks();
        if (fetched.isEmpty()) {
            throw new ConnectException("No connection!");
        }
        setTracks(fetched);
        trackDao.deleteAll();
        setIds(trackDao.insertAll(tracks));
        return tracks;
    }

    public List<Track> deleteTracks(TrackDao trackDao)
    {
        trackDao.deleteAll();
        tracks.clear();
        return tracks;
    }

    private void setTracks(List<Track> newTracks) {
        tracks.clear();
        tracks.addAll(newTracks);
    }

    private void setIds(List<Long> ids) {
        for (int i = 0; i < ids.size(); i++) {
            tracks.get(i).id = ids.get(i).intValue();
        }
    }
}