package com.example.sproject.ui.notifications.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.sproject.ui.defenitions.GenresFragment;
import com.example.sproject.ui.notifications.MaterialsFragment;
import com.example.sproject.ui.notifications.viewpager_fragments.LiteraryFlowFragment;
import com.example.sproject.ui.notifications.viewpager_fragments.LiteraryGenresFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private static final int pagesCount = 2;

    public ViewPagerAdapter(@NonNull MaterialsFragment materialsFragment) {
        super(materialsFragment);

    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new LiteraryFlowFragment();
            case 1: return new LiteraryGenresFragment();
            default: return null;
        }
    }

    @Override
    public int getItemCount() {
        return pagesCount;
    }
}
