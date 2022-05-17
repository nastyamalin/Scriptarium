//package com.example.sproject.Adapters;
//
//import android.content.Context;
//import android.os.Build;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.RequiresApi;
//import androidx.cardview.widget.CardView;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.example.sproject.Models.Notes;
//import com.example.sproject.Models.Verse;
//import com.example.sproject.NotesClickListener;
//import com.example.sproject.R;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Random;
//
//public class VerseListAdapter extends RecyclerView.Adapter<VerseViewHolder> {
//    Context context;
//    List<Verse> list;
//    NotesClickListener listener;
//
//    public VerseListAdapter(Context context, List<Verse> list, NotesClickListener listener) {
//        this.context = context;
//        this.list = list;
//        this.listener = listener;
//    }
//
//    @NonNull
//    @Override
//    public VerseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        return new VerseViewHolder(LayoutInflater.from(context).inflate(R.layout.favorites_list, parent, false));
//    }
//
//    @RequiresApi(api = Build.VERSION_CODES.M)
//    @Override
//    public void onBindViewHolder(@NonNull NotesViewHolder holder, int position) {
//        holder.textView_title.setText(list.get(position).getTitle());
//        holder.textView_title.setSelected(true);
//        holder.textView_verse.setText(list.get(position).getVerse());
//        holder.textView_date.setText(list.get(position).getDate());
//        holder.textView_date.setSelected(true);
//        if(list.get(position).isPinned()) {
//            holder.imageView_pin.setImageResource(R.drawable.ic_sticky_note);
//        }
//        else {
//            holder.imageView_pin.setImageResource(0);
//        }
//        int color_code = getRandomColor();
//        holder.verse_container.setCardBackgroundColor(holder.itemView.getResources().getColor(color_code, null));
//        holder.verse_container.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                listener.onClick(list.get(holder.getAdapterPosition()));
//            }
//        });
//
//        holder.verse_container.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View view) {
//                listener.onLongClick(list.get(holder.getAdapterPosition()), holder.verse_container);
//                return true;
//            }
//        });
//    }
//    private int getRandomColor(){
//        List<Integer> colorCode = new ArrayList<>();
//        colorCode.add(R.color.color1);
//        colorCode.add(R.color.color2);
//        colorCode.add(R.color.color3);
//        colorCode.add(R.color.color4);
//        colorCode.add(R.color.color5);
//
//        Random random = new Random();
//        int random_color = random.nextInt(colorCode.size());
//        return colorCode.get(random_color);
//    }
//
//
//    @Override
//    public int getItemCount() {
//        return list.size();
//    }
//    public void filteredList(List<Verse> filteredList){
//        list = filteredList;
//        notifyDataSetChanged();
//    }
//}
//class VerseViewHolder extends RecyclerView.ViewHolder {
//
//    CardView verse_container;
//    TextView textView_title, textView_verse,textView_date;
//    public ImageView imageView_pin;
//    public VerseViewHolder(@NonNull View itemView){
//        super(itemView);
//        verse_container=itemView.findViewById(R.id.notes_container);
//        verse_container=itemView.findViewById(R.id.textView_title);
//        verse_container=itemView.findViewById(R.id.textView_notes);
//        verse_container=itemView.findViewById(R.id.textView_date);
//        verse_container=itemView.findViewById(R.id.ImageView_pin);
//    }
//}
//}
