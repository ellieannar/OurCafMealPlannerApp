package com.example.cafmealplanner.ui.Menu;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.graphics.drawable.Drawable;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cafmealplanner.R;

import java.util.Vector;

public class FoodInfo extends Fragment implements View.OnClickListener {


    boolean editRatingOn = false;

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
        getView().findViewById(R.id.backToMenu).setOnClickListener(this);

        //allow info text to scroll
        ingredientsList.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onClick(View v) {
        if (v == getView().findViewById(R.id.rating)) {
            Button editRating = getView().findViewById(R.id.rating);

            if (!editRatingOn) {
                editRating.setText("Submit");
                editRatingOn = true;
            }
            else {
                editRating.setText("Edit");
                editRatingOn = false;
            }
        }
        /*else if ((v == getView().findViewById(R.id.star1) || v == getView().findViewById(R.id.star2) || v == getView().findViewById(R.id.star3) || v == getView().findViewById(R.id.star4) || v == getView().findViewById(R.id.star5)) && editRatingOn) {

        }*/
        else if (v == getView().findViewById(R.id.backToMenu)) {
            // Create new fragment and transaction
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);

            // Replace whatever is in the fragment_container view with this fragment
            transaction.replace(R.id.nav_host_fragment_activity_main, MenuFragment.class, null);
            // Commit the transaction

            transaction.commit();
        }
    }
}