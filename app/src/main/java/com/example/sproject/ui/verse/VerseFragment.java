package com.example.sproject.ui.verse;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.example.sproject.R;
import com.example.sproject.VerseClickListener;
import com.example.sproject.adapters.VerseListAdapter;
import com.example.sproject.databinding.FragmentVerseBinding;
import com.example.sproject.models.Verse;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


public class VerseFragment extends Fragment {

    private FragmentVerseBinding binding;
    private VerseListAdapter verseListAdapter;
    private List<Verse> verse = new ArrayList<>();
    private Verse selectedVerse;

    private NavController navController;
    private static int counter = 0;
    private VerseViewModel verseViewModel;
    private Object VerseClickListener;
    public static final String VERSE = "notes";

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

        binding = FragmentVerseBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        verseViewModel = new ViewModelProvider(this).get(VerseViewModel.class);
        verseViewModel.getVerseList();
        binding.recyclerVerse.setLayoutManager(new StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL));
//        verseListAdapter = new VerseListAdapter(requireContext(), verse, verseClickListener);

        initListeners();
        initObservers();
    }

    private void setSearch() {
        binding.searchViewVerse.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
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
        verseViewModel.verseList.observe(getViewLifecycleOwner(), verseList -> {
            verse = verseList;
            updateRecycler(verseList);
            //showEmptyAnimation();
            setSearch();
        });

    }

    private void initListeners() {
        binding.fabAddFavorites.setOnClickListener(view1 -> {
            navController.navigate(R.id.action_navigation_favorites_to_verseTakerFragment);
        });
    }

    private void filter(String s) {
        List<Verse> filteredList = new ArrayList<>();
        for (Verse singleVerse : verse) {
            if (singleVerse.getTitle().toLowerCase().contains(s.toLowerCase())
                    || singleVerse.getVerse().toLowerCase().contains(s.toLowerCase())) {
                filteredList.add(singleVerse);
            }
        }
        if (!filteredList.isEmpty()) {
            verseListAdapter.setList(filteredList);
        } else {
            verseListAdapter.setList(new ArrayList<>());
        }

    }

    private void updateRecycler(List<Verse> verse) {
        verseListAdapter = new VerseListAdapter(requireContext(), verse, verseClickListener);
        binding.recyclerVerse.setAdapter(verseListAdapter);
    }

    private final VerseClickListener verseClickListener = new VerseClickListener() {
        @Override
        public void onClick(Verse verse) {
            Bundle args = new Bundle();
            args.putSerializable(VERSE,verse);
            navController.navigate(R.id.action_navigation_favorites_to_verseTakerFragment,args);
        }

        @Override
        public void onLongClick(Verse verse, CardView cardView) {
            selectedVerse = new Verse();
            selectedVerse = verse;
//            showPopup(cardView);
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (counter > 0) {
            verseViewModel.getVerseList();
        }
        counter++;
    }
//    private void showEmptyAnimation() {
//        if (verseListAdapter.getItemCount() == 0) {
//            binding.emptyAnimationHome.setVisibility(View.VISIBLE);
//        } else {
//            binding.emptyAnimationHome.setVisibility(View.GONE);
//        }
//    }

}
