package com.example.cafmealplanner.ui.Schedule;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cafmealplanner.R;
import com.example.cafmealplanner.databinding.FragmentMealInfoBinding;
import com.example.cafmealplanner.databinding.FragmentScheduleBinding;
import com.example.cafmealplanner.ui.Menu.mealView;

import android.widget.ImageButton;
import android.content.Intent;
import android.widget.LinearLayout;

import java.util.Vector;


public class MealInfo extends Fragment implements View.OnClickListener {

    public static MealInfo newInstance() {
        return new MealInfo();
    }

    private MealInfoViewModel mViewModel;
    private FragmentMealInfoBinding binding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ScheduleViewModel dashboardViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        binding = FragmentMealInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        //final TextView textView = binding.textView12;
        //dashboardViewModel.getText().observe(getViewLifecycleOwner(), textView::setText);
        return root;
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Create the button to return to the schedule page and set the on click listener
        ImageButton backToSchedule = (ImageButton) getView().findViewById(R.id.backToSchedule);
        backToSchedule.setOnClickListener((View.OnClickListener) this);

        // Add some meal views

        LinearLayout dinnerLinearLayout = (LinearLayout)getView().findViewById(R.id.mealInfoLinearLayout);
        LinearLayout dinnerItems = new LinearLayout(getContext());
        dinnerItems.setOrientation(LinearLayout.VERTICAL);
        Log.d("Made it all the way here!", "onViewCreated: ");

        mealView dinnerMeals[] = new mealView[5];
        for (int i = 0; i < 5; i++) {
            dinnerMeals[i] = new mealView(getContext());
        }

        for (int i = 0; i<5; i++) { // All the food options are pasta for some reason
            dinnerMeals[i].setMealName("PASTA");
            dinnerItems.addView(dinnerMeals[i]);
        }

        dinnerLinearLayout.addView(dinnerItems);
    }

    public void onClick(View v) {

    }





}