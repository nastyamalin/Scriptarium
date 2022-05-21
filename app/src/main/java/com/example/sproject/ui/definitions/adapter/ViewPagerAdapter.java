package com.example.sproject.ui.definitions.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.sproject.ui.definitions.MaterialsFragment;
import com.example.sproject.ui.definitions.viewpager_fragments.GenresFragment;
import com.example.sproject.ui.definitions.viewpager_fragments.MovementsFragment;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private static final int pagesCount = 2;

    public ViewPagerAdapter(@NonNull MaterialsFragment materialsFragment) {
        super(materialsFragment);

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new GenresFragment();
            case 1: return new MovementsFragment();
            default: return null;
        }
    }

    @Override
    public int getItemCount() {
        return pagesCount;
    }
}
