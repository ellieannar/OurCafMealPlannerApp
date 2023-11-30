package com.example.cafmealplanner.ui.Menu;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
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
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Vector;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

import com.example.cafmealplanner.R;
import com.example.cafmealplanner.databinding.FragmentMenuBinding;
import com.example.cafmealplanner.ui.Data.Day;
import com.example.cafmealplanner.ui.Data.Meal;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private FragmentMenuBinding binding;

    //Local Calendar for current (viewed) date
    private Calendar cal = Calendar.getInstance();

    //Current actual date
    private Calendar now = Calendar.getInstance();

    //Months indexed
    private String monthOfYear[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    //Track all the more info buttons we will have
    Vector<mealView> breakfastMeals;
    Vector<mealView> lunchMeals;
    Vector<mealView> dinnerMeals;

    //Track all days in one arraylist
    ArrayList<Day> weekDays = new ArrayList<>();


    //Default on create view
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        //MenuViewModel menuViewModel =
                //new ViewModelProvider(this).get(MenuViewModel.class);
        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        container.removeAllViews();

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("MENU");
        transaction.commit();

        int count = manager.getBackStackEntryCount();
        if (count > 0) {
            Log.d("MENU", "Attempting to add the menu page to the back stack. The top of the stack is now " + manager.getBackStackEntryAt(count - 1).getName());
        }

        //button clicks manager
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getContext());
        lbm.registerReceiver(receiver, new IntentFilter("filter_string"));

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

        // views to add meal subview meals to
        LinearLayout breakfastLinearLayout = (LinearLayout)getView().findViewById(R.id.breakfastLinearLayout);
        LinearLayout lunchLinearLayout = (LinearLayout)getView().findViewById(R.id.lunchLinearLayout);
        LinearLayout dinnerLinearLayout = (LinearLayout)getView().findViewById(R.id.dinnerLinearLayout);

        //subviews to add each meal to
        LinearLayout breakfastItems = new LinearLayout(getContext());
        breakfastItems.setOrientation(LinearLayout.VERTICAL);

        LinearLayout lunchItems = new LinearLayout(getContext());
        lunchItems.setOrientation(LinearLayout.VERTICAL);

        LinearLayout dinnerItems = new LinearLayout(getContext());
        dinnerItems.setOrientation(LinearLayout.VERTICAL);

        //allow detection of breakfast buttons
        breakfastItems.setOnClickListener(this);
        breakfastLinearLayout.setOnClickListener(this);

        //vector of meal items
        breakfastMeals = new Vector<>(5);
        lunchMeals = new Vector<>(5);
        dinnerMeals = new Vector<>(5);

        // creating new day
        Day d = new Day();



        cal.add(Calendar.DAY_OF_YEAR, -1);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        year = cal.get(Calendar.YEAR);


        // Call the asynchronous method and handle the result

        for (int j = 0; j < 6; j++) {

            //creating new meals
            Meal breakfast = new Meal();
            Meal lunch = new Meal();
            Meal dinner = new Meal();

            CompletableFuture<Day> futureDay = d.connectAsync(year + "-" + (month+1) + "-" + day);
            Log.d("Date", "onViewCreated: " + year + "-" + (month+1) + "-" + day);
            futureDay.thenAccept(today -> {
                // store the meals in breakfast, lunch, and dinner
                final Meal tempMeal = today.getMeal("Breakfast");
                for (int i = 0; i < tempMeal.size(); i++) {
                    breakfast.add(tempMeal.get(i));
                    Log.d("Breakfast", "Adding breakfast " + tempMeal.get(i).getTitle());
                }


                final Meal tempMealTwo = today.getMeal("Lunch");
                for (int i = 0; i < tempMealTwo.size(); i++) {
                    lunch.add(tempMealTwo.get(i));
                }

                final Meal tempMealThree = today.getMeal("Dinner");
                for (int i = 0; i < tempMealThree.size(); i++) {
                    dinner.add(tempMealThree.get(i));
                }

            });

            Day tempDay = new Day(breakfast, lunch, dinner);
            weekDays.add(tempDay);

            // Wait for the asynchronous operation to complete
            try {
                futureDay.get();
            } catch (Exception e) {
                Log.d("Exception thrown", "Error encountered MenuFragment line 174");
                e.printStackTrace();
            }



            cal.add(Calendar.DAY_OF_YEAR, 1);
            month = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_MONTH);
            year = cal.get(Calendar.YEAR);

            Log.d("Count", "" + j);
        }


        Log.d("Size", "onViewCreated: " + weekDays.get(1).getMeal("Breakfast").size());
        //get & add breakfast items to view
        for (int i = 0; i < weekDays.get(0).getMeal("Breakfast").size(); i++) {
            mealView tempMealView = new mealView(getContext());
            //Log.d("TAG", "onViewCreated: Made it here");
            tempMealView.setMealName(weekDays.get(0).getMeal("Breakfast").get(i).getTitle());
            breakfastMeals.add(tempMealView);
        }

        //get & add lunch items to view
        for (int i = 0; i < weekDays.get(0).getMeal("Lunch").size(); i++) {
            mealView tempMealView = new mealView(getContext());
            tempMealView.setMealName(weekDays.get(0).getMeal("Lunch").get(i).getTitle());
            lunchMeals.add(tempMealView);
        }

        //get & add dinner items to view
        for (int i = 0; i < weekDays.get(0).getMeal("Dinner").size(); i++) {
            mealView tempMealView = new mealView(getContext());
            tempMealView.setMealName(weekDays.get(0).getMeal("Dinner").get(i).getTitle());
            dinnerMeals.add(tempMealView);
        }



        // add meals to view
        for (int i = 0; i < breakfastMeals.size(); i++) {
            breakfastItems.addView(breakfastMeals.get(i));
            breakfastMeals.get(i).setOnClickListener(this);
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


    //button clicks
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getExtras().getString("audience").equals("forMenu")) {
                displayFoodInfo();
            }
        }
    };

    private void displayFoodInfo() {

        /*
        // Create new fragment and transaction
        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setReorderingAllowed(true);

        // Replace whatever is in the fragment_container view with this fragment
        transaction.replace(R.id.nav_host_fragment_activity_main, FoodInfo.class, null);

        // Commit the transaction
        transaction.commit();

         */

        // Ensure activity is properly initialized
        if (getActivity() != null) {
            // Create new fragment and transaction
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);

            // Replace whatever is in the fragment_container view with this fragment
            transaction.replace(R.id.nav_host_fragment_activity_main, FoodInfo.class, null);

            // Commit the transaction
            transaction.commit();
        } else {
            Log.d("NAVIGATION", "for some reason getactivity is null");
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}