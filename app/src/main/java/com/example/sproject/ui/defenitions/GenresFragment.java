package com.example.sproject.ui.defenitions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sproject.databinding.FragmentDefinitionsBinding;

public class GenresFragment extends Fragment {

    private FragmentDefinitionsBinding binding;
    private int kindOfDefinitions;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentDefinitionsBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setDefinitions();
        initListeners();
    }

    private void initListeners() {

    }

    private void setDefinitions() {
        switch (kindOfDefinitions){

        }
    }
}
