package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.bumptech.glide.Glide;
import com.rightbrain.officeproject.databinding.ActivityFullImageBinding;

public class FullImageActivity extends AppCompatActivity {

    ActivityFullImageBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityFullImageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String img = intent.getStringExtra("url");

        Glide
                .with(FullImageActivity.this)
                .load(""+img)
                .into(binding.fullimage);
    }
}