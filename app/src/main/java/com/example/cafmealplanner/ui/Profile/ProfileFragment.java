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

public class ProfileFragment extends Fragment implements View.OnClickListener{

    private FragmentProfileBinding binding;

    private Boolean editMode = false;


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
            editModeTurnedOn();
        } else if (v == editButton) {
            editMode = false;
            editModeTurnedOff();
        }


    }


    private void editModeTurnedOn() {
        Log.d("ON", "editModeTurnedOn");
        displayAllDietaryRestrictions();

    }

    private void editModeTurnedOff() {
        Log.d("OFF", "editModeTurnedOff");
    }




    private void displayAllDietaryRestrictions() {
        LinearLayout dietaryLayout = getView().findViewById(R.id.dietaryRestrictionsLinearLayout);

        dietaryRestriction r = new dietaryRestriction(getContext());
        r.setRestriction(dietaryRestriction.restrictionType.GLUTEN_FREE);
        dietaryLayout.addView(r);
        r = new dietaryRestriction(getContext());
        r.setRestriction(dietaryRestriction.restrictionType.DAIRY_FREE);
        dietaryLayout.addView(r);
        r = new dietaryRestriction(getContext());
        r.setRestriction(dietaryRestriction.restrictionType.VEGETARIAN);
        dietaryLayout.addView(r);
        r = new dietaryRestriction(getContext());
        r.setRestriction(dietaryRestriction.restrictionType.VEGAN);
        dietaryLayout.addView(r);
        r = new dietaryRestriction(getContext());
        r.setRestriction(dietaryRestriction.restrictionType.SEAFOOD);
        dietaryLayout.addView(r);

    }




    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}