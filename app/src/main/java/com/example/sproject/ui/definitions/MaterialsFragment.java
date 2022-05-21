package com.example.sproject.ui.definitions;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.sproject.R;
import com.example.sproject.databinding.FragmentMaterialsBinding;
import com.example.sproject.ui.definitions.adapter.ViewPagerAdapter;
import com.google.android.material.tabs.TabLayoutMediator;

public class MaterialsFragment extends Fragment {

    private FragmentMaterialsBinding binding;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

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
        ViewPager2 viewPager2 = binding.infoViewPager;
        viewPager2.setAdapter(new ViewPagerAdapter(this));
        new TabLayoutMediator(binding.infoTabLayout, viewPager2, (tab, position) -> tab.setText(tabText[position])).attach();
    }



    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}