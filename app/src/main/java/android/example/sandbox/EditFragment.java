package android.example.sandbox;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class EditFragment extends Fragment {

    private EditText titleEt;
    private EditText detailsEt;
    private Button editBtn;
    NotesEntity notesEntity;




    @Override
    public void onCreate( Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_edit , container , false);
        titleEt = view.findViewById(R.id.edit_title_et);
        detailsEt = view.findViewById(R.id.edit_details_et);
        editBtn = view.findViewById(R.id.update_btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getNoteFromHomeFragment();
        setUpClickListeners();
    }

    private void setUpClickListeners() {
        editBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = titleEt.getText().toString();
                String details = detailsEt.getText().toString();

                if (title.isEmpty() || details.isEmpty()) {
                    Toast.makeText(requireContext(), "There is an empty field", Toast.LENGTH_SHORT).show();
                } else {

                    notesEntity.setTitle(title);
                    notesEntity.setDetails(details);

                    new UpdateAsyncTask(RoomFactory.getNotesDatabase(requireContext()).getNoteDAO()).execute(notesEntity);

                    Navigation.findNavController(view).navigate(R.id.action_editFragment_to_homeFragment);

                }
            }
        });

    }

    public void getNoteFromHomeFragment(){

        Bundle bundle = getArguments();

        if (bundle != null){

            notesEntity = (NotesEntity) bundle.getSerializable("note");

            titleEt.setText(notesEntity.getTitle());
            detailsEt.setText(notesEntity.getDetails());
        }
    }
}