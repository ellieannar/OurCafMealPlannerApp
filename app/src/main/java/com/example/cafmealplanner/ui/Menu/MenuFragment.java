package com.example.cafmealplanner.ui.Menu;



import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;

import com.example.cafmealplanner.MainActivity;
import com.example.cafmealplanner.R;
import com.example.cafmealplanner.databinding.FragmentMenuBinding;
import com.example.cafmealplanner.ui.Data.Day;
import com.example.cafmealplanner.ui.Data.FoodItem;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private FragmentMenuBinding binding;

    //Local Calendar for current (viewed) date
    private Calendar cal = Calendar.getInstance();

    //Current actual date
    private Calendar now = Calendar.getInstance();

    //Months indexed
    private String monthOfYear[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};

    //keeps track of which day is displayed
    Integer whichDay = 1;


    //Track all the more info buttons we will have
    Vector<mealView> breakfastMeals;
    Vector<mealView> lunchMeals;
    Vector<mealView> dinnerMeals;

    //Linear layouts
    static LinearLayout breakfastLinearLayout;
    static LinearLayout lunchLinearLayout;
    static LinearLayout dinnerLinearLayout;


    //Track all days in one arraylist - get from MainActivity
    static ArrayList<Day> weekDays = new ArrayList<>();


    //Default on create view
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        container.removeAllViews();

        //Navigation
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

        MainActivity activity = (MainActivity) getActivity();

         weekDays = activity.weekDays;

        ConnectivityManager cm =
                (ConnectivityManager) requireActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        if (netInfo == null || !netInfo.isConnectedOrConnecting()) {
            Toast.makeText(getActivity(), "No internet connection",
                    Toast.LENGTH_LONG).show();

        }


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



        breakfastLinearLayout = getView().findViewById(R.id.breakfastLinearLayout);
        lunchLinearLayout = getView().findViewById(R.id.lunchLinearLayout);
        dinnerLinearLayout = getView().findViewById(R.id.dinnerLinearLayout);


        //subviews to add each meal to
        LinearLayout breakfastItems = new LinearLayout(getContext());
        breakfastItems.setOrientation(LinearLayout.VERTICAL);

        LinearLayout lunchItems = new LinearLayout(getContext());
        lunchItems.setOrientation(LinearLayout.VERTICAL);

        LinearLayout dinnerItems = new LinearLayout(getContext());
        dinnerItems.setOrientation(LinearLayout.VERTICAL);

        //allow detection of breakfast buttons
        //breakfastItems.setOnClickListener(this);
        //breakfastLinearLayout.setOnClickListener(this);

        //vector of meal items
        breakfastMeals = new Vector<>(5);
        lunchMeals = new Vector<>(5);
        dinnerMeals = new Vector<>(5);



        cal.add(Calendar.DAY_OF_YEAR, -1);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        year = cal.get(Calendar.YEAR);

        displayDay(1);



    }

    public void displayDay(int d) {

        //keep track of preferences
        SharedPreferences sp = getActivity().getSharedPreferences("profileSharedData", Context.MODE_PRIVATE);
        // Create a mutable copy of the set
        Set<String> favoriteMeals = new HashSet<>(sp.getStringSet("FAVORITE_MEALS", Collections.emptySet()));
        Set<String> dietaryRestrictions = new HashSet<>(sp.getStringSet("DIETARY_RESTRICTIONS", Collections.emptySet()));

        for(String x : dietaryRestrictions) {
            switch (x) {
                case "Gluten Free":
                    dietaryRestrictions.remove(x);
                    dietaryRestrictions.add("GLUTEN_FREE");
                    break;
                case "Vegan":
                    dietaryRestrictions.remove(x);
                    dietaryRestrictions.add("VEGAN");
                    break;
                case "Vegetarian":
                    dietaryRestrictions.remove(x);
                    dietaryRestrictions.add("VEGETARIAN");
                    break;
                case "Diary Free":
                    dietaryRestrictions.remove(x);
                    dietaryRestrictions.add("DAIRY_FREE");
                    break;
                case "No Seafood":
                    dietaryRestrictions.remove(x);
                    dietaryRestrictions.add("SEAFOOD");
                    break;
            }

        }

        for (String x : dietaryRestrictions) {
            Log.d("Restriction", "displayDay: " + x);
        }

        whichDay = d;

        breakfastLinearLayout.removeAllViews();
        lunchLinearLayout.removeAllViews();
        dinnerLinearLayout.removeAllViews();

        //vector of meal items
        breakfastMeals = new Vector<>(5);
        lunchMeals = new Vector<>(5);
        dinnerMeals = new Vector<>(5);

        //subviews to add each meal to
        LinearLayout breakfastItems = new LinearLayout(getContext());
        breakfastItems.setOrientation(LinearLayout.VERTICAL);

        LinearLayout lunchItems = new LinearLayout(getContext());
        lunchItems.setOrientation(LinearLayout.VERTICAL);

        LinearLayout dinnerItems = new LinearLayout(getContext());
        dinnerItems.setOrientation(LinearLayout.VERTICAL);


        //get & add breakfast items to view
        for (int i = 0; i < weekDays.get(whichDay).getMeal("Breakfast").size(); i++) {
            mealView tempMealView = new mealView(getContext());
            mealView.indicatorVisibility(false);
            if (favoriteMeals.contains(weekDays.get(whichDay).getMeal("Breakfast").get(i).getTitle())) {
                mealView.indicatorVisibility(true);
                mealView.setIndicator(R.color.green, getContext());
            }
            for (int j = 0; j < weekDays.get(whichDay).getMeal("Breakfast").get(i).getRestrictions().size(); j++) {
                tempMealView.addTag(weekDays.get(whichDay).getMeal("Breakfast").get(i).getRestrictions().get(j));

            }

            for (String x : dietaryRestrictions) {
                if (!weekDays.get(whichDay).getMeal("Breakfast").get(i).getRestrictionStrings().contains(x) && !x.equals("SEAFOOD")) {
                    mealView.indicatorVisibility(true);
                    mealView.setIndicator(R.color.biolaRed, getContext());
                } else if (x.equals("SEAFOOD") && weekDays.get(whichDay).getMeal("Breakfast").get(i).getRestrictionStrings().contains(x)) {
                    mealView.indicatorVisibility(true);
                    mealView.setIndicator(R.color.biolaRed, getContext());
                }
            }
            tempMealView.setMealName(weekDays.get(whichDay).getMeal("Breakfast").get(i).getTitle());
            tempMealView.setOtherInfo(weekDays.get(whichDay).getMeal("Breakfast").get(i).getDescription(), weekDays.get(whichDay).getMeal("Breakfast").get(i).getLocation());


            breakfastMeals.add(tempMealView);

        }

        //get & add lunch items to view
        for (int i = 0; i < weekDays.get(whichDay).getMeal("Lunch").size(); i++) {
            mealView tempMealView = new mealView(getContext());
            mealView.indicatorVisibility(false);
            if (favoriteMeals.contains(weekDays.get(whichDay).getMeal("Lunch").get(i).getTitle())) {
                mealView.indicatorVisibility(true);
                mealView.setIndicator(R.color.green, getContext());
            }

            for (int j = 0; j < weekDays.get(whichDay).getMeal("Lunch").get(i).getRestrictions().size(); j++) {
                tempMealView.addTag(weekDays.get(whichDay).getMeal("Lunch").get(i).getRestrictions().get(j));
            }

            for (String x : dietaryRestrictions) {
                if (!weekDays.get(whichDay).getMeal("Lunch").get(i).getRestrictionStrings().contains(x) && !x.equals("SEAFOOD")) {
                    mealView.indicatorVisibility(true);
                    mealView.setIndicator(R.color.biolaRed, getContext());
                } else if (x.equals("SEAFOOD") && weekDays.get(whichDay).getMeal("Lunch").get(i).getRestrictionStrings().contains(x)) {
                    mealView.indicatorVisibility(true);
                    mealView.setIndicator(R.color.biolaRed, getContext());
                }
            }
            tempMealView.setMealName(weekDays.get(whichDay).getMeal("Lunch").get(i).getTitle());
            tempMealView.setOtherInfo(weekDays.get(whichDay).getMeal("Lunch").get(i).getDescription(), weekDays.get(whichDay).getMeal("Lunch").get(i).getLocation());
            lunchMeals.add(tempMealView);
        }

        //get & add dinner items to view
        for (int i = 0; i < weekDays.get(whichDay).getMeal("Dinner").size(); i++) {
            mealView tempMealView = new mealView(getContext());
            mealView.indicatorVisibility(false);
            if (favoriteMeals.contains(weekDays.get(whichDay).getMeal("Dinner").get(i).getTitle())) {
                mealView.indicatorVisibility(true);
                mealView.setIndicator(R.color.green, getContext());
            }

            for (int j = 0; j < weekDays.get(whichDay).getMeal("Dinner").get(i).getRestrictions().size(); j++) {
                tempMealView.addTag(weekDays.get(whichDay).getMeal("Dinner").get(i).getRestrictions().get(j));
            }

            for (String x : dietaryRestrictions) {
                if (!weekDays.get(whichDay).getMeal("Dinner").get(i).getRestrictionStrings().contains(x) && !x.equals("SEAFOOD")) {
                    mealView.indicatorVisibility(true);
                    mealView.setIndicator(R.color.biolaRed, getContext());
                } else if (x.equals("SEAFOOD") && weekDays.get(whichDay).getMeal("Dinner").get(i).getRestrictionStrings().contains(x)) {
                    mealView.indicatorVisibility(true);
                    mealView.setIndicator(R.color.biolaRed, getContext());
                }
            }
            tempMealView.setMealName(weekDays.get(whichDay).getMeal("Dinner").get(i).getTitle());
            tempMealView.setOtherInfo(weekDays.get(whichDay).getMeal("Dinner").get(i).getDescription(), weekDays.get(whichDay).getMeal("Dinner").get(i).getLocation());
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
            whichDay++;
            displayDay(whichDay);
        } else if (v == getView().findViewById(R.id.backDayButton)) {
            //move back one day
            cal.add(Calendar.DAY_OF_YEAR, -1);
            //update month, day year
            month = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_MONTH);
            year = cal.get(Calendar.YEAR);
            //display
            label.setText(monthOfYear[month] + " " + day + ", " + year);
            whichDay--;
            displayDay(whichDay);
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
                FoodItem f = new FoodItem();
                if (intent.getExtras().getString("title") != null) {
                    f.setTitle(intent.getExtras().getString("title"));
                }
                if (intent.getExtras().getString("desc") != null) {
                    f.setDescription(intent.getExtras().getString("desc"));
                }
                if (intent.getExtras().getString("loc") != null) {
                    f.setLocation(intent.getExtras().getString("loc"));
                }
                if (intent.getExtras().getSerializable("rest") != null) {
                    f.addRestrictions((ArrayList<FoodItem.restrictionType>) intent.getExtras().getSerializable("rest"));
                }

                displayFoodInfo(f);
            }
        }
    };



    private void displayFoodInfo(FoodItem f) {


        // Ensure activity is properly initialized
        if (getActivity() != null) {
            // Create new fragment and transaction
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);
            Bundle b = new Bundle();
            b.putString("loc" , f.getLocation());
            b.putString("title" , f.getTitle());
            b.putString("desc", f.getDescription());
            b.putParcelable("rest", f);
            Log.d("Err here", "displayFoodInfo: ");
            // Replace whatever is in the fragment_container view with this fragment
            transaction.replace(R.id.nav_host_fragment_activity_main, FoodInfo.class, b);

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