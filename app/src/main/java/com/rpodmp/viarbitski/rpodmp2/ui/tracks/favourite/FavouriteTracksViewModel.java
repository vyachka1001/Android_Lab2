package com.rpodmp.viarbitski.rpodmp2.ui.tracks.favourite;

import androidx.lifecycle.ViewModel;

import com.rpodmp.viarbitski.rpodmp2.db.dao.TrackDao;
import com.rpodmp.viarbitski.rpodmp2.db.entity.Track;

import java.util.ArrayList;
import java.util.List;

public class FavouriteTracksViewModel extends ViewModel {
    private final List<Track> tracks = new ArrayList<>();

    public FavouriteTracksViewModel() {
    }

    public List<Track> getFavouriteTracks(TrackDao trackDao)
    {
        setTracks(trackDao.getFavourites());
        return tracks;
    }

    public List<Track> deleteFavourites(TrackDao trackDao)
    {
        trackDao.deleteFavourites();
        tracks.clear();
        return tracks;
    }

    private void setTracks(List<Track> newTracks) {
        tracks.clear();
        tracks.addAll(newTracks);
    }
}