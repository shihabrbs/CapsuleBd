package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.adapter.SliderAdapter;
import com.rightbrain.officeproject.model.ModelSetup;
import com.rightbrain.officeproject.retrofit.ApiClint;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MultiLayout_SplashScreen extends AppCompatActivity {
    private ViewPager mSliderViewPager;
    private LinearLayout linearLayout;
    Button button, btnskip,buttonback;
    private SliderAdapter sliderAdapter;
    private TextView[] mDots;
    TextView btnstart,btnstartFirst;
    int count = 1;
    int mcurrentpage;
    CompositeDisposable compositeDisposable;

    ArrayList<ModelSetup> setupArrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_multi_layout__splash_screen);

        compositeDisposable = new CompositeDisposable();
        getData();



        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        mSliderViewPager = (ViewPager) findViewById(R.id.viewPager);
        linearLayout = (LinearLayout) findViewById(R.id.linearLayout6);
        button = findViewById(R.id.nextbtn);
        btnskip = findViewById(R.id.skipbtn);
        btnstart = findViewById(R.id.tv_getstarted);
        buttonback = findViewById(R.id.backbtn);
        btnstartFirst = findViewById(R.id.tv_getstarted_start);

        setupArrayList = new ArrayList<>();

        sliderAdapter = new SliderAdapter(this, setupArrayList);

        mSliderViewPager.setAdapter(sliderAdapter);
        addDotsInticator(0);




        mSliderViewPager.addOnPageChangeListener(listener);

        btnstartFirst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiLayout_SplashScreen.this, DrawerMainActivity.class);
                startActivity(intent);
                finish();
                mysharedPreferanceSetup.setSplashScreen("mainpage");
                mysharedPreferanceSetup.setUniqueCode(""+setupArrayList.get(mcurrentpage).getActiveKey());
                mysharedPreferanceSetup.setShopName(""+setupArrayList.get(mcurrentpage).getName());
                mysharedPreferanceSetup.setShippingCharge(""+setupArrayList.get(mcurrentpage).getShippingCharge());
                mysharedPreferanceSetup.setCurrency(""+setupArrayList.get(mcurrentpage).getCurrency());

                if (setupArrayList.get(mcurrentpage).getLogo().equals("")){

                    mysharedPreferanceSetup.setLogo("");
                }else {
                    mysharedPreferanceSetup.setLogo(""+setupArrayList.get(mcurrentpage).getLogo());

                }

            }
        });

        btnstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiLayout_SplashScreen.this, DrawerMainActivity.class);
                startActivity(intent);
                finish();
                mysharedPreferanceSetup.setSplashScreen("mainpage");
                mysharedPreferanceSetup.setUniqueCode(""+setupArrayList.get(mcurrentpage).getActiveKey());
                mysharedPreferanceSetup.setShopName(""+setupArrayList.get(mcurrentpage).getName());
                mysharedPreferanceSetup.setShippingCharge(""+setupArrayList.get(mcurrentpage).getShippingCharge());
                mysharedPreferanceSetup.setCurrency(""+setupArrayList.get(mcurrentpage).getCurrency());
                mysharedPreferanceSetup.setCartProcess(""+setupArrayList.get(mcurrentpage).getCartProcess());

                if (setupArrayList.get(mcurrentpage).getLogo().equals("")){

                    mysharedPreferanceSetup.setLogo("");
                }else {
                    mysharedPreferanceSetup.setLogo(""+setupArrayList.get(mcurrentpage).getLogo());

                }
            }
        });

        btnskip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MultiLayout_SplashScreen.this, DrawerMainActivity.class);
                startActivity(intent);
                finish();
                mysharedPreferanceSetup.setSplashScreen("mainpage");
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int number = setupArrayList.size();
                mSliderViewPager.setCurrentItem(mcurrentpage + 1);
                if (mcurrentpage == number) {
                    count++;
                   /* if (count > 2) {
                        Intent intent = new Intent(MultiLayout_SplashScreen.this,DrawerMainActivity.class);
                        startActivity(intent);
                        finish();
                        mysharedPreferanceSetup.setSplashScreen("mainpage");

                    } else {

                    }*/

                } else {

                }
            }
        });

        buttonback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSliderViewPager.setCurrentItem(mcurrentpage - 1);
                if (mcurrentpage == 3) {
                    count--;


                } else {

                }
            }
        });
    }

    private void getData() {
        compositeDisposable.add(ApiClint.getInstance().getShop("UBE49VBB")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelSetup>>>() {
                    @Override
                    public void onNext(final Response<List<ModelSetup>> response) {

                      /*  mysharedPreferanceSetup.setCurrency("" + response.body().get(0).getCurrency());
                        mysharedPreferanceSetup.setShippingCharge("" + response.body().get(0).getShippingCharge());*/
                        ModelSetup modelSetup = new ModelSetup();
                        modelSetup.setName("Welcome To Our Shop");
                        modelSetup.setBackgroundImage("https://i.pinimg.com/originals/3b/ef/27/3bef27693c812b4762a9f363231ad5d2.jpg");
                        modelSetup.setDescription("This is our new Shop");
                        setupArrayList.add(modelSetup);

                        setupArrayList.addAll(response.body());
                        sliderAdapter.notifyDataSetChanged();




                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void addDotsInticator(int position) {
        int number = setupArrayList.size();
        mDots = new TextView[number];
        linearLayout.removeAllViews();


        for (int i = 0; i < mDots.length; i++) {
            mDots[i] = new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.gray));


            linearLayout.addView(mDots[i]);
        }
        if (mDots.length > 0) {
            mDots[position].setTextColor(getResources().getColor(R.color.soft_pink));
        }
    }

    ViewPager.OnPageChangeListener listener = new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @SuppressLint("ResourceAsColor")
        @Override
        public void onPageSelected(int i) {
            addDotsInticator(i);
            mcurrentpage = i;

            if (mcurrentpage == 0) {
                button.setEnabled(true);
                button.setText("Next");
                linearLayout.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                btnskip.setVisibility(View.GONE);
                btnstartFirst.setVisibility(View.GONE);
                btnstart.setVisibility(View.GONE);
                buttonback.setVisibility(View.GONE);
                btnskip.setText("SKIP");
            } else if (i == mDots.length - 1) {
                button.setEnabled(true);
                button.setText("Get Started");
                linearLayout.setVisibility(View.VISIBLE);
                buttonback.setVisibility(View.VISIBLE);
                btnstart.setVisibility(View.VISIBLE);
                btnstartFirst.setVisibility(View.GONE);
                button.setVisibility(View.GONE);
                btnskip.setVisibility(View.GONE);
                btnskip.setText("");
            } else {
                button.setEnabled(true);
                button.setText("Next");
                buttonback.setVisibility(View.GONE);
                btnstartFirst.setVisibility(View.VISIBLE);
                linearLayout.setVisibility(View.VISIBLE);
                button.setVisibility(View.VISIBLE);
                btnskip.setVisibility(View.GONE);
                btnstart.setVisibility(View.GONE);
                btnskip.setText("");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}