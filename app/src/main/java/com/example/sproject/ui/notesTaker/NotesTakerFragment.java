package com.example.sproject.ui.notesTaker;

import static com.example.sproject.ui.home.IdeaFragment.NOTES;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
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
import com.example.sproject.databinding.FragmentNotesTakerBinding;
import com.example.sproject.models.Notes;
import com.example.sproject.ui.home.IdeaViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class NotesTakerFragment extends Fragment {
    private Notes notes = new Notes();
    private FragmentNotesTakerBinding binding;
    boolean isOldNote = false;
    private IdeaViewModel ideaViewModel;
    private NavController navController;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(getArguments() != null){
            notes = (Notes) getArguments().getSerializable(NOTES);
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
        binding = FragmentNotesTakerBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ideaViewModel = new ViewModelProvider(requireActivity()).get(IdeaViewModel.class);
        initListeners();
        setupNotes();
    }

    private void setupNotes() {
        if(notes.getTitle()!=null)binding.editTextTitle.setText(notes.getTitle());
        if(notes.getNotes()!=null)binding.editTextNotes.setText(notes.getNotes());
    }

    private void initListeners() {
        binding.ImageViewSave.setOnClickListener(view -> prepareNotes());
        binding.arrowBtn.setOnClickListener(v->{
            navController.navigateUp();
        });
    }


    private void prepareNotes() {
        String title = binding.editTextTitle.getText().toString();
        String description = binding.editTextNotes.getText().toString();
        if (description.isEmpty()) {
            Toast.makeText(requireContext(), "Please add some text", Toast.LENGTH_SHORT).show();
            return;
        }
        SimpleDateFormat formatter = new SimpleDateFormat("EEE, d MMM yyyy HH:mm a", Locale.getDefault());
        Date date = new Date();
//        if (!isOldNote) {
//            notes = new Notes();
//        }
        notes.setTitle(title);
        notes.setNotes(description);
        notes.setDate(formatter.format(date));
        ideaViewModel.addNote(notes);
        NavHostFragment.findNavController(this).navigate(R.id.action_notesTakerFragment_to_navigation_ideas);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}