package com.rightbrain.officeproject.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.CheckBox;
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

public class SignUpActivity extends AppCompatActivity {

    CompositeDisposable compositeDisposable;
    @BindView(R.id.textView3)
    TextView mybtn;

    @BindView(R.id.name)
    EditText name;

    @BindView(R.id.addressid)
    EditText address;

    @BindView(R.id.emailaddress)
    EditText emailaddress;


    @BindView(R.id.mobile_number)
    EditText phone;

    @BindView(R.id.img_home)
    ImageView gohome;

    @BindView(R.id.checkBox)
    CheckBox checkbox;

    @BindView(R.id.appnametv)
    TextView appname;
    MysharedPreferance sharedPreferance;
    String logo;
    String shopname;
    String uniqueCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        compositeDisposable = new CompositeDisposable();
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());
        ButterKnife.bind(this);
        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();
        shopname = mysharedPreferanceSetup.getShopName();
        logo = mysharedPreferanceSetup.getLogo();

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

        TextView textView = findViewById(R.id.dthana);
        String text = "Already have an account ? Login";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {


                startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                finish();

            }

            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setUnderlineText(false);
            }

        };
        ss.setSpan(clickableSpan1, 26, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        ss.setSpan(new BackgroundColorSpan(Color.WHITE), 26, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        ss.setSpan(new ForegroundColorSpan(Color.rgb(243, 156, 38)), 26, 31, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView.setText(ss);
        textView.setMovementMethod(LinkMovementMethod.getInstance());


        checkbox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkbox.isChecked()){
                    mybtn.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.signup_round_btn_orenge));
                }else {
                    mybtn.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.signup_round_btn_gray));
                }

            }
        });



        mybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String phonee = phone.getText().toString();
                String namee = name.getText().toString();
                String addresss = address.getText().toString();
                String email = emailaddress.getText().toString();


                //input string
                String firstThreeNumber = "";     //substring containing first 4 characters

                if (namee.equals("")){
                    name.setError("Enter Your Name");
                    name.requestFocus();
                    return;
                }

                if (phonee.length() == 11) {
                    firstThreeNumber = phonee.substring(0, 3);

                    if ((firstThreeNumber.contains("017") || (firstThreeNumber.contains("019")) || (firstThreeNumber.contains("018")) || (firstThreeNumber.contains("016")) || (firstThreeNumber.contains("013")) || (firstThreeNumber.contains("015")))) {


                        if (checkbox.isChecked()){


                            ModelUsers modelUsers = new ModelUsers();
                            modelUsers.setMobile(""+phonee);
                            modelUsers.setName(""+namee);
                            modelUsers.setAddress(""+addresss);
                            modelUsers.setEmail(""+email);
                            //  modelUsers.setEmail("ss@gmail.com");


                            createAccount(modelUsers,uniqueCode);
                        }else {
                            Toast.makeText(SignUpActivity.this, "You must Agree to terms and condition", Toast.LENGTH_SHORT).show();
                        }



                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid Phone Number", Toast.LENGTH_SHORT).show();
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
        });


        gohome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUpActivity.this,DrawerMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });

        TextView textView2 = findViewById(R.id.termsandcondition);
        String text2 = "By using this app, you agree to the Terms and Condition";

        SpannableString sss = new SpannableString(text2);

        ClickableSpan clickableSpan2 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {
                showTermsDialog();

            }

            @Override
            public void updateDrawState(final TextPaint textPaint) {
                textPaint.setUnderlineText(false);
            }

        };
        sss.setSpan(clickableSpan2, 35, 55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        sss.setSpan(new BackgroundColorSpan(Color.WHITE), 35, 55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        sss.setSpan(new ForegroundColorSpan(Color.rgb(243, 156, 38)), 35, 55, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);

        textView2.setText(sss);
        textView2.setMovementMethod(LinkMovementMethod.getInstance());

    }

    /* Terms and condition dialog */
    private void showTermsDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(SignUpActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_terms, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(SignUpActivity.this);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        TextView close = view.findViewById(R.id.close);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        alertDialog.show();

    }

    private void createAccount(ModelUsers modelUsers,String uniqueCode) {


        compositeDisposable.add(ApiClint.getInstance().createAccount(""+uniqueCode, modelUsers)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelUsers>>() {
                    @Override
                    public void onNext(final Response<ModelUsers> response) {

                   //     Toast.makeText(SignUpActivity.this, ""+response.body().getMsg()+" "+response.body().getPassword(), Toast.LENGTH_LONG).show();

                        /*sharedPreferance.setUserId(""+response.body().getUser_id());
                        sharedPreferance.setName(""+response.body().getName());
                        sharedPreferance.setAddress(""+response.body().getAddress());
                        sharedPreferance.setMobile(""+response.body().getUsername());
                        sharedPreferance.setAnotherPhone(""+response.body().getPhone());
                        sharedPreferance.setEmail(""+response.body().getEmail());*/

                        String userid = ""+response.body().getUser_id();
                        String name = ""+response.body().getName();
                        String address = ""+response.body().getAddress();
                        String anothernumber = ""+response.body().getPhone();
                        String email = ""+response.body().getEmail();

                        Intent intent = new Intent(SignUpActivity.this,OTPWebActivity.class);
                        intent.putExtra("otpcode",""+response.body().getPassword());
                        intent.putExtra("phone",""+response.body().getUsername());
                        intent.putExtra("userid",""+userid);
                        intent.putExtra("name",""+name);
                        intent.putExtra("address",""+address);
                        intent.putExtra("anothernumber",""+anothernumber);
                        intent.putExtra("email",""+email);
                        startActivity(intent);


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