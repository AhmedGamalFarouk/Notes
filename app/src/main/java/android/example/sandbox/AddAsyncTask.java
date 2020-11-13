package android.example.sandbox;

import android.os.AsyncTask;

public class AddAsyncTask extends AsyncTask<NotesEntity , Void , Void> {

    private NoteDAO noteDAO;

    public  AddAsyncTask(NoteDAO noteDAO){
        this.noteDAO = noteDAO;
    }
    @Override
    protected Void doInBackground(NotesEntity... notesEntities) {
        noteDAO.insertNote(notesEntities[0]);
        return null;
    }
}
