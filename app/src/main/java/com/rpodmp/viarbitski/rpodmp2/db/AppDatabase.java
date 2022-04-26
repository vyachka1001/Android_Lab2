package com.rpodmp.viarbitski.rpodmp2.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.rpodmp.viarbitski.rpodmp2.db.dao.ArtistDao;
import com.rpodmp.viarbitski.rpodmp2.db.dao.TrackDao;
import com.rpodmp.viarbitski.rpodmp2.db.entity.Artist;
import com.rpodmp.viarbitski.rpodmp2.db.entity.Track;

@Database(entities = {Track.class, Artist.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    private static String DB_NAME = "lab2_db";
    private static AppDatabase instance;

    public static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, DB_NAME).allowMainThreadQueries().build();
        }
        return instance;
    }

    public abstract TrackDao trackDao();

    public abstract ArtistDao artistDao();
}
