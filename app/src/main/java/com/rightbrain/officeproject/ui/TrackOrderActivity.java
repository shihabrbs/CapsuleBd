package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;

import com.rightbrain.officeproject.databinding.ActivityTrackOrderBinding;

public class TrackOrderActivity extends AppCompatActivity {

    ActivityTrackOrderBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityTrackOrderBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Intent intent = getIntent();
        String status = intent.getStringExtra("status");




        if (status.equals("wfc")) {

            binding.imageCircleGreen2.setVisibility(View.VISIBLE);
            binding.imageLineGreen2.setVisibility(View.VISIBLE);
            binding.imageCircleBlue2.setVisibility(View.VISIBLE);

            binding.textView35.setTextColor(Color.parseColor("#595959"));
            binding.textView38.setTextColor(Color.parseColor("#595959"));

            binding.textView36.setTextColor(Color.parseColor("#1C5F95"));
            binding.textView40.setTextColor(Color.parseColor("#1C5F95"));


        } else if (status.equals("confirm")) {

            binding.imageCircleGreen2.setVisibility(View.VISIBLE);
            binding.imageLineGreen2.setVisibility(View.VISIBLE);
            binding.imageCircleGreen3.setVisibility(View.VISIBLE);
            binding.imageCircleBlue4.setVisibility(View.VISIBLE);
            binding.imageLineGreen3.setVisibility(View.VISIBLE);


            binding.textView35.setTextColor(Color.parseColor("#595959"));
            binding.textView38.setTextColor(Color.parseColor("#595959"));

            binding.textView36.setTextColor(Color.parseColor("#595959"));
            binding.textView40.setTextColor(Color.parseColor("#595959"));

            binding.textView37.setTextColor(Color.parseColor("#1C5F95"));
            binding.textView41.setTextColor(Color.parseColor("#1C5F95"));


        }else if (status.equals("delivered")) {

            binding.imageCircleGreen2.setVisibility(View.VISIBLE);
            binding.imageLineGreen2.setVisibility(View.VISIBLE);
            binding.imageCircleGreen3.setVisibility(View.VISIBLE);
            binding.imageVerify.setVisibility(View.VISIBLE);
            binding.imageLineGreen3.setVisibility(View.VISIBLE);


            binding.textView35.setTextColor(Color.parseColor("#595959"));
            binding.textView38.setTextColor(Color.parseColor("#595959"));

            binding.textView36.setTextColor(Color.parseColor("#595959"));
            binding.textView40.setTextColor(Color.parseColor("#595959"));

            binding.textView37.setTextColor(Color.parseColor("#FE6268"));
            binding.textView41.setTextColor(Color.parseColor("#FE6268"));

            // binding.textView32.setText("Order Delivered");


        }

    }

    public void btn_back(View view) {
        onBackPressed();
    }
}