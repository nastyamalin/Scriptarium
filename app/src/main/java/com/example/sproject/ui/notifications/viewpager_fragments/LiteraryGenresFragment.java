package com.example.sproject.ui.notifications.viewpager_fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.example.sproject.R;
import com.example.sproject.databinding.FragmentLiteraryGenresBinding;

import java.util.ArrayList;
import java.util.Arrays;

public class LiteraryGenresFragment extends Fragment {
    FragmentLiteraryGenresBinding binding;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLiteraryGenresBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ArrayList<String> list = new ArrayList<>(Arrays.asList(requireContext().getResources().getStringArray(R.array.literary_movement)));
        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, list);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    }
}