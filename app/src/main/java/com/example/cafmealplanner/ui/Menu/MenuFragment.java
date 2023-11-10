package com.example.cafmealplanner.ui.Menu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import java.util.Calendar;
import java.util.Vector;

import com.example.cafmealplanner.R;
import com.example.cafmealplanner.databinding.FragmentMenuBinding;
import com.example.cafmealplanner.mealView;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private FragmentMenuBinding binding;

    //Local Calendar for current (viewed) date
    private Calendar cal = Calendar.getInstance();

    //Current actual date
    private Calendar now = Calendar.getInstance();

    //Months indexed
    private String monthOfYear[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    //Default on create view
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MenuViewModel menuViewModel =
                new ViewModelProvider(this).get(MenuViewModel.class);
        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }

    //function for when view is created
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //Back day and forward day buttons
        Button backDay = getView().findViewById(R.id.backDayButton);
        Button forwardDay = getView().findViewById(R.id.forwardDayButton);

        //forward and back day onClickListener
        forwardDay.setOnClickListener(this);
        backDay.setOnClickListener(this);

        //get current date to display
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);

        // Display initial date
        TextView label = getView().findViewById(R.id.dateTextView);
        label.setText(monthOfYear[month] + " " + day + ", " + year);

        // views to add meals to
        LinearLayout breakfastLinearLayout = (LinearLayout)getView().findViewById(R.id.breakfastLinearLayout);
        LinearLayout lunchLinearLayout = (LinearLayout)getView().findViewById(R.id.lunchLinearLayout);
        LinearLayout dinnerLinearLayout = (LinearLayout)getView().findViewById(R.id.dinnerLinearLayout);


        LinearLayout breakfastItems = new LinearLayout(getContext());
        breakfastItems.setOrientation(LinearLayout.VERTICAL);

        LinearLayout lunchItems = new LinearLayout(getContext());
        lunchItems.setOrientation(LinearLayout.VERTICAL);

        LinearLayout dinnerItems = new LinearLayout(getContext());
        dinnerItems.setOrientation(LinearLayout.VERTICAL);


        //meals
        Vector<mealView> breakfastMeals = new Vector<mealView>(5);
        Vector<mealView> lunchMeals = new Vector<mealView>(5);
        Vector<mealView> dinnerMeals = new Vector<mealView>(5);

        // Using meals offered by caf on 11/10/2023

        //breakfast items
        mealView temp = new mealView(getContext());
        temp.setMealName("quick oats");
        temp.addTag(mealView.TAG_TYPE.GLUTEN_FREE);
        temp.addTag(mealView.TAG_TYPE.VEGAN);
        breakfastMeals.add(temp);
        temp = new mealView(getContext());
        temp.setMealName("hot breakfast bar");
        temp.addTag(mealView.TAG_TYPE.GLUTEN_FREE);
        temp.addTag(mealView.TAG_TYPE.VEGETARIAN);
        breakfastMeals.add(temp);
        temp = new mealView(getContext());
        temp.setMealName("Biola's broken egg bar");
        temp.addTag(mealView.TAG_TYPE.GLUTEN_FREE);
        temp.addTag(mealView.TAG_TYPE.VEGETARIAN);
        breakfastMeals.add(temp);
        temp = new mealView(getContext());
        temp.setMealName("biscuit and gravy");
        temp.addTag(mealView.TAG_TYPE.FARM_FRESH);
        temp.addTag(mealView.TAG_TYPE.HUMANE);
        breakfastMeals.add(temp);
        temp = new mealView(getContext());
        temp.setMealName("breakfast pastries");
        temp.addTag(mealView.TAG_TYPE.VEGETARIAN);
        temp.addTag(mealView.TAG_TYPE.FARM_FRESH);
        temp.addTag(mealView.TAG_TYPE.LOCALLY_CRAFTED);
        breakfastMeals.add(temp);


        //lunch items
        temp = new mealView(getContext());
        temp.setMealName("clam chowder");
        temp.addTag(mealView.TAG_TYPE.SEAFOOD);
        lunchMeals.add(temp);
        temp = new mealView(getContext());
        temp.setMealName("baked teriyaki chicken leg");
        temp.addTag(mealView.TAG_TYPE.HUMANE);
        lunchMeals.add(temp);
        temp = new mealView(getContext());
        temp.setMealName("Biola's classic daily house-made pizza");
        lunchMeals.add(temp);
        temp = new mealView(getContext());
        temp.setMealName("spinach and artichoke pizza");
        temp.addTag(mealView.TAG_TYPE.VEGETARIAN);
        lunchMeals.add(temp);
        temp = new mealView(getContext());
        temp.setMealName("sugar rush");
        temp.addTag(mealView.TAG_TYPE.VEGETARIAN);
        temp.addTag(mealView.TAG_TYPE.FARM_FRESH);
        lunchMeals.add(temp);

        //dinner items
        temp = new mealView(getContext());
        temp.setMealName("clam chowder");
        temp.addTag(mealView.TAG_TYPE.SEAFOOD);
        dinnerMeals.add(temp);
        temp = new mealView(getContext());
        temp.setMealName("italian beef stew");
        temp.addTag(mealView.TAG_TYPE.HUMANE);
        dinnerMeals.add(temp);
        temp = new mealView(getContext());
        temp.setMealName("classic cheese burger");
        temp.addTag(mealView.TAG_TYPE.HUMANE);
        dinnerMeals.add(temp);
        temp = new mealView(getContext());
        temp.setMealName("Biola's classic daily house-made pizza");
        dinnerMeals.add(temp);
        temp = new mealView(getContext());
        temp.setMealName("sugar rush");
        temp.addTag(mealView.TAG_TYPE.VEGETARIAN);
        temp.addTag(mealView.TAG_TYPE.FARM_FRESH);
        dinnerMeals.add(temp);

        // add meals to view
        for (int i = 0; i < breakfastMeals.size(); i++) {
            breakfastItems.addView(breakfastMeals.get(i));
        }
        for (int i = 0; i < lunchMeals.size(); i++) {
            lunchItems.addView(lunchMeals.get(i));
        }
        for (int i = 0; i < dinnerMeals.size(); i++) {
            dinnerItems.addView(dinnerMeals.get(i));
        }
        breakfastLinearLayout.addView(breakfastItems);
        lunchLinearLayout.addView(lunchItems);
        dinnerLinearLayout.addView(dinnerItems);
    }


    //click handler
    public void onClick(View v) {
        //Date text to be changed
        TextView label = getView().findViewById(R.id.dateTextView);
        int month;
        int day;
        int year;

        //depending on the element clicked (v), perform corresponding action
        if (v == getView().findViewById(R.id.forwardDayButton)) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
            month = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_MONTH);
            year = cal.get(Calendar.YEAR);
            label.setText(monthOfYear[month] + " " + day + ", " + year);
        } else if (v == getView().findViewById(R.id.backDayButton)) {
            //move back one day
            cal.add(Calendar.DAY_OF_YEAR, -1);
            //update month, day year
            month = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_MONTH);
            year = cal.get(Calendar.YEAR);
            //display
            label.setText(monthOfYear[month] + " " + day + ", " + year);
        }


        //check if either arrow should be hidden
        hideArrow();
    }


    //hide arrows so date cannot go out of bounds
    private void hideArrow() {
        if (cal.get(Calendar.DAY_OF_YEAR) < now.get(Calendar.DAY_OF_YEAR)) {
            getView().findViewById(R.id.backDayButton).setVisibility(View.INVISIBLE);
        } else if (cal.get(Calendar.DAY_OF_YEAR) > now.get(Calendar.DAY_OF_YEAR)+5) {
            getView().findViewById(R.id.forwardDayButton).setVisibility(View.INVISIBLE);
        } else {
            getView().findViewById(R.id.backDayButton).setVisibility(View.VISIBLE);
            getView().findViewById(R.id.forwardDayButton).setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}