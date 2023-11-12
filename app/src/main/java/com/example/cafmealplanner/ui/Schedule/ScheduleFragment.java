package com.example.cafmealplanner.ui.Schedule;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.autofill.AutofillValue;
import android.widget.Button;
import android.widget.LinearLayout;
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

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setupLinearLayouts();

    }




    void setupLinearLayouts() {
        //Sunday
        LinearLayout day = getView().findViewById(R.id.sundayLinearLayout);
        mealButton m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.SUNDAY);
        m.setMeal(mealButton.mealTime.BREAKFAST);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.SUNDAY);
        m.setMeal(mealButton.mealTime.LUNCH);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.SUNDAY);
        m.setMeal(mealButton.mealTime.DINNER);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);

        //Monday
        day = getView().findViewById(R.id.mondayLinearLayout);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.MONDAY);
        m.setMeal(mealButton.mealTime.BREAKFAST);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.MONDAY);
        m.setMeal(mealButton.mealTime.LUNCH);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.MONDAY);
        m.setMeal(mealButton.mealTime.DINNER);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);

        //Tuesday
        day = getView().findViewById(R.id.tuesdayLinearLayout);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.TUESDAY);
        m.setMeal(mealButton.mealTime.BREAKFAST);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.TUESDAY);
        m.setMeal(mealButton.mealTime.LUNCH);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.TUESDAY);
        m.setMeal(mealButton.mealTime.DINNER);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);

        //Wednesday
        day = getView().findViewById(R.id.wednesdayLinearLayout);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.WEDNESDAY);
        m.setMeal(mealButton.mealTime.BREAKFAST);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.WEDNESDAY);
        m.setMeal(mealButton.mealTime.LUNCH);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.WEDNESDAY);
        m.setMeal(mealButton.mealTime.DINNER);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);

        //Thursday
        day = getView().findViewById(R.id.thursdayLinearLayout);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.THURSDAY);
        m.setMeal(mealButton.mealTime.BREAKFAST);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.THURSDAY);
        m.setMeal(mealButton.mealTime.LUNCH);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.THURSDAY);
        m.setMeal(mealButton.mealTime.DINNER);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);

        //Friday
        day = getView().findViewById(R.id.fridayLinearLayout);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.FRIDAY);
        m.setMeal(mealButton.mealTime.BREAKFAST);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.FRIDAY);
        m.setMeal(mealButton.mealTime.LUNCH);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.FRIDAY);
        m.setMeal(mealButton.mealTime.DINNER);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);

        //Saturday
        day = getView().findViewById(R.id.saturdayLinearLayout);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.SATURDAY);
        m.setMeal(mealButton.mealTime.BREAKFAST);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.SATURDAY);
        m.setMeal(mealButton.mealTime.LUNCH);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);
        m = new mealButton(getContext());
        m.setDay(mealButton.dayOfWeek.SATURDAY);
        m.setMeal(mealButton.mealTime.DINNER);
        m.setFill(mealButton.buttonSelection.EMPTY);
        day.addView(m);

    }

/*
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