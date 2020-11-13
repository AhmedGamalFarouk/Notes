package android.example.sandbox;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

public class AddFragment extends Fragment {
    EditText etTitle;
    EditText etDetails;
    Button saveBtn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_add, container, false);
        etTitle = view.findViewById(R.id.title_et);
        etDetails = view.findViewById(R.id.details_et);
        saveBtn = view.findViewById(R.id.save_btn);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String title = etTitle.getText().toString();
                String details = etDetails.getText().toString();
                if (title.isEmpty() || details.isEmpty()) {
                    Toast.makeText(requireContext(), "There is an empty field", Toast.LENGTH_SHORT).show();
                } else {
                    new AddAsyncTask(RoomFactory.getNotesDatabase(requireContext()).getNoteDAO())
                            .execute(new NotesEntity(title, details));
                    Navigation.findNavController(view).navigate(R.id.action_addFragment_to_homeFragment);
                }

            }
        });
    }
}
