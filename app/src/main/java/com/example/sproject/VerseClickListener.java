package com.example.sproject;

import androidx.cardview.widget.CardView;

import com.example.sproject.models.Verse;

public interface VerseClickListener {

        void onClick(Verse verse);
        void onLongClick(Verse verse, CardView cardView);

}
