package com.antigravity.spamquarantine.data.db;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.antigravity.spamquarantine.data.model.QuarantineLogEntity;
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
public final class QuarantineDao_Impl implements QuarantineDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<QuarantineLogEntity> __insertionAdapterOfQuarantineLogEntity;

  private final EntityDeletionOrUpdateAdapter<QuarantineLogEntity> __deletionAdapterOfQuarantineLogEntity;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public QuarantineDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfQuarantineLogEntity = new EntityInsertionAdapter<QuarantineLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `quarantine_logs` (`id`,`rawPhoneNumber`,`normalizedPhoneNumber`,`matchedPattern`,`timestamp`,`isReviewed`) VALUES (nullif(?, 0),?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final QuarantineLogEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getRawPhoneNumber() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getRawPhoneNumber());
        }
        if (entity.getNormalizedPhoneNumber() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getNormalizedPhoneNumber());
        }
        if (entity.getMatchedPattern() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getMatchedPattern());
        }
        statement.bindLong(5, entity.getTimestamp());
        final int _tmp = entity.isReviewed() ? 1 : 0;
        statement.bindLong(6, _tmp);
      }
    };
    this.__deletionAdapterOfQuarantineLogEntity = new EntityDeletionOrUpdateAdapter<QuarantineLogEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `quarantine_logs` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final QuarantineLogEntity entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM quarantine_logs";
        return _query;
      }
    };
  }

  @Override
  public Object insertLog(final QuarantineLogEntity log,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfQuarantineLogEntity.insertAndReturnId(log);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteLog(final QuarantineLogEntity log,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfQuarantineLogEntity.handle(log);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAll.acquire();
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
          __preparedStmtOfClearAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getAllQuarantineLogs(
      final Continuation<? super List<QuarantineLogEntity>> $completion) {
    final String _sql = "SELECT * FROM quarantine_logs ORDER BY timestamp DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<QuarantineLogEntity>>() {
      @Override
      @NonNull
      public List<QuarantineLogEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfRawPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "rawPhoneNumber");
          final int _cursorIndexOfNormalizedPhoneNumber = CursorUtil.getColumnIndexOrThrow(_cursor, "normalizedPhoneNumber");
          final int _cursorIndexOfMatchedPattern = CursorUtil.getColumnIndexOrThrow(_cursor, "matchedPattern");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfIsReviewed = CursorUtil.getColumnIndexOrThrow(_cursor, "isReviewed");
          final List<QuarantineLogEntity> _result = new ArrayList<QuarantineLogEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final QuarantineLogEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpRawPhoneNumber;
            if (_cursor.isNull(_cursorIndexOfRawPhoneNumber)) {
              _tmpRawPhoneNumber = null;
            } else {
              _tmpRawPhoneNumber = _cursor.getString(_cursorIndexOfRawPhoneNumber);
            }
            final String _tmpNormalizedPhoneNumber;
            if (_cursor.isNull(_cursorIndexOfNormalizedPhoneNumber)) {
              _tmpNormalizedPhoneNumber = null;
            } else {
              _tmpNormalizedPhoneNumber = _cursor.getString(_cursorIndexOfNormalizedPhoneNumber);
            }
            final String _tmpMatchedPattern;
            if (_cursor.isNull(_cursorIndexOfMatchedPattern)) {
              _tmpMatchedPattern = null;
            } else {
              _tmpMatchedPattern = _cursor.getString(_cursorIndexOfMatchedPattern);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final boolean _tmpIsReviewed;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsReviewed);
            _tmpIsReviewed = _tmp != 0;
            _item = new QuarantineLogEntity(_tmpId,_tmpRawPhoneNumber,_tmpNormalizedPhoneNumber,_tmpMatchedPattern,_tmpTimestamp,_tmpIsReviewed);
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
  public Object getBlockedCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM quarantine_logs";
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
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
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
