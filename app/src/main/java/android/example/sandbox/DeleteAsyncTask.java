package android.example.sandbox;

import android.os.AsyncTask;

public class DeleteAsyncTask extends AsyncTask<NotesEntity , Void , Void> {

  private NoteDAO noteDAO;

    public DeleteAsyncTask(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Override
    protected Void doInBackground(NotesEntity... notesEntities) {
        noteDAO.deleteNote(notesEntities[0]);
        return null;
    }
}

