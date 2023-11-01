package com.example.program2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;

import com.google.android.material.imageview.ShapeableImageView;


public class MainActivity extends AppCompatActivity {


    //views
    EditText firstNameTextView;
    EditText lastNameTextView;
    EditText emailTextView;
    EditText phoneNumberTextView;
    ShapeableImageView profileImg;
    public static final String EXTRA_MESSAGE = "com.example.myfirstapp.MESSAGE";


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("New Contact");
        actionBar.setDisplayHomeAsUpEnabled(true);

        // Connecting the views
        firstNameTextView = findViewById(R.id.firstName);
        lastNameTextView = findViewById(R.id.lastName);
        emailTextView = findViewById(R.id.emailAddr);
        phoneNumberTextView = findViewById(R.id.phoneNumber);
        profileImg = findViewById(R.id.profileImage);


    }

    //menu button - done
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_activity_menu, menu);
        return true;
    }

    // what to do when buttons are pressed
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.doneButton) {
            Intent intent = new Intent(this, allContactsActivity.class);
            intent.putExtra(EXTRA_MESSAGE, firstNameTextView.getText().toString() + " " +lastNameTextView.getText().toString() + "\n\t\tEmail: " + emailTextView.getText().toString() + "\n\t\tPhone Number: " + phoneNumberTextView.getText().toString());
            startActivity(intent);
            return true;
        } else if (item.getItemId() == android.R.id.home) {
            Intent intent = new Intent(this, allContactsActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }


}