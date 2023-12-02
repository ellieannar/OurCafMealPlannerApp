package com.example.cafmealplanner.ui.Data;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

public class Meal implements Parcelable {
    ArrayList<FoodItem> mealElements = new ArrayList<FoodItem>();
    String mealTime = "Breakfast";

    String date = "";

    public Meal() {
        super();
    }
    protected Meal(Parcel in) {
        mealElements = in.createTypedArrayList(FoodItem.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(mealElements);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

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

    public String getMealTime() {
        return mealTime;
    }

    public void setMealTime(String m) {
        mealTime = m;
    }

    public void setDate(String d) {
        date = d;
    }

    public String getDate(){
        return date;
    }
}
