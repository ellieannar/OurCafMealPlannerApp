package com.example.cafmealplanner.ui.Schedule;

import androidx.lifecycle.ViewModelProvider;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cafmealplanner.R;
import com.example.cafmealplanner.mealView;

import android.widget.ImageButton;
import android.content.Intent;
import android.widget.LinearLayout;

import java.util.Vector;

public class MealInfo extends Fragment {

    public static MealInfo newInstance() {
        return new MealInfo();
    }

    private MealInfoViewModel mViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_meal_info, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Create the button to return to the schedule page and set the on click listener
        ImageButton backToSchedule = (ImageButton) getView().findViewById(R.id.backToSchedule);
        backToSchedule.setOnClickListener((View.OnClickListener) this);

        // Add some meal views

        LinearLayout dinnerLinearLayout = (LinearLayout)getView().findViewById(R.id.dinnerLinearLayout);
        LinearLayout dinnerItems = new LinearLayout(getContext());
        dinnerItems.setOrientation(LinearLayout.VERTICAL);

        Vector<mealView> dinnerMeals = new Vector<mealView>(5);
        for (int i = 0; i < 5; i++) {
            dinnerMeals.add(new mealView(getContext()));
        }

        for (int i = 0; i < dinnerMeals.size(); i++) { // All the food options are pasta for some reason
            dinnerMeals.get(i).setMealName("PASTA");
            dinnerItems.addView(dinnerMeals.get(i));
        }

        dinnerLinearLayout.addView(dinnerItems);
    }

    public void onClick(View v) {
        if (v == getView().findViewById(R.id.backToSchedule)) {
            // Start the schedule activity if the button is clicked
            Intent intent = new Intent(getContext(), ScheduleFragment.class);
            startActivity(intent);
        }
    }

}