package com.rpodmp.viarbitski.rpodmp2.db.dao;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.rpodmp.viarbitski.rpodmp2.db.entity.Track;
import java.lang.Class;
import java.lang.Long;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class TrackDao_Impl implements TrackDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Track> __insertionAdapterOfTrack;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  private final SharedSQLiteStatement __preparedStmtOfDeleteFavourites;

  private final SharedSQLiteStatement __preparedStmtOfSetFavourite;

  public TrackDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfTrack = new EntityInsertionAdapter<Track>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `Track` (`id`,`name`,`artist`,`duration`,`play_count`,`listeners`,`image_url`,`isFavourite`) VALUES (nullif(?, 0),?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, Track value) {
        stmt.bindLong(1, value.id);
        if (value.name == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.name);
        }
        if (value.artist == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.artist);
        }
        stmt.bindLong(4, value.duration);
        stmt.bindLong(5, value.playCount);
        stmt.bindLong(6, value.listeners);
        if (value.imageUrl == null) {
          stmt.bindNull(7);
        } else {
          stmt.bindString(7, value.imageUrl);
        }
        final int _tmp = value.isFavourite ? 1 : 0;
        stmt.bindLong(8, _tmp);
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Track";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteFavourites = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "DELETE FROM Track where isFavourite = 1";
        return _query;
      }
    };
    this.__preparedStmtOfSetFavourite = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE Track SET isFavourite = (?) WHERE id = (?)";
        return _query;
      }
    };
  }

  @Override
  public List<Long> insertAll(final List<Track> tracks) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      List<Long> _result = __insertionAdapterOfTrack.insertAndReturnIdsList(tracks);
      __db.setTransactionSuccessful();
      return _result;
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteAll() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteAll.release(_stmt);
    }
  }

  @Override
  public void deleteFavourites() {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteFavourites.acquire();
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfDeleteFavourites.release(_stmt);
    }
  }

  @Override
  public void setFavourite(final int trackId, final boolean isFavourite) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfSetFavourite.acquire();
    int _argIndex = 1;
    final int _tmp = isFavourite ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, trackId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfSetFavourite.release(_stmt);
    }
  }

  @Override
  public List<Track> getAll() {
    final String _sql = "SELECT * FROM Track";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
      final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
      final int _cursorIndexOfPlayCount = CursorUtil.getColumnIndexOrThrow(_cursor, "play_count");
      final int _cursorIndexOfListeners = CursorUtil.getColumnIndexOrThrow(_cursor, "listeners");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
      final int _cursorIndexOfIsFavourite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavourite");
      final List<Track> _result = new ArrayList<Track>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Track _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpArtist;
        if (_cursor.isNull(_cursorIndexOfArtist)) {
          _tmpArtist = null;
        } else {
          _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
        }
        final int _tmpDuration;
        _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
        final int _tmpPlayCount;
        _tmpPlayCount = _cursor.getInt(_cursorIndexOfPlayCount);
        final int _tmpListeners;
        _tmpListeners = _cursor.getInt(_cursorIndexOfListeners);
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        _item = new Track(_tmpName,_tmpArtist,_tmpDuration,_tmpPlayCount,_tmpListeners,_tmpImageUrl);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFavourite);
        _item.isFavourite = _tmp != 0;
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public List<Track> getFavourites() {
    final String _sql = "SELECT * FROM Track WHERE isFavourite = 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfName = CursorUtil.getColumnIndexOrThrow(_cursor, "name");
      final int _cursorIndexOfArtist = CursorUtil.getColumnIndexOrThrow(_cursor, "artist");
      final int _cursorIndexOfDuration = CursorUtil.getColumnIndexOrThrow(_cursor, "duration");
      final int _cursorIndexOfPlayCount = CursorUtil.getColumnIndexOrThrow(_cursor, "play_count");
      final int _cursorIndexOfListeners = CursorUtil.getColumnIndexOrThrow(_cursor, "listeners");
      final int _cursorIndexOfImageUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "image_url");
      final int _cursorIndexOfIsFavourite = CursorUtil.getColumnIndexOrThrow(_cursor, "isFavourite");
      final List<Track> _result = new ArrayList<Track>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final Track _item;
        final String _tmpName;
        if (_cursor.isNull(_cursorIndexOfName)) {
          _tmpName = null;
        } else {
          _tmpName = _cursor.getString(_cursorIndexOfName);
        }
        final String _tmpArtist;
        if (_cursor.isNull(_cursorIndexOfArtist)) {
          _tmpArtist = null;
        } else {
          _tmpArtist = _cursor.getString(_cursorIndexOfArtist);
        }
        final int _tmpDuration;
        _tmpDuration = _cursor.getInt(_cursorIndexOfDuration);
        final int _tmpPlayCount;
        _tmpPlayCount = _cursor.getInt(_cursorIndexOfPlayCount);
        final int _tmpListeners;
        _tmpListeners = _cursor.getInt(_cursorIndexOfListeners);
        final String _tmpImageUrl;
        if (_cursor.isNull(_cursorIndexOfImageUrl)) {
          _tmpImageUrl = null;
        } else {
          _tmpImageUrl = _cursor.getString(_cursorIndexOfImageUrl);
        }
        _item = new Track(_tmpName,_tmpArtist,_tmpDuration,_tmpPlayCount,_tmpListeners,_tmpImageUrl);
        _item.id = _cursor.getInt(_cursorIndexOfId);
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsFavourite);
        _item.isFavourite = _tmp != 0;
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
