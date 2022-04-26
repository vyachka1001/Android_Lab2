package com.rpodmp.viarbitski.rpodmp2.db;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomOpenHelper;
import androidx.room.RoomOpenHelper.Delegate;
import androidx.room.RoomOpenHelper.ValidationResult;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.room.util.TableInfo.Column;
import androidx.room.util.TableInfo.ForeignKey;
import androidx.room.util.TableInfo.Index;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Callback;
import androidx.sqlite.db.SupportSQLiteOpenHelper.Configuration;
import com.rpodmp.viarbitski.rpodmp2.db.dao.ArtistDao;
import com.rpodmp.viarbitski.rpodmp2.db.dao.ArtistDao_Impl;
import com.rpodmp.viarbitski.rpodmp2.db.dao.TrackDao;
import com.rpodmp.viarbitski.rpodmp2.db.dao.TrackDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile TrackDao _trackDao;

  private volatile ArtistDao _artistDao;

  @Override
  protected SupportSQLiteOpenHelper createOpenHelper(DatabaseConfiguration configuration) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(configuration, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Track` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `artist` TEXT, `duration` INTEGER NOT NULL, `play_count` INTEGER NOT NULL, `listeners` INTEGER NOT NULL, `image_url` TEXT, `isFavourite` INTEGER NOT NULL)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS `Artist` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `name` TEXT, `play_count` INTEGER NOT NULL, `image_url` TEXT)");
        _db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        _db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, 'a7e4a603efba1a97d3164eebf781e527')");
      }

      @Override
      public void dropAllTables(SupportSQLiteDatabase _db) {
        _db.execSQL("DROP TABLE IF EXISTS `Track`");
        _db.execSQL("DROP TABLE IF EXISTS `Artist`");
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onDestructiveMigration(_db);
          }
        }
      }

      @Override
      protected void onCreate(SupportSQLiteDatabase _db) {
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onCreate(_db);
          }
        }
      }

      @Override
      public void onOpen(SupportSQLiteDatabase _db) {
        mDatabase = _db;
        internalInitInvalidationTracker(_db);
        if (mCallbacks != null) {
          for (int _i = 0, _size = mCallbacks.size(); _i < _size; _i++) {
            mCallbacks.get(_i).onOpen(_db);
          }
        }
      }

      @Override
      public void onPreMigrate(SupportSQLiteDatabase _db) {
        DBUtil.dropFtsSyncTriggers(_db);
      }

      @Override
      public void onPostMigrate(SupportSQLiteDatabase _db) {
      }

      @Override
      protected RoomOpenHelper.ValidationResult onValidateSchema(SupportSQLiteDatabase _db) {
        final HashMap<String, TableInfo.Column> _columnsTrack = new HashMap<String, TableInfo.Column>(8);
        _columnsTrack.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrack.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrack.put("artist", new TableInfo.Column("artist", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrack.put("duration", new TableInfo.Column("duration", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrack.put("play_count", new TableInfo.Column("play_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrack.put("listeners", new TableInfo.Column("listeners", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrack.put("image_url", new TableInfo.Column("image_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsTrack.put("isFavourite", new TableInfo.Column("isFavourite", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysTrack = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesTrack = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoTrack = new TableInfo("Track", _columnsTrack, _foreignKeysTrack, _indicesTrack);
        final TableInfo _existingTrack = TableInfo.read(_db, "Track");
        if (! _infoTrack.equals(_existingTrack)) {
          return new RoomOpenHelper.ValidationResult(false, "Track(com.rpodmp.viarbitski.rpodmp2.db.entity.Track).\n"
                  + " Expected:\n" + _infoTrack + "\n"
                  + " Found:\n" + _existingTrack);
        }
        final HashMap<String, TableInfo.Column> _columnsArtist = new HashMap<String, TableInfo.Column>(4);
        _columnsArtist.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArtist.put("name", new TableInfo.Column("name", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArtist.put("play_count", new TableInfo.Column("play_count", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsArtist.put("image_url", new TableInfo.Column("image_url", "TEXT", false, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysArtist = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesArtist = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoArtist = new TableInfo("Artist", _columnsArtist, _foreignKeysArtist, _indicesArtist);
        final TableInfo _existingArtist = TableInfo.read(_db, "Artist");
        if (! _infoArtist.equals(_existingArtist)) {
          return new RoomOpenHelper.ValidationResult(false, "Artist(com.rpodmp.viarbitski.rpodmp2.db.entity.Artist).\n"
                  + " Expected:\n" + _infoArtist + "\n"
                  + " Found:\n" + _existingArtist);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "a7e4a603efba1a97d3164eebf781e527", "6788bf580daa5d07a58b9160016053f0");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(configuration.context)
        .name(configuration.name)
        .callback(_openCallback)
        .build();
    final SupportSQLiteOpenHelper _helper = configuration.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "Track","Artist");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `Track`");
      _db.execSQL("DELETE FROM `Artist`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(TrackDao.class, TrackDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ArtistDao.class, ArtistDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  public List<Migration> getAutoMigrations(
      @NonNull Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecsMap) {
    return Arrays.asList();
  }

  @Override
  public TrackDao trackDao() {
    if (_trackDao != null) {
      return _trackDao;
    } else {
      synchronized(this) {
        if(_trackDao == null) {
          _trackDao = new TrackDao_Impl(this);
        }
        return _trackDao;
      }
    }
  }

  @Override
  public ArtistDao artistDao() {
    if (_artistDao != null) {
      return _artistDao;
    } else {
      synchronized(this) {
        if(_artistDao == null) {
          _artistDao = new ArtistDao_Impl(this);
        }
        return _artistDao;
      }
    }
  }
}
