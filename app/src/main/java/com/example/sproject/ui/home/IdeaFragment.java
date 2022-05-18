package com.example.sproject.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

//        database = RoomDB.gerInstance(requireContext());
//
//        if (database != null) {
//            notes = database.mainDAO().getAll();
//        }
        binding.recyclerHome.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
        notesListAdapter = new NotesListAdapter(requireContext(), notes, notesClickListener);


        initListeners();
        initObservers();
//        setSearch();
    }

    private void setSearch() {
//        binding.searchViewHome.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
//            @Override
//            public boolean onQueryTextSubmit(String s) {
//                return false;
//            }
//
//            @Override
//            public boolean onQueryTextChange(String s) {
//                filter();
//                return true;
//            }
//        });
    }

    private void initObservers() {
        ideaViewModel.notesList.observe(getViewLifecycleOwner(), this::updateRecycler);
    }

    private void initListeners() {
        binding.fabAdd.setOnClickListener(view1 -> navController.navigate(R.id.action_navigation_ideas_to_notesTakerFragment));
    }

    private void filter() {
        List<Notes> filteredList = new ArrayList<>();
        for (Notes singleNote : notes) {
            if (singleNote.getTitle().toLowerCase().contains("newText".toLowerCase())
                    || singleNote.getNotes().toLowerCase().contains("newText".toLowerCase())) {
                filteredList.add(singleNote);
            }
        }
        notesListAdapter.filteredList(filteredList);
    }

//    @Override
//    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == 101) {
//            if (resultCode == Activity.RESULT_OK) {
//                Notes new_notes;
//                if (data != null) {
//                    new_notes = (Notes) data.getSerializableExtra("note");
//                    database.mainDAO().insert(new_notes);
//                    notes.clear();
//                    notes.addAll(database.mainDAO().getAll());
//                    notesListAdapter.notifyDataSetChanged();
//                }
//            }
//        } else if (requestCode == 102) {
//            if (resultCode == Activity.RESULT_OK) {
//                Notes new_notes;
//                if (data != null) {
//                    new_notes = (Notes) data.getSerializableExtra("note");
//                    database.mainDAO().update(Objects.requireNonNull(new_notes).getID(), new_notes.getTitle(), new_notes.getNotes());
//                    notes.clear();
//                    notes.addAll(database.mainDAO().getAll());
//                    notesListAdapter.notifyDataSetChanged();
//                }
//            }
//        }
//    }

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
//            showPopup(cardView);
        }
    };

//    private void showPopup(CardView cardView) {
//        PopupMenu popupMenu = new PopupMenu(requireContext(), cardView);
//        popupMenu.setOnMenuItemClickListener(this);
//        popupMenu.inflate(R.menu.popup_menu);
//        popupMenu.show();
//    }

//    @Override
//    public boolean onMenuItemClick(MenuItem menuItem) {
//        if (menuItem.getItemId() == R.id.pin) {
//            if (selectedNote.isPinned()) {
//                database.mainDAO().pin(selectedNote.getID(), false);
//                Toast.makeText(requireContext(),
//                        "Unpinned!",
//                        Toast.LENGTH_SHORT).show();
//            } else {
//                database.mainDAO().pin(selectedNote.getID(), true);
//                Toast.makeText(requireContext(), "Pinned", Toast.LENGTH_SHORT).show();
//            }
//            notes.clear();
//            notes.addAll(database.mainDAO().getAll());
//            notesListAdapter.notifyDataSetChanged();
//            return true;
//        } else if (menuItem.getItemId() == R.id.delete) {
//
//        }
//        return false;
//    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (counter>0){
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


