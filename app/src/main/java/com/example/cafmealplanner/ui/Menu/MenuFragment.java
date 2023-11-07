package com.example.cafmealplanner.ui.Menu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.cafmealplanner.R;
import com.example.cafmealplanner.databinding.FragmentMenuBinding;

public class MenuFragment extends Fragment implements View.OnClickListener {

    private FragmentMenuBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        MenuViewModel menuViewModel =
                new ViewModelProvider(this).get(MenuViewModel.class);

        binding = FragmentMenuBinding.inflate(inflater, container, false);
        View root = binding.getRoot();


        return root;
    }

    //function for when view is created
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        //forward day button and onClickListener
        Button forwardDay = getView().findViewById(R.id.forwardDayButton);
        forwardDay.setOnClickListener(this);

        //back day button and onClickListener
        Button backDay = getView().findViewById(R.id.backDayButton);
        backDay.setOnClickListener(this);
    }

    //click handler
    public void onClick(View v) {
        //Date text to be changed
        TextView label = getView().findViewById(R.id.dateTextView);
        //depending on the element clicked (v), perform corresponding action
        if (v == getView().findViewById(R.id.forwardDayButton)) {
            label.setText("MOVE FORWARD");
        }else if (v == getView().findViewById(R.id.backDayButton)) {
            label.setText("MOVE BACK");
        }
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}