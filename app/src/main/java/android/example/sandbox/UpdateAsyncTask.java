package android.example.sandbox;

import android.os.AsyncTask;

public class UpdateAsyncTask extends AsyncTask<NotesEntity , Void , Void> {
    private NoteDAO noteDAO;

    public UpdateAsyncTask(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Override
    protected Void doInBackground(NotesEntity... notesEntities) {
        noteDAO.updateNote(notesEntities[0]);
        return null;
    }
}
