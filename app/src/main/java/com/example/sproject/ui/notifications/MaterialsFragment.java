package com.example.sproject.ui.notifications;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.sproject.R;
import com.example.sproject.databinding.FragmentMaterialsBinding;
import com.example.sproject.ui.defenitions.GenresFragment;
import com.example.sproject.ui.defenitions.MovementsFragment;
import com.example.sproject.ui.notifications.adapter.TabLayoutAdapter;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

import java.util.ArrayList;
import java.util.Objects;

public class MaterialsFragment extends Fragment {

    private FragmentMaterialsBinding binding;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
//        MaterialsViewModel materialsViewModel =
//                new ViewModelProvider(this).get(MaterialsViewModel.class);

        binding = FragmentMaterialsBinding.inflate(inflater, container, false);

//        final TextView textView = binding.textNotifications;
//        materialsViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
//        setupSpinner();
//        setupSpinner2();
        String [] fragmentNames = getResources().getStringArray(R.array.type_of_definitions);

        ArrayList<Fragment> fragmentArrayList = new ArrayList<>();
        fragmentArrayList.add(new MovementsFragment());
        fragmentArrayList.add(new GenresFragment());

        ViewPager2 mainViewPager = view.findViewById(R.id.material_view_pager);
        mainViewPager.setAdapter(
                new TabLayoutAdapter(requireActivity(),fragmentArrayList)
        );

        TabLayout mainTabs = view.findViewById(R.id.material_tabs);

        new TabLayoutMediator(
                mainTabs,
                mainViewPager,
                (tab, position) -> tab.setText(fragmentNames[position])
        ).attach();
    }

//    private void setupSpinner2() {
//        ArrayList<String> list = new ArrayList<>(Arrays.asList(requireContext().getResources().getStringArray(R.array.literary_movement)));
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, list);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.spinner.setAdapter(adapter);
//        binding.spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//            }
//        });
//    }


//    private void setupSpinner() {
//        ArrayList<String> list = new ArrayList<>(Arrays.asList(requireContext().getResources().getStringArray(R.array.genres_names)));
//        ArrayAdapter<String> adapter = new ArrayAdapter<>(requireContext(), android.R.layout.simple_spinner_item, list);
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        binding.spinner2.setAdapter(adapter);
//        binding.spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) { }
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) { }
//        });
//    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}