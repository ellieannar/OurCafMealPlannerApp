package com.example.cafmealplanner.ui.Data;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.util.ArrayList;



import java.util.EnumSet;
import java.util.List;


public class FoodItem implements Parcelable {

    protected FoodItem(Parcel in) {
        //restrictions = new ArrayList<>();
        readEnumList(in, restrictions, restrictionType.class);
    }

    public static final Creator<FoodItem> CREATOR = new Creator<FoodItem>() {
        @Override
        public FoodItem createFromParcel(Parcel in) {
            return new FoodItem(in);
        }

        @Override
        public FoodItem[] newArray(int size) {
            return new FoodItem[size];
        }
    };



    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {

        writeEnumList(parcel, restrictions);
    }

    public enum restrictionType {
        VEGAN, GLUTEN_FREE, HUMANE, FARM_FRESH, LOCALLY_CRAFTED, VEGETARIAN, SEAFOOD
    }

    // Each food item stores the title, desc, restrictions, and location
    String location;
    ArrayList<restrictionType> restrictions = new ArrayList<>();
    String title;
    String description;

    public FoodItem () {
        super();
    }

    private <E extends Enum<E>> void writeEnumList(Parcel dest, List<E> list) {
        dest.writeInt(list.size());
        for (E e : list) {
            dest.writeString(e.name());
        }
    }

    private <E extends Enum<E>> void readEnumList(Parcel in, List<E> list, Class<E> enumClass) {
        int size = in.readInt();
        EnumSet<E> enumSet = EnumSet.noneOf(enumClass);
        for (int i = 0; i < size; i++) {
            enumSet.add(Enum.valueOf(enumClass, in.readString()));
        }
        list.addAll(enumSet);
    }

    public void addRestriction(String r) {
        switch (r) {
            case "Vegetarian: Contains no meat, fish, poultry, shellfish or products derived from these sources but may contain dairy or eggs":
                restrictions.add(restrictionType.VEGETARIAN);
                break;
            case "Vegan: Contains absolutely no animal or dairy products.":
                restrictions.add(restrictionType.VEGAN);
                break;
            case "Made without Gluten-Containing Ingredients: does not contain ingredients that are sources of gluten, but is prepared in an open kitchen where gluten is present.":
                restrictions.add(restrictionType.GLUTEN_FREE);
                break;
            case "Farm to Fork: Contains seasonal, minimally processed ingredients from a local farm, ranch, or fishing boat.":
                restrictions.add(restrictionType.FARM_FRESH);
                break;
            case "Locally Crafted: Contains products crafted by a small, locally owned food business using socially and/or environmentally responsible practices.":
                restrictions.add(restrictionType.LOCALLY_CRAFTED);
                break;
            case "Seafood Watch: Contains seafood that meets the Monterey Bay Aquarium's Seafood Watch guidelines for commercial buyers.":
                restrictions.add(restrictionType.SEAFOOD);
                break;
            case "Humane: Contains humanely raised meat, poultry, or eggs. Must be certified by a credible third-party animal welfare organization.":
                restrictions.add(restrictionType.HUMANE);
                break;
            default:
                break;
        }
    }

    public void addRestriction(restrictionType r) {
        restrictions.add(r);
    }
    public void setLocation(String l) {
        location = l;
    }
    public void setTitle(String t) {
        title = t;
    }
    public void setDescription(String d) {
        description = d;
    }
    public void addRestrictions(ArrayList<restrictionType> r) {
        restrictions = r;
    }

    public ArrayList<String> getRestrictionStrings() {
        ArrayList<String> temp = new ArrayList<>();
        for (int i = 0; i < restrictions.size(); i++) {
            temp.add(restrictions.get(i).name());
        }

        return temp;
    }


    public String getLocation() {
        return location;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<restrictionType> getRestrictions() {
        return restrictions;
    }
}

