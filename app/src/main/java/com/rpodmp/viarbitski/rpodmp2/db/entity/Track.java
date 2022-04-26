package com.rpodmp.viarbitski.rpodmp2.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Track {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "artist")
    public String artist;

    @ColumnInfo(name = "duration")
    public int duration;

    @ColumnInfo(name = "play_count")
    public int playCount;

    @ColumnInfo(name = "listeners")
    public int listeners;

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    public boolean isFavourite;

    public Track(String name, String artist, int duration, int playCount, int listeners, String imageUrl) {
        this.name = name;
        this.artist = artist;
        this.duration = duration;
        this.playCount = playCount;
        this.listeners = listeners;
        this.imageUrl = imageUrl;
        isFavourite = false;
    }

}
