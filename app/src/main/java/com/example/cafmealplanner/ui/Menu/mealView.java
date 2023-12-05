package com.example.cafmealplanner.ui.Menu;

import android.content.Context;
import android.content.Intent;

import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.cafmealplanner.R;
import com.example.cafmealplanner.ui.Data.FoodItem;


public class mealView extends FrameLayout implements View.OnClickListener {


    private TextView nameOfMealTextView;
    private LinearLayout tagsLinearLayout;
    private Button infoButton;
    private static TextView circleIndicator;

    //my food item
    FoodItem f = new FoodItem();

    // String to be populated by either MenuFragment or MealInfo
    public String parentActivity = "back_nav_support";

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

        LayoutInflater.from(context).inflate(R.layout.meal_view, this, true);
        nameOfMealTextView = findViewById(R.id.foodTitle);
        tagsLinearLayout = findViewById(R.id.tagsLinearLayout);
        circleIndicator = findViewById(R.id.cirlceIndicator);


        LayoutParams layoutParams = new LayoutParams(
                LayoutParams.MATCH_PARENT, // Width
                LayoutParams.WRAP_CONTENT  // Height
        );
        this.setLayoutParams(layoutParams);

        infoButton = findViewById(R.id.moreInfoButton);

        infoButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View view){

        //Alert app that more info about specific meal should now be displayed.
        Intent intent = new Intent("filter_string");
        intent.putExtra("mealName", nameOfMealTextView.getText().toString());

        // put your all data using put extra
        intent.putExtra("audience", "forMenu");
        intent.putExtra("title", f.getTitle());
        intent.putExtra("desc", f.getDescription());
        intent.putExtra("loc", f.getLocation());
        intent.putExtra("rest", f.getRestrictions());



        LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
    }

    //allows caller to set the meal name
    public void setMealName(String name) {
        nameOfMealTextView.setTextSize(20);
        nameOfMealTextView = findViewById(R.id.foodTitle);
        nameOfMealTextView.setText(name);
        f.setTitle(name);
    }

    public void setOtherInfo(String desc, String loc) {
        f.setDescription(desc);
        f.setLocation(loc);
    }


    //allows caller to create a tag for meal item
    public void addTag(FoodItem.restrictionType tagType) {
        TextView t = new TextView(getContext());
        t.setWidth(100);
        t.setHeight(100);
        t.setTextSize(20);
        t.setGravity(Gravity.CENTER);
        t.setTextColor(ResourcesCompat.getColor(getResources(), R.color.white, null));
        t.setBackground(ResourcesCompat.getDrawable(getResources(), R.drawable.rounded_corners, null));
        f.addRestriction(tagType);
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

    //returns food title to caller
    public String getFoodTitle() {
        return (String) nameOfMealTextView.getText() ;
    }


    public int getMyId() {
        return infoButton.getId();
    }


    public static void setIndicator(int s, Context c) {
        circleIndicator.setText("");
        circleIndicator.setTextSize(10);
        circleIndicator.setBackground(ContextCompat.getDrawable(c, R.drawable.rounded_corners));
        circleIndicator.getBackground().setTint(ResourcesCompat.getColor(c.getResources(), s, null));

    }

    public static void indicatorVisibility(Boolean b) {
        if (b) {
            circleIndicator.setVisibility(VISIBLE);
        } else {
            circleIndicator.setVisibility(INVISIBLE);
        }
    }


}
