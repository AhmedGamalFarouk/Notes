package android.example.sandbox;

import android.os.AsyncTask;

import java.util.List;

public class GetAsyncTask extends AsyncTask <Void , Void , List<NotesEntity>> {

    private NoteDAO noteDAO;

    public GetAsyncTask(NoteDAO noteDAO) {
        this.noteDAO = noteDAO;
    }

    @Override
    protected List<NotesEntity> doInBackground(Void... voids) {
        return noteDAO.getAllNotes();
    }
}
