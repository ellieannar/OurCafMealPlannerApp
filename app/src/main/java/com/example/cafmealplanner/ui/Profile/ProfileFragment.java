package com.example.cafmealplanner.ui.Profile;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.res.ResourcesCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;

import com.example.cafmealplanner.R;
import com.example.cafmealplanner.databinding.FragmentProfileBinding;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Vector;

public class ProfileFragment extends Fragment implements View.OnClickListener{

    private FragmentProfileBinding binding;

    // To see if mode is edit or not
    private Boolean editMode = false;
    //Keep track of all 5 dietary restrictions
    public dietaryRestriction[] allDietaryRestrictions = new dietaryRestriction[5];
    public favoriteMeal[] allMeals = new favoriteMeal[10];

    EditText firstName;
    EditText lastName;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ProfileViewModel profileViewModel =
                new ViewModelProvider(this).get(ProfileViewModel.class);

        binding = FragmentProfileBinding.inflate(inflater, container, false);
        View root = binding.getRoot();
        container.removeAllViews();

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("PROFILE");
        transaction.commit();

        int count = manager.getBackStackEntryCount();
        if (count > 0) {
            Log.d("PROFILE", "Attempting to add the profile page to the back stack. The top of the stack is now " + manager.getBackStackEntryAt(count - 1).getName());
        }

        return root;
    }



    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        Button editButton = getView().findViewById(R.id.profileEditButton);
        //spinner hidden to start
        hideSpinner();

        firstName = getView().findViewById(R.id.firstNameTextView);
        lastName = getView().findViewById(R.id.lastNameTextView);
        readLongData();

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


        //EditText firstName = getView().findViewById(R.id.firstNameTextView);
        //EditText lastName = getView().findViewById(R.id.lastNameTextView);

        hideEditText(firstName);
        hideEditText(lastName);

        setupFavoriteMeals();

    }

    private void readLongData() {
        SharedPreferences sp = getActivity().getSharedPreferences("profileSharedData", Context.MODE_PRIVATE);
        String f = sp.getString("FIRST_NAME", "");
        String l = sp.getString("LAST_NAME", "");
        firstName.setText(f);
        lastName.setText(l);
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

    private void saveLongData(String first, String last) {

        SharedPreferences sp = getActivity().getSharedPreferences("profileSharedData", Context.MODE_PRIVATE);
        SharedPreferences.Editor spEdit = sp.edit();

        spEdit.putString("FIRST_NAME", firstName.getText().toString());
        spEdit.putString("LAST_NAME", lastName.getText().toString());

        spEdit.commit();

    }

    //call corresponding fucntions when edit mode turned on
    private void editModeTurnedOn() {
        displayAllDietaryRestrictions();
        showSpinner();


        showEditText(firstName);
        showEditText(lastName);

        openFavoriteMeals();

    }
    //call corresponding functions when edit mode turned off
    private void editModeTurnedOff() {
        displaySelectedRestrictions();
        hideSpinner();


        hideEditText(firstName);
        hideEditText(lastName);

        saveLongData(firstName.getText().toString(), lastName.getText().toString());

        closeFavoriteMeals();

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
        mealPlanSpinner.setSelection(adapter.getPosition(mealPlan.getText().toString()));
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

    void hideEditText(EditText e) {
        if (e.getText().toString().matches("")) {
            if (e == getView().findViewById(R.id.firstNameTextView)) {
                e.setHint("First Name");
            } else {
                e.setHint("Last Name");
            }

        }

        e.setFocusable(false);
        e.setEnabled(false);
        e.setCursorVisible(false);
        e.setBackgroundColor(Color.TRANSPARENT);
        e.setTextColor(ResourcesCompat.getColor(getResources(), R.color.biolaBlack, null));
    }

    void showEditText(EditText e) {
        e.setEnabled(true);
        e.setCursorVisible(true);
        if (e.getText().toString().matches("")) {
            if (e == getView().findViewById(R.id.firstNameTextView)) {
                e.setHint("First Name");
            } else {
                e.setHint("Last Name");
            }
        }
        e.setFocusableInTouchMode(true);
        e.setTextColor(ResourcesCompat.getColor(getResources(), R.color.darkBlue, null));

    }

    void setupFavoriteMeals() {
        LinearLayout favMeals = getView().findViewById(R.id.favoriteMealsLinearLayout);

        String s[] = {"Pizza", "Pasta", "Soup", "Poke", "Salad", "Teriyaki", "Hamburger", "Italian Beef Stew", "Quick Oats", "Hot breakfast bar"};

        for (int i = 0; i < 10; i++) {
            favoriteMeal f = new favoriteMeal(getContext());
            f.setMealName(s[i]);
            allMeals[i] = f;
        }

    }

    void openFavoriteMeals() {
        LinearLayout favMeals = getView().findViewById(R.id.favoriteMealsLinearLayout);
        favMeals.removeAllViews();
        for (int i = 0; i < 10; i++) {
            allMeals[i].showCheckbox();
            favMeals.addView(allMeals[i]);
        }
    }

    void closeFavoriteMeals() {
        LinearLayout favMeals = getView().findViewById(R.id.favoriteMealsLinearLayout);
        ScrollView favScroll = getView().findViewById(R.id.favoriteMealsScrollView);
        favMeals.removeAllViews();
        for (int i = 0; i < 10; i++) {
            if (allMeals[i].isChecked()) {
                allMeals[i].hideCheckbox();
                favMeals.addView(allMeals[i]);
            }
        }
        favScroll.fullScroll(ScrollView.FOCUS_UP);

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}