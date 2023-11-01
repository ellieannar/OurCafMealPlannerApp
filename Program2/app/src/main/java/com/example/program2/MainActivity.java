package com.example.program2;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.imageview.ShapeableImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    EditText firstNameTextView;
    EditText lastNameTextView;
    EditText emailTextView;
    EditText phoneNumberTextView;
    Button addContact;
    Button addImage;
    ShapeableImageView profileImg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Connecting the views
        firstNameTextView = findViewById(R.id.firstName);
        lastNameTextView = findViewById(R.id.lastName);
        emailTextView = findViewById(R.id.emailAddr);
        phoneNumberTextView = findViewById(R.id.phoneNumber);
        addContact = findViewById(R.id.addContactButton);
        addImage = findViewById(R.id.addImage);
        profileImg = findViewById(R.id.profileImage);

        addImage.setOnClickListener(view -> selectImage());

        addContact.setOnClickListener(view -> addContact());


    }



    // add contact clears out text fields
    void addContact() {
        firstNameTextView.setText("");
        lastNameTextView.setText("");
        emailTextView.setText("");
        phoneNumberTextView.setText("");
        profileImg.setImageResource(R.drawable.profile_image);
    }

    //Code used from Geeks for Geeks

    void selectImage() {
        Intent imageSelectorIntent = new Intent();
        imageSelectorIntent.setType("image/*");
        imageSelectorIntent.setAction(Intent.ACTION_GET_CONTENT);

        photoSelectionLauncher.launch(imageSelectorIntent);
    }

    ActivityResultLauncher<Intent> photoSelectionLauncher
    = registerForActivityResult(
        new ActivityResultContracts
            .StartActivityForResult(),
        result -> {
            if (result.getResultCode()
                == Activity.RESULT_OK) {
                Intent data = result.getData();
                // do your operation from here....
                if (data != null
                    && data.getData() != null) {
                    Uri selectedImageUri = data.getData();
                    Bitmap selectedImageBitmap = null;
                    try {
                        selectedImageBitmap
                            = MediaStore.Images.Media.getBitmap(
                                this.getContentResolver(),
                                selectedImageUri);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    profileImg.setImageBitmap(
                        selectedImageBitmap);
                }
            }
        });

}