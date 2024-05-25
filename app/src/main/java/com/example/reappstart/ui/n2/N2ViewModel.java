package com.example.reappstart.ui.n2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class N2ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public N2ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("검색");
    }

    public LiveData<String> getText() {
        return mText;
    }
}