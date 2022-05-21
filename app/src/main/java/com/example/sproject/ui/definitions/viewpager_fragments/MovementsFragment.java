package com.example.sproject.ui.definitions.viewpager_fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sproject.R;
import com.example.sproject.databinding.FragmentDefinitionsBinding;
import com.example.sproject.ui.definitions.adapter.DefinitionsRecyclerAdapter;
import com.example.sproject.ui.definitions.OnItemDefinitionsRecyclerClickInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class MovementsFragment extends Fragment implements OnItemDefinitionsRecyclerClickInterface {

    private FragmentDefinitionsBinding binding;
    Dialog dialog;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDefinitionsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.literary_movement)));
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.definitionsRecycler.setLayoutManager(layoutManager);
        DefinitionsRecyclerAdapter adapter = new DefinitionsRecyclerAdapter(arrayList,requireContext(),this);
        binding.definitionsRecycler.setAdapter(adapter);
        dialog = new Dialog(requireActivity());
        dialog.setContentView(R.layout.dialog_details);
    }


    @Override
    public void onItemClick(int position) {
        dialog.show();
        dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        dialog.findViewById(R.id.dialog_container).setBackgroundColor(getResources().getColor(R.color.transparent));
        TextView text = dialog.findViewById(R.id.details_text);
       // ArrayList<String> genresArray = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.movements_description)));
       // text.setText(genresArray.get(position));
        dialog.findViewById(R.id.btn_dialog_details).setOnClickListener(v -> dialog.dismiss());
    }
}
