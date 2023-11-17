package com.example.cafmealplanner.ui.Menu;

import androidx.activity.OnBackPressedCallback;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cafmealplanner.R;
import com.example.cafmealplanner.ui.Schedule.MealInfo;

import java.util.Vector;

public class FoodInfo extends Fragment implements View.OnClickListener {


    boolean editRatingOn = false;
    int starRating = 0; // Should be obtained from a database

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        container.removeAllViews();
        return inflater.inflate(R.layout.fragment_food_info, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Create a vector of strings to keep all the ingredients
        Vector<String> ingredientNames = new Vector<String>(3);
        ingredientNames.add(new String("White jasmine rice"));
        ingredientNames.add(new String("chopped pork"));
        ingredientNames.add(new String("mushrooms"));
        ingredientNames.add(new String("celery"));
        ingredientNames.add(new String("cabbage"));
        ingredientNames.add(new String("sesame"));

        // Display the ingredients list in the text view
        TextView ingredientsList = getView().findViewById(R.id.ingredients);

        for (int i = 0; i < ingredientNames.size(); i++) {
            ingredientsList.append(ingredientNames.get(i));

            if (i < ingredientNames.size() - 1) {
                ingredientsList.append(", ");
            }
        }

        // Implement the rating and back navigation buttons
        getView().findViewById(R.id.rating).setOnClickListener((View.OnClickListener) this);
        getView().findViewById(R.id.star1).setOnClickListener(this);
        getView().findViewById(R.id.star2).setOnClickListener(this);
        getView().findViewById(R.id.star3).setOnClickListener(this);
        getView().findViewById(R.id.star4).setOnClickListener(this);
        getView().findViewById(R.id.star5).setOnClickListener(this);

        getView().findViewById(R.id.back).setOnClickListener(this);

        //allow info text to scroll
        ingredientsList.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onClick(View v) {
        if (v == getView().findViewById(R.id.rating)) {
            // The rating button toggles between edit and view modes
            Button editRating = getView().findViewById(R.id.rating);

            if (!editRatingOn) {
                editRating.setText("Submit");
                editRatingOn = true;
                setStarAppearance(); // Change the appearance of the stars to indicate editing mode
            }
            else {
                editRating.setText("Edit");
                editRatingOn = false;
                setStarAppearance(); // Change the appearance of the stars to indicate non-editing mode
            }
        }
        else if (v == getView().findViewById(R.id.star1) && editRatingOn) { // Only edit the star rating in editing mode
            setRating(1);
        }
        else if (v == getView().findViewById(R.id.star2) && editRatingOn) {
            setRating(2);
        }
        else if (v == getView().findViewById(R.id.star3) && editRatingOn) {
            setRating(3);
        }
        else if (v == getView().findViewById(R.id.star4) && editRatingOn) {
            setRating(4);
        }
        else if (v == getView().findViewById(R.id.star5) && editRatingOn) {
            setRating(5);
        }
        else if (v == getView().findViewById(R.id.back)) {
            // Get the fragment manager and transaction
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);

            // Replace the current fragment with whatever came before
            if (fragmentManager.getBackStackEntryCount() > 0) {
                int count = fragmentManager.getBackStackEntryCount();

                if (fragmentManager.getBackStackEntryAt(count - 1).getName() == "MENU")
                    transaction.replace(R.id.nav_host_fragment_activity_main, MenuFragment.class, null);
                else if (fragmentManager.getBackStackEntryAt(count - 1).getName() == "MEAL_INFO")
                    transaction.replace(R.id.nav_host_fragment_activity_main, MealInfo.class, null);

                transaction.commit();
            }
        }
    }

    public void setStarAppearance() {
        int i;
        ImageButton star = new ImageButton(getContext());

        // The current star rating will determine the number of filled or yellow stars
        // depending on whether we are in editing mode or not
        for (i = 0; i < starRating; i++) {
            if (i == 0)
                star.findViewById(R.id.star1);
            else if (i == 1)
                star.findViewById(R.id.star2);
            else if (i == 2)
                star.findViewById(R.id.star3);
            else if (i == 3)
                star.findViewById(R.id.star4);
            else if (i == 4)
                star.findViewById(R.id.star5);

            if (editRatingOn)
                star.setImageResource(R.drawable.filled_star);
            else
                star.setImageResource(R.drawable.sss);

            star.setAdjustViewBounds(true);
            star.setMaxHeight(50);
            star.setMaxWidth(50);
        }

        // The rest of the stars will be gray or blank, depending on whether
        // we are in editing mode or not
        while (i < 5) {
            if (i == 0)
                star.findViewById(R.id.star1);
            else if (i == 1)
                star.findViewById(R.id.star2);
            else if (i == 2)
                star.findViewById(R.id.star3);
            else if (i == 3)
                star.findViewById(R.id.star4);
            else if (i == 4)
                star.findViewById(R.id.star5);

            if (editRatingOn)
                star.setImageResource(R.drawable.blank_star);
            else
                star.setImageResource(R.drawable.gray_star);

            star.setAdjustViewBounds(true);
            star.setMaxHeight(50);
            star.setMaxWidth(50);
        }
    }

    public void setRating(int numStars) {
        starRating = numStars; // Adjust the official star rating
        setStarAppearance(); // Change the appearance of the stars to reflect this
    }
}