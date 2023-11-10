package com.example.cafmealplanner.ui.Profile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;

import com.example.cafmealplanner.R;

import java.util.Vector;

public class dietaryRestriction extends FrameLayout {

    enum restrictionType {
        GLUTEN_FREE, DAIRY_FREE, VEGETARIAN, VEGAN, SEAFOOD
    }

    private FrameLayout frameLayout;
    private CheckBox restriction;
    private Vector<String> allDietaryRestrictions;

    public dietaryRestriction(@NonNull Context context) {
        super(context);
        initDietaryRestriction(context);

    }

    public dietaryRestriction(@NonNull Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        initDietaryRestriction(context);

    }

    public dietaryRestriction(@NonNull Context context, AttributeSet attributeSet, int defStyleAttr){
        super(context, attributeSet, defStyleAttr);
        initDietaryRestriction(context);

    }

    private void initDietaryRestriction(Context context) {
        //LayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutInflater.from(context).inflate(R.layout.dietary_restriction, this, true);
        frameLayout = findViewById(R.id.dietaryRestrictionFrameLayout);
        restriction = findViewById(R.id.singleRestriction);

    }


    private void addRestriction(restrictionType t) {
        switch (t){
            case GLUTEN_FREE:
                allDietaryRestrictions.add("Gluten Free");
                break;
            case DAIRY_FREE:
                allDietaryRestrictions.add("Dairy Free");
                break;
            case VEGETARIAN:
                allDietaryRestrictions.add("Vegetarian");
                break;
            case VEGAN:
                allDietaryRestrictions.add("Vegan");
                break;
            case SEAFOOD:
                allDietaryRestrictions.add("Seafood");
                break;
        }


    }

    public void setRestriction(restrictionType r) {
        switch (r){
            case GLUTEN_FREE:
                restriction.setText("Gluten Free");
                break;
            case DAIRY_FREE:
                restriction.setText("Dairy Free");
                break;
            case VEGETARIAN:
                restriction.setText("Vegetarian");
                break;
            case VEGAN:
                restriction.setText("Vegan");
                break;
            case SEAFOOD:
                restriction.setText("No Seafood");
                break;
        }
    }

}
