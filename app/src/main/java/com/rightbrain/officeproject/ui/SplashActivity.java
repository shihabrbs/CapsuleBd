package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.ModelSetup;
import com.rightbrain.officeproject.retrofit.ApiClint;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class SplashActivity extends AppCompatActivity {

    Thread timer;
    CompositeDisposable compositeDisposable;
    MysharedPreferanceSetup mysharedPreferanceSetup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        compositeDisposable = new CompositeDisposable();
        getData();
        mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        String layout = mysharedPreferanceSetup.getSplashScreen();


        if (layout.equals("none")) {
            Intent intent = new Intent(SplashActivity.this, MultiLayout_SplashScreen.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(SplashActivity.this, DrawerMainActivity.class);
            startActivity(intent);
            finish();
        }

        /*timer = new Thread(){
            @Override
            public void run() {
                try {
                    synchronized (this){
                        wait(2000);
                    }
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(SplashActivity.this,DrawerMainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        };
        timer.start();*/
    }

    private void getData() {
        compositeDisposable.add(ApiClint.getInstance().getShop("UBE49VBB")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelSetup>>>() {
                    @Override
                    public void onNext(final Response<List<ModelSetup>> response) {

                        mysharedPreferanceSetup.setCurrency("" + response.body().get(0).getCurrency());
                        mysharedPreferanceSetup.setShippingCharge("" + response.body().get(0).getShippingCharge());


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

}