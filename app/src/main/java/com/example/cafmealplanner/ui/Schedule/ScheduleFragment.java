package com.example.cafmealplanner.ui.Schedule;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.content.Intent;


import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.example.cafmealplanner.R;
import com.example.cafmealplanner.databinding.FragmentScheduleBinding;
import com.example.cafmealplanner.ui.Data.Meal;
import com.example.cafmealplanner.ui.Data.resource;

public class ScheduleFragment extends Fragment implements View.OnClickListener{

    private FragmentScheduleBinding binding;
    private mealButton sundayButtons[] = new mealButton[3];
    private Button editButton;
    private resource resources = new resource();

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ScheduleViewModel dashboardViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        binding = FragmentScheduleBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        container.removeAllViews();

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("SCHEDULE");
        transaction.commit();

        int count = manager.getBackStackEntryCount();
        if (count > 0) {
            Log.d("SCHEDULE", "Attempting to add the schedule page to the back stack. The top of the stack is now " + manager.getBackStackEntryAt(count - 1).getName());
        }

        //button clicks manager
        LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(getContext());
        lbm.registerReceiver(receiver, new IntentFilter("filter_string"));

        return root;
    }


    //button clicks
    public BroadcastReceiver receiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (intent != null && intent.getExtras().getString("audience").equals("forSchedule")) {
                displayMealInfo();
            }
        }
    };

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        setupLinearLayouts();
        editButton = view.findViewById(R.id.edit_schedule);
        editButton.setText("EDIT");
        editButton.setOnClickListener(this);
    }

    void setupLinearLayouts() {
        //Sunday

        mealButton.dayOfWeek days[] = {mealButton.dayOfWeek.SUNDAY, mealButton.dayOfWeek.MONDAY, mealButton.dayOfWeek.TUESDAY, mealButton.dayOfWeek.WEDNESDAY, mealButton.dayOfWeek.THURSDAY, mealButton.dayOfWeek.FRIDAY, mealButton.dayOfWeek.SATURDAY};
        mealButton.mealTime times[] = {mealButton.mealTime.BREAKFAST, mealButton.mealTime.LUNCH, mealButton.mealTime.DINNER};
        LinearLayout dayLayouts[] = {getView().findViewById(R.id.sundayLinearLayout), getView().findViewById(R.id.mondayLinearLayout),getView().findViewById(R.id.tuesdayLinearLayout),getView().findViewById(R.id.wednesdayLinearLayout),getView().findViewById(R.id.thursdayLinearLayout),getView().findViewById(R.id.fridayLinearLayout),getView().findViewById(R.id.saturdayLinearLayout)};
        LinearLayout day;
        for (int i = 0; i < 7; i++) {
            day = dayLayouts[i];
            for (int j = 0; j < 3; j++) {
                mealButton m = new mealButton(getContext());
                m.setDay(days[i]);
                m.setMeal(times[j]);
                if (resources.getMealStatus(i, j)) {
                    m.setFill(mealButton.buttonSelection.FILLED);
                } else {
                    m.setFill(mealButton.buttonSelection.EMPTY);
                }
                day.addView(m);
            }
        }


    }


    //click handler
    public void onClick(View v) {
        if (v == editButton) {
            //Alert app that editing mode selected for schedule
            Intent intent = new Intent("filter_string");
            //Log.d("EDIT SCHEDULE", "onClick: ");
            //intent.putExtra("mealName", nameOfMealTextView.getText().toString());

            // put your all data using put extra
            intent.putExtra("audience", "edit_schedule");

            LocalBroadcastManager.getInstance(getContext()).sendBroadcast(intent);
            Log.d("BROADCAST INTENT", "success ");

            //update text
            if (editButton.getText() == "EDIT") {
                editButton.setText("DONE");
            } else {
                editButton.setText("EDIT");
            }

        }

        /*
        Button b = getView().findViewById(R.id.edit_schedule);
        if (v == b) {
            // Create new fragment and transaction
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);
            // Replace whatever is in the fragment_container view with this fragment
            // Replace whatever is in the fragment_container view with this fragment
            transaction.replace(R.id.nav_host_fragment_activity_main, MealInfo.class, null);
            // Commit the transaction
            transaction.commit();
        }
         */
    }

    private void displayMealInfo() {
        // Ensure activity is properly initialized
        if (getActivity() != null) {
            // Create new fragment and transaction
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);

            // Replace whatever is in the fragment_container view with this fragment
            transaction.replace(R.id.nav_host_fragment_activity_main, MealInfo.class, null);

            // Commit the transaction
            transaction.commit();
        } else {
            Log.d("NAVIGATION", "for some reason getactivity is null");
        }

    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}