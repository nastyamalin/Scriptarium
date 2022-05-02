package com.example.sproject.ui.defenitions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.sproject.R;
import com.example.sproject.databinding.FragmentDefinitionsBinding;
import com.example.sproject.ui.defenitions.adapter.DefinitionsRecyclerAdapter;
import com.example.sproject.ui.defenitions.adapter.OnItemDefinitionsRecyclerClickInterface;

import java.util.ArrayList;
import java.util.Arrays;

public class GenresFragment extends Fragment implements OnItemDefinitionsRecyclerClickInterface {

    private FragmentDefinitionsBinding binding;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDefinitionsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> arrayList = new ArrayList<>(Arrays.asList(getResources().getStringArray(R.array.genres_names)));
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.definitionsRecycler.setLayoutManager(layoutManager);
        DefinitionsRecyclerAdapter adapter = new DefinitionsRecyclerAdapter(arrayList,requireContext(),this);
        binding.definitionsRecycler.setAdapter(adapter);
    }


    @Override
    public void onItemClick(int position) {

    }
}
