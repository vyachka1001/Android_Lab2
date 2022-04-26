package com.rpodmp.viarbitski.rpodmp2.ui.artists;

import androidx.lifecycle.ViewModel;

import com.rpodmp.viarbitski.rpodmp2.db.dao.ArtistDao;
import com.rpodmp.viarbitski.rpodmp2.db.entity.Artist;
import com.rpodmp.viarbitski.rpodmp2.helpers.ApiHelper;

import java.net.ConnectException;
import java.util.ArrayList;
import java.util.List;

public class ArtistsViewModel extends ViewModel {

    private final List<Artist> artists = new ArrayList<>();

    public ArtistsViewModel() {
    }

    public List<Artist> getArtists(ArtistDao trackDao)
    {
        setArtists(trackDao.getAll());
        return artists;
    }

    public List<Artist> fetchArtists(ArtistDao trackDao) throws ConnectException {
        setArtists(ApiHelper.getTopArtists());
        if (artists.isEmpty()) {
            throw new ConnectException("No connection!");
        }
        trackDao.deleteAll();
        trackDao.insertAll(artists);
        return artists;
    }

    public List<Artist> deleteArtists(ArtistDao trackDao)
    {
        trackDao.deleteAll();
        artists.clear();
        return artists;
    }

    private void setArtists(List<Artist> newArtists) {
        artists.clear();
        artists.addAll(newArtists);
    }
}