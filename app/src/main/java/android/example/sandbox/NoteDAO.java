package android.example.sandbox;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDAO {

    @Query("SELECT * FROM notes")
    List<NotesEntity> getAllNotes();

    @Insert
    void insertNote(NotesEntity notesEntity);

    @Delete
    void deleteNote(NotesEntity notesEntity);

    @Update
    void updateNote(NotesEntity notesEntity);


}
