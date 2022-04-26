package com.rpodmp.viarbitski.rpodmp2.db.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Artist {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "play_count")
    public int playCount;

    @ColumnInfo(name = "image_url")
    public String imageUrl;

    public Artist(String name, int playCount, String imageUrl) {
        this.name = name;
        this.playCount = playCount;
        this.imageUrl = imageUrl;
    }
}
