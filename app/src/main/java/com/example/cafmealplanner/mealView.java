package com.example.cafmealplanner;

import android.content.Context;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.res.ResourcesCompat;

import java.util.ArrayList;


public class mealView extends FrameLayout {


    public enum TAG_TYPE {
        VEGAN, GLUTEN_FREE, HUMANE, FARM_FRESH,
        LOCALLY_CRAFTED
    }


    private FrameLayout frameLayout;
    private TextView nameOfMealTextView;
    private ArrayList<TextView> tagsTextViews;
    private LinearLayout tagsLinearLayout;


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
        tagsLinearLayout = findViewById(R.id.tagsLinearLayout);



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

    public void addTag(TAG_TYPE tagType) {
        TextView t = new TextView(getContext());
        t.setWidth(80);
        t.setHeight(80);
        t.setGravity(Gravity.CENTER);
        t.setTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
        t.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_corners, null));

        switch (tagType){
            case VEGAN:
                t.setText("v");
                t.setTextSize(20);
                t.getBackground().setTint(ResourcesCompat.getColor(getResources(), R.color.green, null));
                break;
            case HUMANE:
                t.setText("H");
                t.setTextSize(20);
                t.getBackground().setTint(ResourcesCompat.getColor(getResources(), R.color.lightBlue, null));
                break;
        }

        tagsLinearLayout.addView(t);

    }




}
