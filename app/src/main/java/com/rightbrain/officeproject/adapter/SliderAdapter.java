package com.rightbrain.officeproject.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.CustomTarget;
import com.bumptech.glide.request.transition.Transition;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.ModelSetup;

import java.util.ArrayList;

public class SliderAdapter extends PagerAdapter {
    Context context;
    ArrayList<ModelSetup> setupArrayList;
    LayoutInflater layoutInflater;

    public SliderAdapter(Context context,ArrayList<ModelSetup> setupArrayList) {
        this.context = context;
        this.setupArrayList = setupArrayList;
    }

    public int[] slide_images = {
            R.drawable.sliderimage4,
            R.drawable.sliderimage2,
            R.drawable.sliderimage3
    };

    public String[] slider_heading = {
            "Preminum Quality Guarantee",
            "Great Deals Everyday",
            "Give $5, Get $5"
    };

    public String[] slider_details = {
            "You can only find high-quality and affordable clothing for you and your little ones here.",
            "Don't miss out on our Flash Sale and Daily Specials with great discounts.",
            "Invite friends! They will get $4 off their first order and you'll get a $5 bonus!"
    };

    @Override
    public int getCount() {
        return setupArrayList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == (ConstraintLayout) object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
        View view = layoutInflater.inflate(R.layout.slider_layout,container,false);

        ConstraintLayout layout =view.findViewById(R.id.relativeLayoutImage);

     /*   try {
            URL url = new URL("http://www.terminalbd.com/uploads/domain/4/customizeTemplate/60dad73c60b30.a-splash-screen-for-android-and-slide-screen.jpg");
            Bitmap bitmap = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            Drawable image = new BitmapDrawable(context.getResources(), bitmap);

            layout.setBackgroundDrawable(image);

        } catch (Exception e) {
            e.printStackTrace();
        }*/

        Glide.with(context)
                .load("http://"+setupArrayList.get(position).getBackgroundImage())
                .into(new CustomTarget<Drawable>() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        layout.setBackground(resource);
                    }

                    @Override
                    public void onLoadCleared(@Nullable Drawable placeholder) {

                    }
                });

        ImageView imageView = view.findViewById(R.id.imageView37);
        TextView textView = view.findViewById(R.id.tv_demo);
        TextView details = view.findViewById(R.id.tv_details);

        Glide
                .with(context)
                .load("http://"+setupArrayList.get(position).getLogo())
                .into(imageView);
/*
        imageView.setImageResource(slide_images[position]);*/
        textView.setText(""+setupArrayList.get(position).getName());
        details.setText(""+setupArrayList.get(position).getDescription());

        container.addView(view);
        return view;


    }






    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout) object);
    }


}
