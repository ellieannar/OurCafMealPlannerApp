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
        LOCALLY_CRAFTED, VEGETARIAN, SEAFOOD
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
        t.setWidth(100);
        t.setHeight(100);
        t.setTextSize(20);
        t.setGravity(Gravity.CENTER);
        t.setTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
        t.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_corners, null));

        switch (tagType){
            case VEGAN:
                t.setText("vg");
                t.getBackground().setTint(ResourcesCompat.getColor(getResources(), R.color.green, null));
                break;
            case HUMANE:
                t.setText("h");
                t.getBackground().setTint(ResourcesCompat.getColor(getResources(), R.color.lightBlue, null));
                break;
            case FARM_FRESH:
                t.setText("ff");
                t.getBackground().setTint(ResourcesCompat.getColor(getResources(), R.color.pink, null));
                break;
            case GLUTEN_FREE:
                t.setText("gf");
                t.getBackground().setTint(ResourcesCompat.getColor(getResources(), R.color.yellow, null));
                break;
            case LOCALLY_CRAFTED:
                t.setText("lc");
                t.getBackground().setTint(ResourcesCompat.getColor(getResources(), R.color.darkBlue, null));
                break;
            case VEGETARIAN:
                t.setText("v");
                t.getBackground().setTint(ResourcesCompat.getColor(getResources(), R.color.darkGreen, null));
                break;
            case SEAFOOD:
                t.setText("s");
                t.getBackground().setTint(ResourcesCompat.getColor(getResources(), R.color.darkPink, null));
                break;
        }

        tagsLinearLayout.addView(t);

    }


    public String getFoodTitle() {
        return (String) nameOfMealTextView.getText() ;
    }



}
