package com.rpodmp.viarbitski.rpodmp2.db.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.rpodmp.viarbitski.rpodmp2.db.entity.Track;

import java.util.List;

@Dao
public interface TrackDao {
    @Query("SELECT * FROM Track")
    List<Track> getAll();

    @Query("SELECT * FROM Track WHERE isFavourite = 1")
    List<Track> getFavourites();

    @Insert
    List<Long> insertAll(List<Track> tracks);

    @Query("DELETE FROM Track")
    void deleteAll();

    @Query("DELETE FROM Track where isFavourite = 1")
    void deleteFavourites();

    @Query("UPDATE Track SET isFavourite = (:isFavourite) WHERE id = (:trackId)")
    void setFavourite(int trackId, boolean isFavourite);
}
