package com.example.cafmealplanner.ui.Data;

import java.util.ArrayList;


public class FoodItem {

    enum restrictionType {
        VEGAN, GLUTEN_FREE, HUMANE, FARM_FRESH, LOCALLY_CRAFTED, VEGETARIAN, SEAFOOD
    }

    // Each food item stores the title, desc, restrictions, and location
    String location;
    ArrayList<restrictionType> restrictions = new ArrayList<restrictionType>();
    String title;
    String description;

    FoodItem () {
        super();
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
}