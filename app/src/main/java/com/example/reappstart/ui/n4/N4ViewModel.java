package com.example.reappstart.ui.n4;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class N4ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public N4ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("찜 기능 예정");
    }

    public LiveData<String> getText() {
        return mText;
    }
}