package com.app.fragmentlivedataviewmodelpratice.ui.main;

import android.os.Bundle;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;

public class MainViewModel extends ViewModel {
    // TODO: Implement the ViewModel

    private static final String NAME_KEY = "name";

    private SavedStateHandle mState;

    public MainViewModel(SavedStateHandle savedStateHandle) {
        mState = savedStateHandle;
    }

    LiveData<String> getOperator() {
        return mState.getLiveData(NAME_KEY);
    }

    void saveNewOperator(String operator) {
        mState.set(NAME_KEY, operator);
    }
}