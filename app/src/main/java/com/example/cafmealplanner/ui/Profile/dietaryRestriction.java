package com.example.cafmealplanner.ui.Profile;

import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatCheckBox;

import com.example.cafmealplanner.R;

import java.util.Vector;

public class dietaryRestriction extends LinearLayout {

    enum restrictionType {
        GLUTEN_FREE, DAIRY_FREE, VEGETARIAN, VEGAN, SEAFOOD
    }

    private LinearLayout linearLayout;
    private AppCompatCheckBox restrictionCheckBox;
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
        LayoutInflater.from(context).inflate(R.layout.dietary_restriction, this, true);
        linearLayout = findViewById(R.id.dietaryRestrictionsLinearLayout);
        restrictionCheckBox = findViewById(R.id.restrictionCheckBox);
        restrictionTextView = findViewById(R.id.restrictionTextView);
       /*
        ColorStateList colorStateList = new ColorStateList(
                new int[][] {
                        new int[] { -android.R.attr.checked }, // unchecked
                        new int[] {  android.R.attr.checked }  // checked
                },
                new int[] {
                        R.color.biolaBlack,
                        R.color.biolaRed
                }
        );
        restrictionCheckBox.setButtonTintList(colorStateList);

        */
    }


    // funtion that can be called to set the restriction type
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

    //return the restriction type
    public String getRestriction() {
        return (String) restrictionTextView.getText();
    }
    public void setChecked(Boolean b) {
        if (b) {
            restrictionCheckBox.setChecked(true);
        } else {
            restrictionCheckBox.setChecked(false);
        }
    }

    // return if the checkbox is selected or not
    public Boolean restrictionIsEnabled() {
        return restrictionCheckBox.isChecked();
    }

    //allows caller to hide checkbox if desired - making non-editable
    public void hideCheckbox() {
        restrictionCheckBox.setVisibility(INVISIBLE);
    }
    //allows caller to show checkbox - enable editing
    public void showCheckbox() {
        restrictionCheckBox.setVisibility(VISIBLE);
    }

}
