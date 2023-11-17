package com.example.cafmealplanner.ui.Schedule;

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
import com.example.cafmealplanner.ui.Menu.mealView;

import android.widget.ImageButton;
import android.content.Intent;
import android.widget.LinearLayout;

import java.util.Vector;


public class MealInfo extends Fragment implements View.OnClickListener {

    public static MealInfo newInstance() {
        return new MealInfo();
    }

    private MealInfoViewModel mViewModel;
    private FragmentMealInfoBinding binding;

    Vector<mealView> dinnerMeals;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ScheduleViewModel dashboardViewModel =
                new ViewModelProvider(this).get(ScheduleViewModel.class);

        binding = FragmentMealInfoBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        container.removeAllViews();

        return root;
    }


    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        // Create the button to return to the schedule page and set the on click listener
        ImageButton backToSchedule = (ImageButton) getView().findViewById(R.id.backToSchedule);
        backToSchedule.setOnClickListener((View.OnClickListener) this);

        // Add some meal views

        LinearLayout dinnerLinearLayout = (LinearLayout)getView().findViewById(R.id.dinnerLinearLayout);
        LinearLayout dinnerItems = new LinearLayout(getContext());
        dinnerItems.setOrientation(LinearLayout.VERTICAL);

        dinnerMeals = new Vector<mealView>(5);
        for (int i = 0; i < 5; i++) {
            mealView temp = new mealView(getContext());
            temp.setMealName("PASTA");
            temp.addTag(mealView.TAG_TYPE.VEGETARIAN);
            dinnerMeals.add(temp);
        }

        for (int i = 0; i < dinnerMeals.size(); i++) {
            dinnerItems.addView(dinnerMeals.get(i));
        }

        dinnerLinearLayout.addView(dinnerItems);
    }

    public void onClick(View v) {
        if (v == getView().findViewById(R.id.backToSchedule)){
            // Create new fragment and transaction
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);

            // Replace whatever is in the fragment_container view with this fragment
            transaction.replace(R.id.nav_host_fragment_activity_main, ScheduleFragment.class, null);

            // Add the transaction to the back stack to support back navigation
            transaction.addToBackStack(null);

            // Commit the transaction
            transaction.commit();
        }
    }








}