package com.example.cafmealplanner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;


public class mealView extends FrameLayout {


    private FrameLayout frameLayout;
    private TextView nameOfMealTextView;
    private TextView tagsTextView;


    public mealView(@NonNull Context context) {
        super(context);
        initMealView(context);

    }

    public mealView(@NonNull Context context, AttributeSet attributeSet){
        super(context, attributeSet);
        initMealView(context);

    }

    public mealView(@NonNull Context context, AttributeSet attributeSet, int defStyleAttr){
        super(context, attributeSet, defStyleAttr);
        initMealView(context);

    }

    private void initMealView(Context context) {
        //LayoutParams = LayoutParams(LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LayoutInflater.from(context).inflate(R.layout.meal_view, this, true);
        frameLayout = findViewById(R.id.mealViewFrameLayout);
        nameOfMealTextView = findViewById(R.id.foodTitle);
        tagsTextView = findViewById(R.id.tag);

        //set values
        nameOfMealTextView.setText("Pizza");


        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, // Width
                LayoutParams.WRAP_CONTENT  // Height
        );
        this.setLayoutParams(layoutParams);


    }


    public void setMealName(String name) {
        nameOfMealTextView = findViewById(R.id.foodTitle);
        nameOfMealTextView.setText(name);
    }




}
