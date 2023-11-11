package com.example.cafmealplanner.ui.Profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cafmealplanner.R;
import com.example.cafmealplanner.databinding.FragmentProfileBinding;

import java.util.Vector;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    private FragmentProfileBinding binding;

    // To see if mode is edit or not
    private Boolean editMode = false;
    //Keep track of all 5 dietary restrictions
    public dietaryRestriction[] allDietaryRestrictions = new dietaryRestriction[5];


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        return root;
    }



    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        Button editButton = getView().findViewById(R.id.profileEditButton);
        //spinner hidden to start
        hideSpinner();

        //edit button listener
        editButton.setOnClickListener(this);

        LinearLayout dietaryLinearLayout = getView().findViewById(R.id.dietaryRestrictionsLinearLayout);

        //set up dietary restriction array
        dietaryRestriction r = new dietaryRestriction(getContext());
        r.setRestriction(dietaryRestriction.restrictionType.GLUTEN_FREE);
        allDietaryRestrictions[0] = r;
        r = new dietaryRestriction(getContext());
        r.setRestriction(dietaryRestriction.restrictionType.DAIRY_FREE);
        allDietaryRestrictions[1] = r;
        r = new dietaryRestriction(getContext());
        r.setRestriction(dietaryRestriction.restrictionType.VEGETARIAN);
        allDietaryRestrictions[2] = r;
        r = new dietaryRestriction(getContext());
        r.setRestriction(dietaryRestriction.restrictionType.VEGAN);
        allDietaryRestrictions[3] = r;
        r = new dietaryRestriction(getContext());
        r.setRestriction(dietaryRestriction.restrictionType.SEAFOOD);
        allDietaryRestrictions[4] = r;



    }

    //Checks for a click
    public void onClick(View v) {
        Button editButton = getView().findViewById(R.id.profileEditButton);
        //if edit button clicked & we're not already editing
        if (v == editButton && !editMode) {
            editMode = true;
            editButton.setText("Done");
            editModeTurnedOn();
        }
        //if edit button clicked but we're already editing
        else if (v == editButton) {
            editMode = false;
            editButton.setText("Edit");
            editModeTurnedOff();
        }

    }

    //call corresponding fucntions when edit mode turned on
    private void editModeTurnedOn() {
        displayAllDietaryRestrictions();
        showSpinner();


    }
    //call corresponding functions when edit mode turned off
    private void editModeTurnedOff() {
        displaySelectedRestrictions();
        hideSpinner();
    }


    //for displaying all 5 dietary restrictions regardless of selection
    private void displayAllDietaryRestrictions() {
        LinearLayout dietaryLayout = getView().findViewById(R.id.dietaryRestrictionsLinearLayout);
        dietaryLayout.removeAllViews();
        for (int i = 0; i < 5; i++) {
            allDietaryRestrictions[i].showCheckbox();
            dietaryLayout.addView(allDietaryRestrictions[i]);
        }

    }
    //display all selected dietery restricitons - hiding the checkbox (not editable)
    private void displaySelectedRestrictions() {
        LinearLayout dietaryLayout = getView().findViewById(R.id.dietaryRestrictionsLinearLayout);
        dietaryLayout.removeAllViews();
        for (int i = 0; i < 5; i++) {
            if (allDietaryRestrictions[i].restrictionIsEnabled()) {
                allDietaryRestrictions[i].hideCheckbox();
                dietaryLayout.addView(allDietaryRestrictions[i]);
            }
        }
    }

    //show the spinner when in edit mode
    private void showSpinner() {

        TextView mealPlan = getView().findViewById(R.id.mealPlanDisplayView);
        mealPlan.setVisibility(View.INVISIBLE);
        Spinner mealPlanSpinner = getView().findViewById(R.id.mealPlanSpinner);
        mealPlanSpinner.setVisibility(View.VISIBLE);

        // set up the options and apply
        String[] items = new String[]{"20 Flex", "15 Flex", "12 Flex", "10 Flex", "175 Block", "5 Flex", "20 Block"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_item,items);
        mealPlanSpinner.setAdapter(adapter);
    }

    //hide the spinner when no longer editing
    private void hideSpinner() {
        TextView mealPlan = getView().findViewById(R.id.mealPlanDisplayView);
        mealPlan.setVisibility(View.VISIBLE);
        Spinner mealPlanSpinner = getView().findViewById(R.id.mealPlanSpinner);
        mealPlanSpinner.setVisibility(View.INVISIBLE);

        //if something was selected (i.e. it's not the initial run), set the meal plan
        if (mealPlanSpinner.getSelectedItem() != null) {
            mealPlan.setText(mealPlanSpinner.getSelectedItem().toString());
        }



    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}