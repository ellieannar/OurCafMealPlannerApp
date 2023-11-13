package com.example.cafmealplanner.ui.Schedule;

import android.content.Context;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.cafmealplanner.R;

import org.w3c.dom.Text;

import java.util.Vector;

public class mealButton extends LinearLayout implements View.OnClickListener {


    enum buttonSelection {
        FILLED, EMPTY
    }

    enum dayOfWeek {
        SUNDAY, MONDAY, TUESDAY, WEDNESDAY, THURSDAY, FRIDAY, SATURDAY
    }

    enum mealTime {
        BREAKFAST, LUNCH, DINNER
    }

    private LinearLayout linearLayout;
    private ImageButton button;
    private buttonSelection selected;
    private dayOfWeek day = dayOfWeek.SUNDAY;
    private mealTime meal = mealTime.BREAKFAST;

    public mealButton(@NonNull Context context) {
        super(context);
        initmealButton(context);

    }

    public mealButton(@NonNull Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initmealButton(context);

    }

    public mealButton(@NonNull Context context, AttributeSet attributeSet, int defStyleAttr) {
        super(context, attributeSet, defStyleAttr);
        initmealButton(context);

    }

    private void initmealButton(Context context) {
        LayoutInflater.from(context).inflate(R.layout.meal_button, this, true);
        linearLayout = findViewById(R.id.mealButtonLinearLayout);
        button = findViewById(R.id.imgButton);

        button.setOnClickListener(this);
    }


    public void setFill(buttonSelection b) {
        selected = b;
    }

    public void setMeal (mealTime m) {
        meal = m;
    }

    public void setDay (dayOfWeek d) {
        day = d;
    }

    public Boolean getFill() {
        if (selected == buttonSelection.FILLED) {
            return true;
        }
        return false;
    }

    public String getMeal() {
        return meal.toString();
    }

    public String getDay() {
        return day.toString();
    }


    @Override
    public void onClick(View view){

        //Alert app that more info about specific meal should now be displayed.
        Intent intent = new Intent("filter_string");
        intent.putExtra("day", getDay());
        intent.putExtra("time", getMeal());
        intent.putExtra("audience", "forSchedule");
        // put your all data using put extra

        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
    }



}