package com.example.simplenote;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class NoteAdapter extends ListAdapter<Note, NoteAdapter.ViewHolder> {
    public onItemClickListener listener;

    public NoteAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Note> DIFF_CALLBACK = new DiffUtil.ItemCallback<Note>() {
        @Override
        public boolean areItemsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Note oldItem, @NonNull Note newItem) {
            return oldItem.getTitle().equals(newItem.getTitle()) &&
                    oldItem.getDescription().equals(newItem.getDescription()) &&
                    oldItem.getPriority() == newItem.getPriority();
        }
    };

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_card_veiw, parent, false);

        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Note currentNote = getItem(position);
        holder.textViewTitle.setText(currentNote.getTitle());
        holder.textViewDescription.setText(currentNote.getDescription());
        holder.textViewPriority.setText(String.valueOf(currentNote.getPriority()));

    }


    public Note getNoteAt(int position) {

        return getItem(position);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewPriority;
        private TextView textViewDescription;
        private TextView textViewTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPriority = itemView.findViewById(R.id.text_view_priority);
            textViewDescription = itemView.findViewById(R.id.description);
            textViewTitle = itemView.findViewById(R.id.text_view_title);


            //1 = we want to edit each item that why itemView
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    //2 = we store position on int position
                    int position = getAdapterPosition();

                    //listener.onItemClick(there is need item position so for this =2);
                    //when we delete an item then we need to check the listener is null or not and position because
                    //position can be -1 so NO_POSITION = -1.
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        //notes is the arrayList
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }


    }

    //to edit note... = 1

    public interface onItemClickListener {
        void onItemClick(Note note);
    }

    public void setOnItemClickListener(onItemClickListener listener) {
        this.listener = listener;

        //now we want to catch the click in it;
    }

}
