package com.example.cafmealplanner.ui.Menu;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MenuViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public MenuViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("This is Menu fragment");
    }

    public LiveData<String> getText() {
        return mText;
    }
}