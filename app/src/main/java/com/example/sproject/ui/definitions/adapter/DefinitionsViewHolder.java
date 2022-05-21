package com.example.sproject.ui.definitions.adapter;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sproject.R;
import com.example.sproject.ui.definitions.OnItemDefinitionsRecyclerClickInterface;

public class DefinitionsViewHolder extends RecyclerView.ViewHolder {

    TextView itemTextView;
    CardView itemCardView;

    public DefinitionsViewHolder(@NonNull View itemView, OnItemDefinitionsRecyclerClickInterface clickInterface) {
        super(itemView);
        itemTextView = itemView.findViewById(R.id.item_text);
        itemCardView = itemView.findViewById(R.id.definitions_item);

        itemView.setOnClickListener(view -> {
            if(clickInterface != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    clickInterface.onItemClick(position);
                }
            }
        });
    }

}
