package com.example.cafmealplanner.ui.Schedule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.cafmealplanner.R;
import com.example.cafmealplanner.databinding.FragmentMealInfoBinding;
import com.example.cafmealplanner.databinding.FragmentScheduleBinding;
import com.example.cafmealplanner.ui.Data.Meal;
import com.example.cafmealplanner.ui.Menu.FoodInfo;
import com.example.cafmealplanner.ui.Menu.mealView;

import android.widget.ImageButton;
import android.content.Intent;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Vector;


public class MealInfo extends Fragment implements View.OnClickListener {

    public static MealInfo newInstance() {
        return new MealInfo();
    }

    private MealInfoViewModel mViewModel;
    private FragmentMealInfoBinding binding;

    ArrayList<mealView> meals = new ArrayList<>();

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        //get the meal to display
        //Meal mealToDisplay = this.getArguments().getMeal("message");

        ScheduleViewModel dashboardViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        binding = FragmentMealInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        container.removeAllViews();

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("MEAL_INFO");
        transaction.commit();

        int count = manager.getBackStackEntryCount();
        if (count > 0) {
            Log.d("MEAL_INFO", "Attempting to add the meal info page to the back stack. The top of the stack is now " + manager.getBackStackEntryAt(count - 1).getName());
        }

        return root;
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Create the button to return to the schedule page and set the on click listener
        ImageButton backToSchedule = (ImageButton) getView().findViewById(R.id.backToSchedule);
        backToSchedule.setOnClickListener((View.OnClickListener) this);

        // Add  meal views
        LinearLayout ll = (LinearLayout) getView().findViewById(R.id.mealLinearLayout);
        LinearLayout items = new LinearLayout(getContext());
        items.setOrientation(LinearLayout.VERTICAL);

        Meal tempMeal = this.getArguments().getParcelable("meal");

        for (int i = 0; i < tempMeal.size(); i++) {
            mealView temp = new mealView(getContext());
            temp.setMealName(tempMeal.get(i).getTitle());
            for (int j = 0; j < tempMeal.get(i).getRestrictions().size(); j++) {
                temp.addTag(tempMeal.get(i).getRestrictions().get(j));
            }
            meals.add(temp);
        }

        for (int i = 0; i < tempMeal.size(); i++) {
            items.addView(meals.get(i));
        }

        ll.addView(items);

        //change date display
        TextView whichMeal = getView().findViewById(R.id.meal);
        whichMeal.setText(tempMeal.getMealTime().toString());

    }

    public void onClick(View v) {
        if (v == getView().findViewById(R.id.backToSchedule)){
            // Create new fragment and transaction
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);

            // Replace whatever is in the fragment_container view with this fragment
            transaction.replace(R.id.nav_host_fragment_activity_main, ScheduleFragment.class, null);

            // Commit the transaction
            transaction.commit();
        }
    }
}