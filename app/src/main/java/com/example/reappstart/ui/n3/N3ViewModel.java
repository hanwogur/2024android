package com.example.reappstart.ui.n3;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class N3ViewModel extends ViewModel {

    private final MutableLiveData<String> mText;

    public N3ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("게시판 예정");
    }

    public LiveData<String> getText() {
        return mText;
    }
}