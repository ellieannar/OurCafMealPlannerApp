package com.example.cafmealplanner.ui.Profile;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.cafmealplanner.R;

import java.util.Vector;

public class dietaryRestriction extends LinearLayout {

    enum restrictionType {
        GLUTEN_FREE, DAIRY_FREE, VEGETARIAN, VEGAN, SEAFOOD
    }

    private LinearLayout linearLayout;
    private CheckBox restrictionCheckBox;
    private TextView restrictionTextView;

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
        linearLayout = findViewById(R.id.dietaryRestrictionsLinearLayout);
        restrictionCheckBox = findViewById(R.id.restrictionCheckBox);
        restrictionTextView = findViewById(R.id.restrictionTextView);
    }


    public void setRestriction(restrictionType r) {
        switch (r){
            case GLUTEN_FREE:
                restrictionTextView.setText("Gluten Free");
                break;
            case DAIRY_FREE:
                restrictionTextView.setText("Dairy Free");
                break;
            case VEGETARIAN:
                restrictionTextView.setText("Vegetarian");
                break;
            case VEGAN:
                restrictionTextView.setText("Vegan");
                break;
            case SEAFOOD:
                restrictionTextView.setText("No Seafood");
                break;
        }
    }

    public String getRestriction() {
        return (String) restrictionTextView.getText();
    }

    public Boolean restrictionIsEnabled() {
        return restrictionCheckBox.isChecked();
    }

    public void hideCheckbox() {
        restrictionCheckBox.setVisibility(INVISIBLE);
    }

    public void showCheckbox() {
        restrictionCheckBox.setVisibility(VISIBLE);
    }

}
