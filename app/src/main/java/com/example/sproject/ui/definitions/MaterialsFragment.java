package com.example.sproject.ui.definitions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.sproject.R;
import com.example.sproject.databinding.FragmentMaterialsBinding;
import com.example.sproject.ui.definitions.adapter.ViewPagerAdapter;
import com.example.sproject.ui.definitions.viewpager_fragments.GenresFragment;
import com.example.sproject.ui.definitions.viewpager_fragments.MovementsFragment;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;

public class MaterialsFragment extends Fragment {

    private FragmentMaterialsBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMaterialsBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewPagerAndTabLayouts();
    }

    private void initViewPagerAndTabLayouts(){
        String[] tabText = {getString(R.string.literary_flows), getString(R.string.literary_genres)};
        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new MovementsFragment());
        fragmentArrayList.add(new GenresFragment());
        binding.infoViewPager.setAdapter(new ViewPagerAdapter(this, fragmentArrayList));
        new TabLayoutMediator(binding.infoTabLayout, binding.infoViewPager, (tab, position) -> tab.setText(tabText[position])).attach();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}