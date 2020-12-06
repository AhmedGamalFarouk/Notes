package android.example.sandbox;

import android.database.Cursor;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class NoteDAO_Impl implements NoteDAO {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<NotesEntity> __insertionAdapterOfNotesEntity;

  private final EntityDeletionOrUpdateAdapter<NotesEntity> __deletionAdapterOfNotesEntity;

  private final EntityDeletionOrUpdateAdapter<NotesEntity> __updateAdapterOfNotesEntity;

  public NoteDAO_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNotesEntity = new EntityInsertionAdapter<NotesEntity>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `notes` (`id`,`title`,`details`) VALUES (nullif(?, 0),?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NotesEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDetails() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDetails());
        }
      }
    };
    this.__deletionAdapterOfNotesEntity = new EntityDeletionOrUpdateAdapter<NotesEntity>(__db) {
      @Override
      public String createQuery() {
        return "DELETE FROM `notes` WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NotesEntity value) {
        stmt.bindLong(1, value.getId());
      }
    };
    this.__updateAdapterOfNotesEntity = new EntityDeletionOrUpdateAdapter<NotesEntity>(__db) {
      @Override
      public String createQuery() {
        return "UPDATE OR ABORT `notes` SET `id` = ?,`title` = ?,`details` = ? WHERE `id` = ?";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, NotesEntity value) {
        stmt.bindLong(1, value.getId());
        if (value.getTitle() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getTitle());
        }
        if (value.getDetails() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getDetails());
        }
        stmt.bindLong(4, value.getId());
      }
    };
  }

  @Override
  public void insertNote(final NotesEntity notesEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfNotesEntity.insert(notesEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void deleteNote(final NotesEntity notesEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __deletionAdapterOfNotesEntity.handle(notesEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateNote(final NotesEntity notesEntity) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __updateAdapterOfNotesEntity.handle(notesEntity);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public List<NotesEntity> getAllNotes() {
    final String _sql = "SELECT * FROM notes";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
      final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
      final int _cursorIndexOfDetails = CursorUtil.getColumnIndexOrThrow(_cursor, "details");
      final List<NotesEntity> _result = new ArrayList<NotesEntity>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final NotesEntity _item;
        final String _tmpTitle;
        _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
        final String _tmpDetails;
        _tmpDetails = _cursor.getString(_cursorIndexOfDetails);
        _item = new NotesEntity(_tmpTitle,_tmpDetails);
        final long _tmpId;
        _tmpId = _cursor.getLong(_cursorIndexOfId);
        _item.setId(_tmpId);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
