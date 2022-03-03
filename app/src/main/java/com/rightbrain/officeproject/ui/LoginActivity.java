package com.rightbrain.officeproject.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.ModelUsers;
import com.rightbrain.officeproject.retrofit.ApiClint;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable;
    EditText phone;
    MysharedPreferance sharedPreferance;

    @BindView(R.id.img_home)
    ImageView gohome;

    @BindView(R.id.appnametv)
    TextView appname;
    String uniqueCode;
    String logo;
    String shopname;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();
        shopname = mysharedPreferanceSetup.getShopName();
        logo = mysharedPreferanceSetup.getLogo();


        compositeDisposable = new CompositeDisposable();
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());
        TextView textView = findViewById(R.id.dthana);
        phone = findViewById(R.id.edit_sign_in_username);


        if (logo.equals("")){
            gohome.setVisibility(View.GONE);
            appname.setVisibility(View.VISIBLE);
        }else {
            gohome.setVisibility(View.VISIBLE);
            appname.setVisibility(View.GONE);

            Glide
                    .with(getApplicationContext())
                    .load("http://"+logo)
                    .into(gohome);

        }


        appname.setText(""+mysharedPreferanceSetup.getShopName());

        String text = "Don't have an account ? SignUp";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {


                startActivity(new Intent(LoginActivity.this, SignUpActivity.class));
                finish();


            }

            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setUnderlineText(false);
            }

        };
        ss.setSpan(clickableSpan1, 24, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new BackgroundColorSpan(Color.WHITE), 24, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.rgb(243, 156, 38)), 24, 30, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());


        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this,DrawerMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

    }



    public void btnlogin(View view) {

        String phonee = phone.getText().toString();


        //input string
        String firstThreeNumber = "";     //substring containing first 4 characters

        if (phonee.length() == 11) {
            firstThreeNumber = phonee.substring(0, 3);
            //      Toast.makeText(this, ""+firstThreeNumber, Toast.LENGTH_SHORT).show();
            if ((firstThreeNumber.contains("017") || (firstThreeNumber.contains("019")) || (firstThreeNumber.contains("018")) || (firstThreeNumber.contains("016")) || (firstThreeNumber.contains("013")) || (firstThreeNumber.contains("015")))) {

                ModelUsers modelUsers = new ModelUsers();
                modelUsers.setMobile(""+phonee);
                createAccount(modelUsers);

            } else {
                Toast.makeText(this, "Invalid Phone Number", Toast.LENGTH_SHORT).show();
                phone.setError("Invalid Phone Number");
                phone.requestFocus();
                return;
            }
        } else {
            phone.setError("must be 11 digit");
            phone.requestFocus();
            return;
        }










    }

    private void createAccount(ModelUsers modelUsers) {


        compositeDisposable.add(ApiClint.getInstance().loginAccount(""+uniqueCode, modelUsers)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelUsers>>() {
                    @Override
                    public void onNext(final Response<ModelUsers> response) {


                             String status = ""+response.body().getMsg();
                             if (status.equals("invalid")){
                                 Toast.makeText(LoginActivity.this, "This number is not registered", Toast.LENGTH_LONG).show();
                             }else {
                                 String userid = ""+response.body().getUser_id();
                                 String name = ""+response.body().getName();
                                 String address = ""+response.body().getAddress();
                                 String anothernumber = ""+response.body().getPhone();
                                 String email = ""+response.body().getEmail();

                                 Intent intent = new Intent(LoginActivity.this,OTPWebActivity.class);
                                 intent.putExtra("otpcode",""+response.body().getPassword());
                                 intent.putExtra("phone",""+response.body().getUsername());
                                 intent.putExtra("userid",""+userid);
                                 intent.putExtra("name",""+name);
                                 intent.putExtra("address",""+address);
                                 intent.putExtra("anothernumber",""+anothernumber);
                                 intent.putExtra("email",""+email);
                                 startActivity(intent);
                             }



                        //Toast.makeText(LoginActivity.this, ""+response.body().getUsername(), Toast.LENGTH_SHORT).show();



                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

            Intent intent = new Intent(LoginActivity.this,DrawerMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);





    }
}