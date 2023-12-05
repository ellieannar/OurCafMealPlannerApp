package com.example.cafmealplanner;

import android.os.Bundle;
import android.util.Log;

import com.example.cafmealplanner.ui.Data.Day;
import com.example.cafmealplanner.ui.Data.Meal;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import com.example.cafmealplanner.databinding.ActivityMainBinding;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.CompletableFuture;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;

    public Day d = new Day();
    public ArrayList<Day> weekDays = new ArrayList<>();

    //Months indexed
    private String monthOfYear[] = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};




    @Override
    protected void onCreate(Bundle savedInstanceState) {



        //get current date to display
        Calendar cal = Calendar.getInstance();
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);
        int year = cal.get(Calendar.YEAR);



        Log.d("MainActivity Created", "onCreate: ");

        //Go back to yesterday
        cal.add(Calendar.DAY_OF_YEAR, -1);
        month = cal.get(Calendar.MONTH);
        day = cal.get(Calendar.DAY_OF_MONTH);
        year = cal.get(Calendar.YEAR);

        for (int j = 0; j < 8; j++) {

            //creating new meals
            Meal breakfast = new Meal();
            Meal lunch = new Meal();
            Meal dinner = new Meal();

            String tempDate;
            if (month < 10) {
                tempDate = year + "-0" + (month + 1);
            } else {
                tempDate = year + "-" + (month + 1);
            }
            if (day < 10) {
                tempDate += "-0" + day;
            } else {
                tempDate += "-" + day;
            }


            CompletableFuture<Day> futureDay = d.connectAsync(tempDate);
            futureDay.thenAccept(today -> {
                // store the meals in breakfast, lunch, and dinner
                final Meal tempMeal = today.getMeal("Breakfast");
                for (int i = 0; i < tempMeal.size(); i++) {
                    breakfast.add(tempMeal.get(i));
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
            tempDay.setDayOfWeek(cal.get(Calendar.DAY_OF_WEEK));
            tempDay.setDate(monthOfYear[month] + " " + day + ", " + year);
            breakfast.setDate(monthOfYear[month] + " " + day + ", " + year);
            lunch.setDate(monthOfYear[month] + " " + day + ", " + year);
            dinner.setDate(monthOfYear[month] + " " + day + ", " + year);
            weekDays.add(tempDay);

            // Wait for the asynchronous operation to complete
            try {
                futureDay.get();
            } catch (Exception e) {
                Log.d("Exception thrown", "Error encountered MainActivity line 99");
                e.printStackTrace();
            }

            //increase day by 1
            cal.add(Calendar.DAY_OF_YEAR, 1);
            month = cal.get(Calendar.MONTH);
            day = cal.get(Calendar.DAY_OF_MONTH);
            year = cal.get(Calendar.YEAR);

        }
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_menu, R.id.navigation_schedule, R.id.navigation_profile)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment_activity_main);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(binding.navView, navController);



    }

}