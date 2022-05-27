package com.example.sproject.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sproject.R;
import com.example.sproject.VerseClickListener;
import com.example.sproject.models.Verse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class VerseListAdapter extends RecyclerView.Adapter<VerseViewHolder> {
    Context context;
    List<Verse> list;
    VerseClickListener listener;

    public VerseListAdapter(Context context, List<Verse> list, VerseClickListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    public void setList(List<Verse> list) {
        if (this.list == null) {
            this.list = list;
        } else {
            DiffUtil.DiffResult result = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return VerseListAdapter.this.list.size();
                }

                @Override
                public int getNewListSize() {
                    return list.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return VerseListAdapter.this.list.get(oldItemPosition).getID() == (list.get(newItemPosition).getID());
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Verse oldVerse = VerseListAdapter.this.list.get(oldItemPosition);

                    Verse newVerse = list.get(newItemPosition);

                    return oldVerse.getID() == newVerse.getID() && oldVerse.getVerse().equals(newVerse.getVerse())
                            && oldVerse.getTitle().equals(newVerse.getTitle()) && oldVerse.getDate().equals(newVerse.getDate())
                            && oldVerse.isPinned() == newVerse.isPinned();
                }
            });
            this.list = list;
            result.dispatchUpdatesTo(this);
        }

    }

    @NonNull
    @Override
    public VerseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new VerseViewHolder(LayoutInflater.from(context).inflate(R.layout.notes_list, parent, false));
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull VerseViewHolder holder, int position) {
        holder.textView_title.setText(list.get(position).getTitle());
        holder.textView_title.setSelected(true);
        holder.textView_verse.setText(list.get(position).getVerse());
        holder.textView_date.setText(list.get(position).getDate());
        holder.textView_date.setSelected(true);
        if (list.get(position).isPinned()) {
            holder.imageView_pin.setImageResource(R.drawable.ic_sticky_note);
        } else {
            holder.imageView_pin.setImageResource(0);
        }
        int color_code = getRandomColor();
        holder.verse_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code, null));
        holder.verse_container.setOnClickListener(view -> listener.onClick(list.get(holder.getAdapterPosition())));

        holder.verse_container.setOnLongClickListener(view -> {
            listener.onLongClick(list.get(holder.getAdapterPosition()), holder.verse_container);
            return true;
        });
    }

    private int getRandomColor() {
        List<Integer> colorCode = new ArrayList<>();
        colorCode.add(R.color.color1);
        colorCode.add(R.color.color2);
        colorCode.add(R.color.color3);
        colorCode.add(R.color.color4);
        colorCode.add(R.color.color5);

        Random random = new Random();
        int random_color = random.nextInt(colorCode.size());
        return colorCode.get(random_color);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

}

class VerseViewHolder extends RecyclerView.ViewHolder {

    CardView verse_container;
    TextView textView_title, textView_verse, textView_date;
    public ImageView imageView_pin;

    public VerseViewHolder(@NonNull View itemView) {
        super(itemView);
        verse_container = itemView.findViewById(R.id.verse_container);
        textView_title = itemView.findViewById(R.id.textView_title);
        textView_verse = itemView.findViewById(R.id.textView_verse);
        textView_date = itemView.findViewById(R.id.textView_date);
        imageView_pin = itemView.findViewById(R.id.ImageView_pin);
    }
}