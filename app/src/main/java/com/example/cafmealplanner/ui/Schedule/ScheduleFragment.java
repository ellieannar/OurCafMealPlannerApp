package com.example.cafmealplanner.ui.Schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.autofill.AutofillValue;
import android.widget.Button;
import android.widget.TextView;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.cafmealplanner.R;
import com.example.cafmealplanner.databinding.FragmentScheduleBinding;

public class ScheduleFragment extends Fragment {

    private FragmentScheduleBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ScheduleViewModel dashboardViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        //final TextView textView = binding.textView12;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }

    /*public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Create the button to switch to the meal info page and set the on click listener
        Button toMealInfo = (Button) getView().findViewById(R.id.goToMealInfo);
        toMealInfo.setOnClickListener(this::onClick);
    }

    public void onClick(View v) {
        if (v == getView().findViewById(R.id.goToMealInfo)) {
            // If the button is clicked, change fragments
            Fragment fragment = new MealInfo();
            getParentFragmentManager().beginTransaction().replace(this, fragment).commit();
        }
    }*/


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}