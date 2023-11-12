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

import org.w3c.dom.Text;

import java.util.Vector;

public class favoriteMeal extends LinearLayout {


    private LinearLayout linearLayout;
    private TextView mealName;
    private CheckBox favoriteCheckbox;

    public favoriteMeal(@NonNull Context context) {
        super(context);
        initfavoriteMeal(context);

    }

    public favoriteMeal(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initfavoriteMeal(context);

    }

    public favoriteMeal(@NonNull Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        initfavoriteMeal(context);

    }

    private void initfavoriteMeal(Context context) {
        LayoutInflater.from(context).inflate(R.layout.favorite_meal, this, true);
        linearLayout = findViewById(R.id.favoriteMeal);

        mealName = findViewById(R.id.favoriteMealText);
        favoriteCheckbox = findViewById(R.id.favoriteCheckbox);

    }

    public void setMealName(String s) {
        mealName.setText(s);
    }

    public String getMealName() {
        return mealName.getText().toString();
    }

    public Boolean isChecked() {
        return favoriteCheckbox.isChecked();
    }

    public void hideCheckbox() {
        favoriteCheckbox.setVisibility(INVISIBLE);
    }

    public void showCheckbox() {
        favoriteCheckbox.setVisibility(VISIBLE);
    }


}