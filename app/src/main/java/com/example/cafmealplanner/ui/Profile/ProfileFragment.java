package com.example.cafmealplanner.ui.Profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

    private Boolean editMode = false;
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

        if (editMode == true) {
            editModeTurnedOn();
        } else {
            editModeTurnedOff();
        }


    }

    public void onClick(View v) {
        Button editButton = getView().findViewById(R.id.profileEditButton);
        if (v == editButton && !editMode) {
            editMode = true;
            editButton.setText("Done");
            editModeTurnedOn();
        } else if (v == editButton) {
            editMode = false;
            editButton.setText("Edit");
            editModeTurnedOff();
        }


    }


    private void editModeTurnedOn() {
        displayAllDietaryRestrictions();

    }

    private void editModeTurnedOff() {
        displaySelectedRestrictions();
    }




    private void displayAllDietaryRestrictions() {

        LinearLayout dietaryLayout = getView().findViewById(R.id.dietaryRestrictionsLinearLayout);
        dietaryLayout.removeAllViews();

        for (int i = 0; i < 5; i++) {
            allDietaryRestrictions[i].showCheckbox();
            dietaryLayout.addView(allDietaryRestrictions[i]);
        }

    }

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




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}