package com.example.program2;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.Objects;


public class allContactsActivity extends AppCompatActivity {


    private static String message = "No Contacts to display.\nPress + to add a contact";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_contacts);



        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.textView);


    if( getIntent().getExtras() != null && !Objects.equals(getIntent().getStringExtra(MainActivity.EXTRA_MESSAGE), ""))
        {
            // Get the Intent that started this activity and extract the string
            Intent intent = getIntent();
            if (!Objects.equals(message, "No Contacts to display.\nPress + to add a contact")) {
                message += "\n" + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            } else {
                message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
            }
            //message = "Most recent contact addition: " + intent.getStringExtra(MainActivity.EXTRA_MESSAGE);
        }
        textView.setText(message);


        // calling the action bar
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setTitle("Contacts");

    }

    //set the menu to menu xml
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }
    // actions when add button tapped
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.newContact) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }




}