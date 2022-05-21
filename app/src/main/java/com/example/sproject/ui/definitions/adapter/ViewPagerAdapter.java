package com.example.sproject.ui.definitions.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.sproject.ui.definitions.MaterialsFragment;

import java.util.ArrayList;

public class ViewPagerAdapter extends FragmentStateAdapter {
    private final ArrayList<Fragment> viewPagerFragments;

    public ViewPagerAdapter(@NonNull MaterialsFragment materialsFragment, ArrayList<Fragment> fragmentsList) {
        super(materialsFragment);
        this.viewPagerFragments = fragmentsList;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        return viewPagerFragments.get(position);
    }

    @Override
    public int getItemCount() {
        return viewPagerFragments.size();
    }
}
