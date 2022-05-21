package com.example.sproject.ui.definitions.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sproject.R;
import com.example.sproject.ui.definitions.OnItemDefinitionsRecyclerClickInterface;

import java.util.ArrayList;

public class DefinitionsRecyclerAdapter extends RecyclerView.Adapter<DefinitionsViewHolder> {
    private final ArrayList<String> definitions;
    private final Context context;
    private final OnItemDefinitionsRecyclerClickInterface clickInterface;

    public DefinitionsRecyclerAdapter(ArrayList<String> definitions, Context context, OnItemDefinitionsRecyclerClickInterface clickInterface) {
        this.definitions = definitions;
        this.context = context;
        this.clickInterface = clickInterface;
    }

    @NonNull
    @Override
    public DefinitionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(context)
                .inflate(R.layout.definitions_recycler_item, parent, false);
        return new DefinitionsViewHolder(itemView, clickInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull DefinitionsViewHolder holder, int position) {
        holder.itemTextView.setText(definitions.get(position));
    }

    @Override
    public int getItemCount() {
        return definitions.size();
    }
}
