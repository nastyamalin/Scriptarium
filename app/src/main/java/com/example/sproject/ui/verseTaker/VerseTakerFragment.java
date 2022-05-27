package com.example.sproject.ui.verseTaker;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sproject.R;

import com.example.sproject.databinding.FragmentVerseTakerBinding;

import com.example.sproject.models.Verse;

import com.example.sproject.ui.verse.VerseViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class VerseTakerFragment extends Fragment {
    private Verse verse = new Verse();
    private FragmentVerseTakerBinding binding;
    boolean isOldVerse = false;
    private VerseViewModel verseViewModel;

    @Override
    public void onStart() {
        super.onStart();
        requireActivity().findViewById(R.id.nav_view).setVisibility(View.GONE);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentVerseTakerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        verseViewModel = new ViewModelProvider(requireActivity()).get(VerseViewModel.class);
        initListeners();
    }

    private void initListeners() {
        binding.ImageViewSave.setOnClickListener(view -> prepareVerse());
    }

    private void prepareVerse() {
        String title = binding.editTextTitle.getText().toString();
        String description = binding.editTextVerse.getText().toString();
        if (description.isEmpty()) {
            Toast.makeText(requireContext(), "Please add some text", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a", Locale.getDefault());
        Date date = new Date();
        if (!isOldVerse) {
            verse = new Verse();
        }
        verse.setTitle(title);
        verse.setVerse(description);
        verse.setDate(formatter.format(date));
        verseViewModel.updateVerse(verse);
        NavHostFragment.findNavController(this).navigate(R.id.navigation_favorites);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}