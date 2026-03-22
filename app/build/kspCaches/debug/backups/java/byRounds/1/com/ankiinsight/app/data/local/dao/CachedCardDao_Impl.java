package com.ankiinsight.app.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.ankiinsight.app.data.local.entity.CachedCardEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CachedCardDao_Impl implements CachedCardDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CachedCardEntity> __insertionAdapterOfCachedCardEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteAll;

  public CachedCardDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCachedCardEntity = new EntityInsertionAdapter<CachedCardEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `cached_cards` (`id`,`deckId`,`deckName`,`front`,`back`,`easeFactor`,`due`,`modelId`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CachedCardEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getDeckId());
        statement.bindString(3, entity.getDeckName());
        statement.bindString(4, entity.getFront());
        statement.bindString(5, entity.getBack());
        statement.bindLong(6, entity.getEaseFactor());
        statement.bindLong(7, entity.getDue());
        statement.bindLong(8, entity.getModelId());
      }
    };
    this.__preparedStmtOfDeleteAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM cached_cards";
        return _query;
      }
    };
  }

  @Override
  public Object insertAll(final List<CachedCardEntity> cards,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCachedCardEntity.insert(cards);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteAll.acquire();
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllCards(final Continuation<? super List<CachedCardEntity>> $completion) {
    final String _sql = "SELECT * FROM cached_cards";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CachedCardEntity>>() {
      @Override
      @NonNull
      public List<CachedCardEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeckId = CursorUtil.getColumnIndexOrThrow(_cursor, "deckId");
          final int _cursorIndexOfDeckName = CursorUtil.getColumnIndexOrThrow(_cursor, "deckName");
          final int _cursorIndexOfFront = CursorUtil.getColumnIndexOrThrow(_cursor, "front");
          final int _cursorIndexOfBack = CursorUtil.getColumnIndexOrThrow(_cursor, "back");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfDue = CursorUtil.getColumnIndexOrThrow(_cursor, "due");
          final int _cursorIndexOfModelId = CursorUtil.getColumnIndexOrThrow(_cursor, "modelId");
          final List<CachedCardEntity> _result = new ArrayList<CachedCardEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CachedCardEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeckId;
            _tmpDeckId = _cursor.getLong(_cursorIndexOfDeckId);
            final String _tmpDeckName;
            _tmpDeckName = _cursor.getString(_cursorIndexOfDeckName);
            final String _tmpFront;
            _tmpFront = _cursor.getString(_cursorIndexOfFront);
            final String _tmpBack;
            _tmpBack = _cursor.getString(_cursorIndexOfBack);
            final int _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getInt(_cursorIndexOfEaseFactor);
            final long _tmpDue;
            _tmpDue = _cursor.getLong(_cursorIndexOfDue);
            final long _tmpModelId;
            _tmpModelId = _cursor.getLong(_cursorIndexOfModelId);
            _item = new CachedCardEntity(_tmpId,_tmpDeckId,_tmpDeckName,_tmpFront,_tmpBack,_tmpEaseFactor,_tmpDue,_tmpModelId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getCardsByDeck(final long deckId,
      final Continuation<? super List<CachedCardEntity>> $completion) {
    final String _sql = "SELECT * FROM cached_cards WHERE deckId = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, deckId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CachedCardEntity>>() {
      @Override
      @NonNull
      public List<CachedCardEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeckId = CursorUtil.getColumnIndexOrThrow(_cursor, "deckId");
          final int _cursorIndexOfDeckName = CursorUtil.getColumnIndexOrThrow(_cursor, "deckName");
          final int _cursorIndexOfFront = CursorUtil.getColumnIndexOrThrow(_cursor, "front");
          final int _cursorIndexOfBack = CursorUtil.getColumnIndexOrThrow(_cursor, "back");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfDue = CursorUtil.getColumnIndexOrThrow(_cursor, "due");
          final int _cursorIndexOfModelId = CursorUtil.getColumnIndexOrThrow(_cursor, "modelId");
          final List<CachedCardEntity> _result = new ArrayList<CachedCardEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CachedCardEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeckId;
            _tmpDeckId = _cursor.getLong(_cursorIndexOfDeckId);
            final String _tmpDeckName;
            _tmpDeckName = _cursor.getString(_cursorIndexOfDeckName);
            final String _tmpFront;
            _tmpFront = _cursor.getString(_cursorIndexOfFront);
            final String _tmpBack;
            _tmpBack = _cursor.getString(_cursorIndexOfBack);
            final int _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getInt(_cursorIndexOfEaseFactor);
            final long _tmpDue;
            _tmpDue = _cursor.getLong(_cursorIndexOfDue);
            final long _tmpModelId;
            _tmpModelId = _cursor.getLong(_cursorIndexOfModelId);
            _item = new CachedCardEntity(_tmpId,_tmpDeckId,_tmpDeckName,_tmpFront,_tmpBack,_tmpEaseFactor,_tmpDue,_tmpModelId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getFailedCards(final Continuation<? super List<CachedCardEntity>> $completion) {
    final String _sql = "SELECT * FROM cached_cards WHERE easeFactor < 2000";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CachedCardEntity>>() {
      @Override
      @NonNull
      public List<CachedCardEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeckId = CursorUtil.getColumnIndexOrThrow(_cursor, "deckId");
          final int _cursorIndexOfDeckName = CursorUtil.getColumnIndexOrThrow(_cursor, "deckName");
          final int _cursorIndexOfFront = CursorUtil.getColumnIndexOrThrow(_cursor, "front");
          final int _cursorIndexOfBack = CursorUtil.getColumnIndexOrThrow(_cursor, "back");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfDue = CursorUtil.getColumnIndexOrThrow(_cursor, "due");
          final int _cursorIndexOfModelId = CursorUtil.getColumnIndexOrThrow(_cursor, "modelId");
          final List<CachedCardEntity> _result = new ArrayList<CachedCardEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CachedCardEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeckId;
            _tmpDeckId = _cursor.getLong(_cursorIndexOfDeckId);
            final String _tmpDeckName;
            _tmpDeckName = _cursor.getString(_cursorIndexOfDeckName);
            final String _tmpFront;
            _tmpFront = _cursor.getString(_cursorIndexOfFront);
            final String _tmpBack;
            _tmpBack = _cursor.getString(_cursorIndexOfBack);
            final int _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getInt(_cursorIndexOfEaseFactor);
            final long _tmpDue;
            _tmpDue = _cursor.getLong(_cursorIndexOfDue);
            final long _tmpModelId;
            _tmpModelId = _cursor.getLong(_cursorIndexOfModelId);
            _item = new CachedCardEntity(_tmpId,_tmpDeckId,_tmpDeckName,_tmpFront,_tmpBack,_tmpEaseFactor,_tmpDue,_tmpModelId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getFailedCardsByDeck(final long deckId,
      final Continuation<? super List<CachedCardEntity>> $completion) {
    final String _sql = "SELECT * FROM cached_cards WHERE deckId = ? AND easeFactor < 2000";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, deckId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CachedCardEntity>>() {
      @Override
      @NonNull
      public List<CachedCardEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfDeckId = CursorUtil.getColumnIndexOrThrow(_cursor, "deckId");
          final int _cursorIndexOfDeckName = CursorUtil.getColumnIndexOrThrow(_cursor, "deckName");
          final int _cursorIndexOfFront = CursorUtil.getColumnIndexOrThrow(_cursor, "front");
          final int _cursorIndexOfBack = CursorUtil.getColumnIndexOrThrow(_cursor, "back");
          final int _cursorIndexOfEaseFactor = CursorUtil.getColumnIndexOrThrow(_cursor, "easeFactor");
          final int _cursorIndexOfDue = CursorUtil.getColumnIndexOrThrow(_cursor, "due");
          final int _cursorIndexOfModelId = CursorUtil.getColumnIndexOrThrow(_cursor, "modelId");
          final List<CachedCardEntity> _result = new ArrayList<CachedCardEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CachedCardEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpDeckId;
            _tmpDeckId = _cursor.getLong(_cursorIndexOfDeckId);
            final String _tmpDeckName;
            _tmpDeckName = _cursor.getString(_cursorIndexOfDeckName);
            final String _tmpFront;
            _tmpFront = _cursor.getString(_cursorIndexOfFront);
            final String _tmpBack;
            _tmpBack = _cursor.getString(_cursorIndexOfBack);
            final int _tmpEaseFactor;
            _tmpEaseFactor = _cursor.getInt(_cursorIndexOfEaseFactor);
            final long _tmpDue;
            _tmpDue = _cursor.getLong(_cursorIndexOfDue);
            final long _tmpModelId;
            _tmpModelId = _cursor.getLong(_cursorIndexOfModelId);
            _item = new CachedCardEntity(_tmpId,_tmpDeckId,_tmpDeckName,_tmpFront,_tmpBack,_tmpEaseFactor,_tmpDue,_tmpModelId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getTotalCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM cached_cards";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getFailedCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM cached_cards WHERE easeFactor < 2000";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getDistinctDeckIds(final Continuation<? super List<Long>> $completion) {
    final String _sql = "SELECT DISTINCT deckId FROM cached_cards";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Long>>() {
      @Override
      @NonNull
      public List<Long> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final List<Long> _result = new ArrayList<Long>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Long _item;
            _item = _cursor.getLong(0);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
