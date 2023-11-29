package com.example.cafmealplanner.ui.Menu;

import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.cafmealplanner.R;
import com.example.cafmealplanner.ui.Schedule.MealInfo;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

public class FoodInfo extends Fragment implements View.OnClickListener {


    boolean editRatingOn = false;
    private static final String ns = null;
    int starRating = 0;

    public List parse(InputStream in) throws XmlPullParserException, IOException {
        try {
            XmlPullParser parser = Xml.newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
            parser.setInput(in, null);
            parser.nextTag();
            return readFeed(parser);
        } finally {
            in.close();
        }
    }

    private List readFeed(XmlPullParser parser) throws XmlPullParserException, IOException {
        List ratings = new ArrayList();

        parser.require(XmlPullParser.START_TAG, ns, "feed");
        while(parser.next() != XmlPullParser.END_TAG) {
            if (parser.getEventType() != XmlPullParser.START_TAG) {
                continue;
            }

            String name = parser.getName();

            if (name.equals("rating")) {
                //ratings.add(readEntry(parser));
            }
        }
        return ratings;
    }

    /*private Object readEntry(XmlPullParser parser) {
    }*/

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        container.removeAllViews();

        FragmentManager manager = getActivity().getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.addToBackStack("FOOD_INFO");
        transaction.commit();

        int count = manager.getBackStackEntryCount();
        if (count > 0) {
            Log.d("FOOD_INFO", "Attempting to add the food info page to the back stack. The top of the stack is now " + manager.getBackStackEntryAt(count - 1).getName());
        }

        return inflater.inflate(R.layout.fragment_food_info, container, false);
    }

    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

        // Create a vector of strings to keep all the ingredients
        Vector<String> ingredientNames = new Vector<String>(3);
        ingredientNames.add(new String("White jasmine rice"));
        ingredientNames.add(new String("chopped pork"));
        ingredientNames.add(new String("mushrooms"));
        ingredientNames.add(new String("celery"));
        ingredientNames.add(new String("cabbage"));
        ingredientNames.add(new String("sesame"));

        // Display the ingredients list in the text view
        TextView ingredientsList = getView().findViewById(R.id.ingredients);

        for (int i = 0; i < ingredientNames.size(); i++) {
            ingredientsList.append(ingredientNames.get(i));

            if (i < ingredientNames.size() - 1) {
                ingredientsList.append(", ");
            }
        }

        // Implement the rating and back navigation buttons
        getView().findViewById(R.id.rating).setOnClickListener((View.OnClickListener) this);
        getView().findViewById(R.id.star1).setOnClickListener(this);
        getView().findViewById(R.id.star2).setOnClickListener(this);
        getView().findViewById(R.id.star3).setOnClickListener(this);
        getView().findViewById(R.id.star4).setOnClickListener(this);
        getView().findViewById(R.id.star5).setOnClickListener(this);

        getView().findViewById(R.id.back).setOnClickListener(this);

        //allow info text to scroll
        ingredientsList.setMovementMethod(new ScrollingMovementMethod());
    }

    public void onClick(View v) {
        if (v == getView().findViewById(R.id.rating)) {
            // The rating button toggles between edit and view modes
            Button editRating = getView().findViewById(R.id.rating);

            if (!editRatingOn) {
                editRatingOn = true;
                editRating.setText("Submit");
                Log.d("CLICKED", "onClick: ");
                setStarAppearance(); // Change the appearance of the stars to indicate editing mode
            }
            else {
                editRating.setText("Edit");
                editRatingOn = false;
                setStarAppearance(); // Change the appearance of the stars to indicate non-editing mode
            }
        }
        else if (v == getView().findViewById(R.id.star1) && editRatingOn) { // Only edit the star rating in editing mode
            setRating(1);
        }
        else if (v == getView().findViewById(R.id.star2) && editRatingOn) {
            setRating(2);
        }
        else if (v == getView().findViewById(R.id.star3) && editRatingOn) {
            setRating(3);
        }
        else if (v == getView().findViewById(R.id.star4) && editRatingOn) {
            setRating(4);
        }
        else if (v == getView().findViewById(R.id.star5) && editRatingOn) {
            setRating(5);
        }
        else if (v == getView().findViewById(R.id.back)) {
            // Get the fragment manager and transaction
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.setReorderingAllowed(true);

            int count = fragmentManager.getBackStackEntryCount();
            if (count > 0) {
                Log.d("BACK_NAV", "Attempting to go back from FoodInfo. The top of the stack is now "+fragmentManager.getBackStackEntryAt(count-1).getName());
                if (fragmentManager.getBackStackEntryAt(count - 2).getName().equals("MENU")) {
                    transaction.replace(R.id.nav_host_fragment_activity_main, MenuFragment.class, null);
                } else if (fragmentManager.getBackStackEntryAt(count - 2).getName().equals("MEAL_INFO")) {
                    transaction.replace(R.id.nav_host_fragment_activity_main, MealInfo.class, null);
                }
            }

            transaction.commit();
        }

    }

    public void setStarAppearance() {
        int i;
        ImageButton star = new ImageButton(getContext());
        ImageButton starRatings[] = {getView().findViewById(R.id.star1), getView().findViewById(R.id.star2), getView().findViewById(R.id.star3), getView().findViewById(R.id.star4), getView().findViewById(R.id.star5)};
        // The current star rating will determine the number of filled or yellow stars
        // depending on whether we are in editing mode or not
        for (i = 0; i < starRating; i++) {
            starRatings[i].setImageResource(R.drawable.filled_star);
            star.setAdjustViewBounds(true);
            star.setMaxHeight(50);
            star.setMaxWidth(50);
        }
        for (i = starRating; i < starRatings.length; i++) {
            if (editRatingOn) {
                starRatings[i].setImageResource(R.drawable.blank_star);
            } else {
                starRatings[i].setImageResource(R.drawable.gray_star);
            }
            star.setAdjustViewBounds(true);
            star.setMaxHeight(50);
            star.setMaxWidth(50);
        }
    }

    public void setRating(int numStars) {
        starRating = numStars; // Adjust the official star rating
        setStarAppearance(); // Change the appearance of the stars to reflect this
    }
}