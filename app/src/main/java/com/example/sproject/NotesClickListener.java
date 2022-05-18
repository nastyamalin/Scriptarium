package com.example.sproject;

import androidx.cardview.widget.CardView;

import com.example.sproject.models.Notes;

public interface NotesClickListener {
    void onClick(Notes notes);
    void onLongClick(Notes notes, CardView cardView);
}
