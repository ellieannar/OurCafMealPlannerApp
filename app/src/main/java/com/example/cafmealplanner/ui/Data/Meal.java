package com.example.cafmealplanner.ui.Data;

import java.util.ArrayList;

public class Meal{
    ArrayList<FoodItem> mealElements = new ArrayList<FoodItem>();

    public int size() {
        return mealElements.size();
    }

    public void add(FoodItem f) {
        mealElements.add(f);
    }

    public FoodItem get(int i) {
        return mealElements.get(i);
    }

    public void clear() {
        mealElements.clear();
    }
}
