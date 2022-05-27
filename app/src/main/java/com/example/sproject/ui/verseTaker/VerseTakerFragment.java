package com.example.sproject.ui.verseTaker;

import static com.example.sproject.ui.verse.VerseFragment.VERSE;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;

import com.example.sproject.R;
import com.example.sproject.databinding.FragmentVerseTakerBinding;
import com.example.sproject.models.Verse;
import com.example.sproject.ui.verse.VerseViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class VerseTakerFragment extends Fragment {
    private Verse verse = new Verse();
    private FragmentVerseTakerBinding binding;
    boolean isOldVerse = false;
    private VerseViewModel verseViewModel;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            verse = (Verse) getArguments().getSerializable(VERSE);
        }
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        navController = Objects.requireNonNull(navHostFragment).getNavController();
    }

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
        setupVerse();
    }

    private void initListeners() {
        binding.ImageViewSave.setOnClickListener(view -> prepareVerse());
        binding.arrowBtn.setOnClickListener(v -> {
            navController.navigateUp();
        });
    }

    private void setupVerse() {
        if (verse.getTitle() != null) binding.editTextTitle.setText(verse.getTitle());
        if (verse.getVerse() != null) binding.editTextVerse.setText(verse.getVerse());
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