package com.example.cafmealplanner.ui.Menu;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cafmealplanner.R;
import com.example.cafmealplanner.ui.Schedule.ScheduleFragment;

import java.util.Vector;

public class FoodInfo extends Fragment implements View.OnClickListener {

    private FoodInfoViewModel mViewModel;

    public static FoodInfo newInstance() {
        return new FoodInfo();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_food_info, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Create a vector of strings to keep all the ingredients
        Vector<String> ingredientNames = new Vector<String>(3);

        for (int i = 0; i < 3; i++) {
            ingredientNames.add(new String());
        }

        // Populate the ingredients list with dummy strings
        ingredientNames.get(0).equals("Noodles");
        ingredientNames.get(1).equals("Sauce");
        ingredientNames.get(2).equals("Breadsticks");

        // Display the ingredients list in the text view
        TextView ingredientsList = getView().findViewById(R.id.ingredients);

        for (int i = 0; i < ingredientNames.size(); i++) {
            ingredientsList.append(ingredientNames.get(i));

            if (i < ingredientNames.size() - 1) {
                ingredientsList.append(", ");
            }
        }

        // Implement the "rate this" button
        Button addRating = getView().findViewById(R.id.rateThis);
        addRating.setOnClickListener((View.OnClickListener) this);
    }

    public void onClick(View v) {
        if (v == getView().findViewById(R.id.rateThis)) {
            // Get the rating the user entered
            // and change the equivalent number of stars to yellow
        }
    }
}