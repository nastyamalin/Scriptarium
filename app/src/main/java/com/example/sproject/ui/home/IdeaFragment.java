package com.example.sproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.sproject.NotesClickListener;
import com.example.sproject.R;
import com.example.sproject.adapters.NotesListAdapter;
import com.example.sproject.databinding.FragmentHomeBinding;
import com.example.sproject.models.Notes;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class IdeaFragment extends Fragment {
    private FragmentHomeBinding binding;
    private NotesListAdapter notesListAdapter;
    private List<Notes> notes = new ArrayList<>();
    private Notes selectedNote;
    private IdeaViewModel ideaViewModel;
    private NavController navController;
    private static int counter = 0;

    @Override
    public void onStart() {
        super.onStart();
        requireActivity().findViewById(R.id.nav_view).setVisibility(View.VISIBLE);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NavHostFragment navHostFragment = (NavHostFragment) requireActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_activity_main);
        navController = Objects.requireNonNull(navHostFragment).getNavController();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ideaViewModel = new ViewModelProvider(this).get(IdeaViewModel.class);
        ideaViewModel.getNotesList();
        binding.recyclerHome.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        notesListAdapter = new NotesListAdapter(requireContext(), notes, notesClickListener);

        initListeners();
        initObservers();
    }

    private void setSearch() {
        binding.searchViewHome.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                filter(s.trim());
                return true;
            }
        });
    }

    private void initObservers() {
        ideaViewModel.notesList.observe(getViewLifecycleOwner(), notesList -> {
            notes = notesList;
            updateRecycler(notesList);
            showEmptyAnimation();
            setSearch();
        });

    }

    private void initListeners() {
        binding.fabAdd.setOnClickListener(view1 -> {
            navController.navigate(R.id.action_navigation_ideas_to_notesTakerFragment);
        });
    }

    private void filter(String s) {
        List<Notes> filteredList = new ArrayList<>();
        for (Notes singleNote : notes) {
            if (singleNote.getTitle().toLowerCase().contains(s.toLowerCase())
                    || singleNote.getNotes().toLowerCase().contains(s.toLowerCase())) {
                filteredList.add(singleNote);
            }
        }
        if (!filteredList.isEmpty()){
            notesListAdapter.setList(filteredList);
        }else{
            notesListAdapter.setList(new ArrayList<>());
        }

    }


    private void updateRecycler(List<Notes> notes) {
        notesListAdapter = new NotesListAdapter(requireContext(), notes, notesClickListener);
        binding.recyclerHome.setAdapter(notesListAdapter);
    }

    private final NotesClickListener notesClickListener = new NotesClickListener() {
        @Override
        public void onClick(Notes notes) {

        }

        @Override
        public void onLongClick(Notes notes, CardView cardView) {
            selectedNote = new Notes();
            selectedNote = notes;
            showPopup(cardView);
        }
    };

    private void showPopup(CardView cardView) {
        PopupMenu popupMenu = new PopupMenu(requireContext(), cardView);
        popupMenu.setOnMenuItemClickListener(this::onMenuItemClick);
        popupMenu.inflate(R.menu.popup_menu);
        popupMenu.show();
    }

    private boolean onMenuItemClick(MenuItem menuItem) {
        if (menuItem.getItemId() == R.id.pin) {
            if (selectedNote.isPinned()) {
                ideaViewModel.updatePin(selectedNote,false);
                Toast.makeText(requireContext(),
                        "Unpinned!",
                        Toast.LENGTH_SHORT).show();
            } else {
                ideaViewModel.updatePin(selectedNote,false);
                Toast.makeText(requireContext(), "Pinned", Toast.LENGTH_SHORT).show();
            }
            ideaViewModel.getNotesList();
            ideaViewModel.notesList.observe(getViewLifecycleOwner(), notes -> notesListAdapter.setList(notes));
            return true;
        } else if (menuItem.getItemId() == R.id.delete) {

        }
        return false;
    }


    private void showEmptyAnimation() {
        if (notesListAdapter.getItemCount() == 0) {
            binding.emptyAnimationHome.setVisibility(View.VISIBLE);
        } else {
            binding.emptyAnimationHome.setVisibility(View.GONE);
        }
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (counter > 0) {
            ideaViewModel.getNotesList();
        }
        counter++;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}


