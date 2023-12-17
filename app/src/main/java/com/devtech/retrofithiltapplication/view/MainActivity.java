package com.devtech.retrofithiltapplication.view;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.devtech.retrofithiltapplication.databinding.ActivityMainBinding;
import com.devtech.retrofithiltapplication.viewmodel.TokenViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private TokenViewModel model;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        initViewModel();
        model.getUserLiveData().observe(this, userModel -> binding.tvMessage.setText(userModel.getData().getEmail()));
    }

    private void initViewModel() {
        model = new ViewModelProvider(this).get(TokenViewModel.class);
        model.getUserData();
    }
}