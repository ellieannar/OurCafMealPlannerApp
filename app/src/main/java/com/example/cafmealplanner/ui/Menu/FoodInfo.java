package com.example.cafmealplanner.ui.Menu;

import androidx.core.util.AtomicFileKt;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
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

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

public class FoodInfo extends Fragment implements View.OnClickListener {
    boolean editRatingOn = false;
    static int starRating = 0;

    // ratingList will store all the entries inside ratings.xml
    List<Rating> ratingList = new ArrayList<>();
    Rating rating;
    String text, mealName;

    /*String ratingsPathname = "../values/ratings.xml";
    File ratingsFile = new File(ratingsPathname);*/

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

        // Populate ratingList with the data from ratings.xml
        // to get a list of meal names and corresponding star ratings
        InputStream in = null;

        /*try {
            in = new FileInputStream(ratingsFile);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        ratingList = parse(in);

        TextView nameView = (TextView) getView().findViewById(R.id.mealName);
        mealName = nameView.getText().toString();

        // Set this meal rating to its corresponding star rating
        // in the file. Otherwise, starRating = 0
        int i;
        for (i = 0; i < ratingList.size(); i++) {
            if (mealName == ratingList.get(i).mealName) {
                starRating = ratingList.get(i).numStars;
                break;
            }
        }

        if (i == ratingList.size())
            starRating = 0;*/

        setStarAppearance();

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

    @SuppressLint("ResourceAsColor")
    public void setStarAppearance() {
        int i;
        ImageButton star = new ImageButton(getContext());
        ImageButton starRatings[] = {getView().findViewById(R.id.star1), getView().findViewById(R.id.star2), getView().findViewById(R.id.star3), getView().findViewById(R.id.star4), getView().findViewById(R.id.star5)};

        // The current star rating will determine the number of filled or yellow stars
        // depending on whether we are in editing mode or not

        for (i = 0; i < starRating; i++) {
            if (editRatingOn) {
                starRatings[i].setImageResource(R.drawable.filled_star);
            } else {
                starRatings[i].setImageResource(R.drawable.sss);
            }
            starRatings[i].setImageTintList(ColorStateList.valueOf(R.color.biolaRed));

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
            starRatings[i].setImageTintList(ColorStateList.valueOf(R.color.biolaBlack));

            star.setAdjustViewBounds(true);
            star.setMaxHeight(50);
            star.setMaxWidth(50);
        }
    }

    public void setRating(int numStars) {
        starRating = numStars; // Adjust the official star rating

        /*Rating newRating = new Rating();
        newRating.setMealName(mealName);
        newRating.setNumStars(starRating);

        // Change the star rating of this food in the rating list

        int i;
        for (i = 0; i < ratingList.size(); i++) {
            if (ratingList.get(i).mealName == newRating.mealName) {
                ratingList.get(i).numStars = newRating.numStars;
                break;
            }
        }

        // If the food is not already in the rating list, add it
        if (i == ratingList.size()) {
            ratingList.add(newRating);
        }*/

        setStarAppearance(); // Change the appearance of the stars to reflect the new star rating
    }

    public List<Rating> getRatings() {
        return ratingList;
    }

    public List<Rating> parse(InputStream in) {
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            XmlPullParser parser = factory.newPullParser();

            parser.setInput(in, null);

            int eventType = parser.getEventType();
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagName = parser.getName();
                switch(eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagName.equalsIgnoreCase("rating")) {
                            rating = new Rating();
                        }
                        break;
                    case XmlPullParser.TEXT:
                        text = parser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if (tagName.equalsIgnoreCase("rating")) {
                            ratingList.add(rating);
                        } else if (tagName.equalsIgnoreCase("meal_name")) {
                            rating.setMealName(text);
                        } else if (tagName.equalsIgnoreCase("num_stars")) {
                            rating.setNumStars(Integer.parseInt(text));
                        }
                        break;
                    default:
                        break;
                }
                eventType = parser.next();
            }
        } catch (XmlPullParserException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return ratingList;
    }

    public void saveRating() {
        try {
            DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document dom = builder.newDocument();

            // Convert the objects to xml
            Element root, name, stars;
            for (Rating r : ratingList) {
                root = dom.createElement("rating");
                dom.appendChild(root);

                name = dom.createElement("meal_name");
                name.setTextContent(r.mealName);

                stars = dom.createElement("num_stars");
                stars.setTextContent(""+starRating);

                root.appendChild(name);
                root.appendChild(stars);
            }

            // Overwrite the existing data in ratings.xml
            Transformer tr = TransformerFactory.newInstance().newTransformer();
            tr.setOutputProperty(OutputKeys.INDENT, "yes");
            tr.transform(new DOMSource(dom), new StreamResult(new File("ratings.xml")));

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerConfigurationException e) {
            throw new RuntimeException(e);
        } catch (TransformerException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void onDestroyView() {
        //saveRating(); // Write the updated rating list to xml
        super.onDestroyView();
    }
}