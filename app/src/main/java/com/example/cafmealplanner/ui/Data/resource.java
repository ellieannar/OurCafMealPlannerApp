package com.example.cafmealplanner.ui.Data;


import android.util.Log;

public class resource {

    private static boolean[][] plannedMeals = new boolean[7][3];
    private static int selectedMeals = 0;
    public resource() {
        /*
        //set up planned meals - initialize all to true
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                plannedMeals[i][j] = true;
            }
        }

         */


    }

    //func for changing the status of a meal
    public static void changeMeal(int day, int meal) {
        Log.d("EDITING", "editing: " + day + " " + meal);
        if (plannedMeals[day][meal]) {
            selectedMeals--;
            plannedMeals[day][meal] = false;
        } else {
            selectedMeals++;
            plannedMeals[day][meal] = true;
        }
    }

    //func for getting the status of a meal
    public boolean getMealStatus(int day, int meal) {
        return plannedMeals[day][meal];
    }

}
