package com.example.reappstart.ui.n5;

import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.reappstart.R;
import com.example.reappstart.databinding.ActivityMainBinding;

public class N5ViewModel extends ViewModel {

    private ActivityMainBinding binding;

    private final MutableLiveData<String> mText;

    public N5ViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("프로필");

    }

    public LiveData<String> getText() {
        return mText;
    }

}