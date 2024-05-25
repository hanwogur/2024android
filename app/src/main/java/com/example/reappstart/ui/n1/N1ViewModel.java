package com.example.reappstart.ui.n1;

import android.view.View;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class N1ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public N1ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("홈");
    }

    public LiveData<String> getText() {
        return mText;
    }


}