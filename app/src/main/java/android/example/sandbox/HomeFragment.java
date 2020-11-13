package android.example.sandbox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

public class HomeFragment extends Fragment {

    RecyclerView notesRv;
    NoteRvAdapter noteRvAdapter;
    List<NotesEntity> noteList = new ArrayList<>();
    FloatingActionButton fab;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        notesRv = view.findViewById(R.id.notes_RV);
        fab = view.findViewById(R.id.add_fab);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        getAllNotes();
        noteSetup();
        setOnClickListeners();


    }

    private void getAllNotes() {

        noteList.clear();

        try {
            noteList.addAll(new GetAsyncTask(RoomFactory.getNotesDatabase(requireContext()).getNoteDAO()).execute().get());
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void setOnClickListeners() {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_addFragment);
            }
        });

    }

    private void noteSetup() {

        noteRvAdapter = new NoteRvAdapter(noteList, new NoteRvAdapter.OnItemClick() {
            @Override
            public void onNoteClick(View view, final int position) {

                setUpEditOrDeleteDialog(view, position);
            }
        });

        notesRv.setLayoutManager(new LinearLayoutManager(requireContext()));
        notesRv.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        notesRv.addItemDecoration(new DividerItemDecoration(requireContext(), LinearLayoutManager.VERTICAL));
        notesRv.setAdapter(noteRvAdapter);

    }




    private void setUpEditOrDeleteDialog(final View view, final int position) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(requireContext());
        dialog.setMessage("Edit or delete this note ?");

        dialog.setNegativeButton("Delete", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                deleteNote(position);

            }
        });

        dialog.setPositiveButton("Edit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

                NotesEntity notesEntity = noteList.get(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable("note", notesEntity);

                Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_editFragment, bundle);

            }
        });

        dialog.show();
    }


        private void deleteNote ( int position){
            NotesEntity notesEntity = noteList.get(position);
            new DeleteAsyncTask(RoomFactory.getNotesDatabase(requireContext()).getNoteDAO()).execute(notesEntity);
            noteRvAdapter.notifyDataSetChanged();
            getAllNotes();

        }

}