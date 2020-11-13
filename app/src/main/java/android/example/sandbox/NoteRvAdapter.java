package android.example.sandbox;

import android.app.Person;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NoteRvAdapter extends RecyclerView.Adapter<NoteRvAdapter.NoteViewHolder> {

    List<NotesEntity> noteList;
    OnItemClick onItemClick;

    public interface OnItemClick{
        void onNoteClick(View view , int position);
    }

    public NoteRvAdapter(List<NotesEntity> noteList, OnItemClick onItemClick) {
        this.noteList = noteList;
        this.onItemClick = onItemClick;
    }

    @NonNull
    @Override
    public NoteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item , parent , false);

        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final NoteViewHolder holder, final int position) {

        NotesEntity note = noteList.get(position);
        holder.titleTv.setText(note.getTitle());
        holder.detailsTv.setText(note.getDetails());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClick.onNoteClick(view ,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return noteList.size();
    }

    class NoteViewHolder extends RecyclerView.ViewHolder{

        TextView titleTv;
        TextView detailsTv;

        public NoteViewHolder(@NonNull View itemView) {
            super(itemView);
            titleTv = itemView.findViewById(R.id.title_tv);
            detailsTv = itemView.findViewById(R.id.details_tv);


        }
    }
}
