package com.rpodmp.viarbitski.rpodmp2.db.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.rpodmp.viarbitski.rpodmp2.db.entity.Artist;

import java.util.List;

@Dao
public interface ArtistDao {
    @Query("SELECT * FROM Artist")
    List<Artist> getAll();

    @Query("SELECT * FROM Artist WHERE id IN (:artistsIds)")
    List<Artist> loadAllByIds(int[] artistsIds);

    @Query("SELECT * FROM Artist WHERE name LIKE :name")
    Artist findByName(String name);

    @Insert
    void insertAll(List<Artist> tracks);

    @Delete
    void delete(Artist track);

    @Query("DELETE FROM Artist")
    void deleteAll();
}
