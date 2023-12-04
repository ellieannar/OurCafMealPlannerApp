package com.example.cafmealplanner.ui.Schedule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.cafmealplanner.R;
import com.example.cafmealplanner.ui.Data.resource;

public class mealButton extends LinearLayout implements View.OnClickListener {


    enum buttonSelection {
        FILLED, EMPTY
    }

    enum dayOfWeek {
        MONDAY(1), TUESDAY(2), WEDNESDAY(3), THURSDAY(4), FRIDAY(5), SATURDAY(6), SUNDAY(7);

        // Custom field to store the integer value
        private final int intValue;

        // Constructor to initialize the integer value
        dayOfWeek(int intValue) {
            this.intValue = intValue;
        }

        // Getter method to retrieve the integer value
        public int toInt() {
            return intValue;
        }
    }

    enum mealTime {
        BREAKFAST, LUNCH, DINNER
    }

    private resource r = new resource();
    private LinearLayout linearLayout;
    private ImageButton button;
    private buttonSelection selected;
    private dayOfWeek day = dayOfWeek.SUNDAY;
    private mealTime meal = mealTime.BREAKFAST;
    private boolean isEditing = false;

    private int assignedID;

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
        //button clicks manager
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getContext());
        lbm.registerReceiver(receiver, new IntentFilter("filter_string"));
    }


    public void setFill(buttonSelection b) {
        selected = b;
        if (isEditing) {
            if (b == buttonSelection.FILLED) {
                button.setColorFilter(getResources().getColor(R.color.green, null));
            } else {
                button.setColorFilter(getResources().getColor(R.color.biolaBlack, null));
            }
        } else {
            if (b == buttonSelection.FILLED) {
                button.setImageResource(R.drawable.baseline_circle_24);
                button.setColorFilter(getResources().getColor(R.color.green, null));
            } else {
                button.setImageResource(R.drawable.baseline_circle_24);
                button.setColorFilter(getResources().getColor(R.color.biolaBlack, null));

            }
        }
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

    public int getDay() {
        return day.toInt();
    }





    @Override
    public void onClick(View view){
        if (!isEditing) {
            //Alert app that more info about specific meal should now be displayed.
            Intent intent = new Intent("filter_string");
            intent.putExtra("day", getDay());
            intent.putExtra("time", getMeal());
            intent.putExtra("audience", "forSchedule");
            // put your all data using put extra

            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
        }  else {
            //they're editing now
            editIcon(view);
        }


    }


    public void editIcon(View view) {
        r.changeMeal(day.ordinal(), meal.ordinal());

        Intent intent = new Intent("filter_string");
        intent.putExtra("id", getAssignedID());
        intent.putExtra("audience", "forScheduleUpdates");
        // put your all data using put extra



        if (selected == buttonSelection.FILLED) {
            selected = buttonSelection.EMPTY;
            intent.putExtra("filled", false);

        } else {
            selected = buttonSelection.FILLED;
            intent.putExtra("filled", true);

        }
        setFill(selected);
        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
    }


    //button clicks
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if (intent != null && intent.getExtras().getString("audience").equals("edit_schedule")) {
                isEditing = !isEditing;

            }
        }


    };


    public void setAssignedID(int i ){
        assignedID = i;
    }

    public int getAssignedID() {
        return assignedID;
    }




}