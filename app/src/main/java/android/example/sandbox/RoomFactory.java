package android.example.sandbox;

import android.content.Context;

import androidx.room.Room;

public class RoomFactory {
    private static NotesDatabase notesDatabase;

    public static NotesDatabase getNotesDatabase(Context context) {
        if (notesDatabase == null){
            notesDatabase = Room.databaseBuilder(context , NotesDatabase.class , "notes_db").build();
        }
        return notesDatabase;
    }
}
