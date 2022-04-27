package com.example.sproject.ui.dashboard;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class FavoritesViewModel extends ViewModel {


    private LiveData<Object> text;

    public FavoritesViewModel() {

    }

    public LiveData<Object> getText() {
        return text;
    }

    public void setText(LiveData<Object> text) {
        this.text = text;
    }
}