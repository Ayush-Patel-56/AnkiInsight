package com.ankiinsight.app.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.ankiinsight.app.data.local.dao.CachedCardDao;
import com.ankiinsight.app.data.local.dao.CachedCardDao_Impl;
import com.ankiinsight.app.data.local.dao.ConflictResultDao;
import com.ankiinsight.app.data.local.dao.ConflictResultDao_Impl;
import com.ankiinsight.app.data.local.dao.GapResultDao;
import com.ankiinsight.app.data.local.dao.GapResultDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile CachedCardDao _cachedCardDao;

  private volatile ConflictResultDao _conflictResultDao;

  private volatile GapResultDao _gapResultDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(2) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `cached_cards` (`id` INTEGER NOT NULL, `deckId` INTEGER NOT NULL, `deckName` TEXT NOT NULL, `front` TEXT NOT NULL, `back` TEXT NOT NULL, `easeFactor` INTEGER NOT NULL, `due` INTEGER NOT NULL, `modelId` INTEGER NOT NULL, PRIMARY KEY(`id`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `conflict_results` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `cardAId` INTEGER NOT NULL, `cardBId` INTEGER NOT NULL, `type` TEXT NOT NULL, `explanation` TEXT NOT NULL, `timestamp` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `gap_results` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `concept` TEXT NOT NULL, `explanation` TEXT NOT NULL, `neededFor` TEXT NOT NULL, `timestamp` INTEGER NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '482922c714825236b3eed0d4f6179e0d')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `cached_cards`");
        db.execSQL("DROP TABLE IF EXISTS `conflict_results`");
        db.execSQL("DROP TABLE IF EXISTS `gap_results`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsCachedCards = new HashMap<String, TableInfo.Column>(8);
        _columnsCachedCards.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCachedCards.put("deckId", new TableInfo.Column("deckId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCachedCards.put("deckName", new TableInfo.Column("deckName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCachedCards.put("front", new TableInfo.Column("front", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCachedCards.put("back", new TableInfo.Column("back", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCachedCards.put("easeFactor", new TableInfo.Column("easeFactor", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCachedCards.put("due", new TableInfo.Column("due", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsCachedCards.put("modelId", new TableInfo.Column("modelId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysCachedCards = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesCachedCards = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoCachedCards = new TableInfo("cached_cards", _columnsCachedCards, _foreignKeysCachedCards, _indicesCachedCards);
        final TableInfo _existingCachedCards = TableInfo.read(db, "cached_cards");
        if (!_infoCachedCards.equals(_existingCachedCards)) {
          return new RoomOpenHelper.ValidationResult(false, "cached_cards(com.ankiinsight.app.data.local.entity.CachedCardEntity).\n"
                  + " Expected:\n" + _infoCachedCards + "\n"
                  + " Found:\n" + _existingCachedCards);
        }
        final HashMap<String, TableInfo.Column> _columnsConflictResults = new HashMap<String, TableInfo.Column>(6);
        _columnsConflictResults.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConflictResults.put("cardAId", new TableInfo.Column("cardAId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConflictResults.put("cardBId", new TableInfo.Column("cardBId", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConflictResults.put("type", new TableInfo.Column("type", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConflictResults.put("explanation", new TableInfo.Column("explanation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsConflictResults.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysConflictResults = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesConflictResults = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoConflictResults = new TableInfo("conflict_results", _columnsConflictResults, _foreignKeysConflictResults, _indicesConflictResults);
        final TableInfo _existingConflictResults = TableInfo.read(db, "conflict_results");
        if (!_infoConflictResults.equals(_existingConflictResults)) {
          return new RoomOpenHelper.ValidationResult(false, "conflict_results(com.ankiinsight.app.data.local.entity.ConflictResultEntity).\n"
                  + " Expected:\n" + _infoConflictResults + "\n"
                  + " Found:\n" + _existingConflictResults);
        }
        final HashMap<String, TableInfo.Column> _columnsGapResults = new HashMap<String, TableInfo.Column>(5);
        _columnsGapResults.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGapResults.put("concept", new TableInfo.Column("concept", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGapResults.put("explanation", new TableInfo.Column("explanation", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGapResults.put("neededFor", new TableInfo.Column("neededFor", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsGapResults.put("timestamp", new TableInfo.Column("timestamp", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysGapResults = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesGapResults = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoGapResults = new TableInfo("gap_results", _columnsGapResults, _foreignKeysGapResults, _indicesGapResults);
        final TableInfo _existingGapResults = TableInfo.read(db, "gap_results");
        if (!_infoGapResults.equals(_existingGapResults)) {
          return new RoomOpenHelper.ValidationResult(false, "gap_results(com.ankiinsight.app.data.local.entity.GapResultEntity).\n"
                  + " Expected:\n" + _infoGapResults + "\n"
                  + " Found:\n" + _existingGapResults);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "482922c714825236b3eed0d4f6179e0d", "376919bdf8a12737386595902603a039");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "cached_cards","conflict_results","gap_results");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `cached_cards`");
      _db.execSQL("DELETE FROM `conflict_results`");
      _db.execSQL("DELETE FROM `gap_results`");
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
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(CachedCardDao.class, CachedCardDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ConflictResultDao.class, ConflictResultDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(GapResultDao.class, GapResultDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public CachedCardDao cachedCardDao() {
    if (_cachedCardDao != null) {
      return _cachedCardDao;
    } else {
      synchronized(this) {
        if(_cachedCardDao == null) {
          _cachedCardDao = new CachedCardDao_Impl(this);
        }
        return _cachedCardDao;
      }
    }
  }

  @Override
  public ConflictResultDao conflictResultDao() {
    if (_conflictResultDao != null) {
      return _conflictResultDao;
    } else {
      synchronized(this) {
        if(_conflictResultDao == null) {
          _conflictResultDao = new ConflictResultDao_Impl(this);
        }
        return _conflictResultDao;
      }
    }
  }

  @Override
  public GapResultDao gapResultDao() {
    if (_gapResultDao != null) {
      return _gapResultDao;
    } else {
      synchronized(this) {
        if(_gapResultDao == null) {
          _gapResultDao = new GapResultDao_Impl(this);
        }
        return _gapResultDao;
      }
    }
  }
}
