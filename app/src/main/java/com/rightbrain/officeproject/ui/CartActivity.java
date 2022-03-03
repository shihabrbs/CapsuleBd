package com.rightbrain.officeproject.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextPaint;
import android.text.TextWatcher;
import android.text.method.LinkMovementMethod;
import android.text.style.BackgroundColorSpan;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.chaos.view.PinView;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.adapter.CartAdapter;
import com.rightbrain.officeproject.databinding.ActivityCartBinding;
import com.rightbrain.officeproject.model.ModelCartRoom;
import com.rightbrain.officeproject.model.ModelUsers;
import com.rightbrain.officeproject.retrofit.ApiClint;
import com.rightbrain.officeproject.retrofit.ApiInterface;
import com.rightbrain.officeproject.room.CartRepository;
import com.rightbrain.officeproject.room.OnDataSend;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class CartActivity extends AppCompatActivity implements OnDataSend {

    ArrayList<String> list;
    RecyclerView recyclerView;
    CartAdapter adapter;
    ConstraintLayout constraintLayout, checkoutbtn;
    LinearLayout constraintLayoutPrice;
    TextView textView, subtotal;
    int j;
    String color;
    CompositeDisposable compositeDisposable;
    String totaltaka = "";
    MysharedPreferance sharedPreferance;
    AlertDialog alertDialoglogin;
    CartRepository repository;
    //  AdapterCart adapterCart;
    ApiInterface apiInterface;
    ArrayList<ModelCartRoom> carts = new ArrayList<>();
    ActivityCartBinding binding;
    int intsub = 0;
    int total = 0;
    int i = 0;
    String  curtnum ;
    String uniqueCode;
    String logo;
    String shopname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_cart);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();
        shopname = mysharedPreferanceSetup.getShopName();
        logo = mysharedPreferanceSetup.getLogo();

        compositeDisposable = new CompositeDisposable();
        list = new ArrayList<>();
        list.add("name1");
        list.add("name2");
        list.add("name3");
        list.add("name4");
        list.add("name5");
        list.add("name6");
        list.add("name7");
        list.add("name8");

        repository = new CartRepository(getApplicationContext());
        recyclerView = findViewById(R.id.recyclerView);
        subtotal = findViewById(R.id.textView16);

        constraintLayout = findViewById(R.id.constraintLayout);
        constraintLayoutPrice = findViewById(R.id.constraintLayout4);
        checkoutbtn = findViewById(R.id.checkoutbtn);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        adapter = new CartAdapter(carts, getApplicationContext(), repository, this);
        recyclerView.setAdapter(adapter);

        //  changebgcolor(2);

        setCartnumber();
        getData();


    }

    private void setCartnumber() {
        CartRepository  repository = new CartRepository(this);

        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                if (modelCartRooms.size()==0){
                   // binding.textView66.setVisibility(View.GONE);
                }else {
                   // binding.textView66.setVisibility(View.VISIBLE);
                    if(modelCartRooms.size()>99){
                    //    binding.textView66.setText("("+"99+"+")");
                    }else {
                    //    binding.textView66.setText("("+modelCartRooms.size()+")");
                      curtnum = ""+modelCartRooms.size();
                    }


                }
            }
        });
    }


    public void getData() {

        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                carts.clear();
                carts.addAll(modelCartRooms);
                adapter.notifyDataSetChanged();

                if (carts.isEmpty()) {
                    constraintLayoutPrice.setVisibility(View.GONE);
                    binding.constraintLayoutempty.setVisibility(View.VISIBLE);
                } else {
                    constraintLayoutPrice.setVisibility(View.VISIBLE);
                    binding.constraintLayoutempty.setVisibility(View.GONE);
                }

               /*
                //  binding.recyclerView2.setAdapter(adapterCart);

                if (modelCartRooms.size() == 0) {
                    binding.constraintLayout4.setVisibility(View.GONE);
                    binding.emptyConstant.setVisibility(View.VISIBLE);
                *//*emptyimage.setVisibility(View.VISIBLE);

                emptyimage.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            Intent intent = new Intent(CartActivity.this, MainActivity.class);
                            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        onBackPressed();
                    }
                });*//*

                } else {
                    binding.constraintLayout4.setVisibility(View.VISIBLE);
                    binding.emptyConstant.setVisibility(View.GONE);
                    // emptyimage.setVisibility(View.GONE);
                }*/


            }
        });
    }

   /* private void changebgcolor(int a) {
        j=a;
        if (j == 1) {
            color = "#FFC107";

            DrawableCompat.setTint(checkoutbtn.getBackground(), ContextCompat.getColor(getApplicationContext(), R.color.orenge));
          //  DrawableCompat.setTint(myimage.getBackground(), ContextCompat.getColor(getApplicationContext(), R.color.orenge));
         //   DrawableCompat.setTint(floatbg.getBackground(), ContextCompat.getColor(getApplicationContext(), R.color.orenge));


        } else if (j == 2) {
            color = "#03A9F4";
            DrawableCompat.setTint(checkoutbtn.getBackground(), ContextCompat.getColor(getApplicationContext(),R.color.blue));
           //  DrawableCompat.setTint(myimage.getBackground(), ContextCompat.getColor(getApplicationContext(), R.color.blue));
          //  DrawableCompat.setTint(floatbg.getBackground(), ContextCompat.getColor(getApplicationContext(), R.color.blue));

        }

        constraintLayout.setBackgroundColor(Color.parseColor(color));
        getTheme().applyStyle(R.style.OverlayPrimaryColorRed, true);
    }*/

    /*@Override
    public Resources.Theme getTheme() {
        Resources.Theme theme = super.getTheme();
        int i = 2;
        if (i == 1) {
            theme.applyStyle(R.style.DefaultTheme, true);


        } else if (i == 2) {
            theme.applyStyle(R.style.OverlayPrimaryColorRed, true);


        }

        return theme;
    }*/

    @Override
    public void totalPrice(String subtotal) {

        binding.textView61.setText("Total: " + "৳ " + subtotal+" | "+curtnum);
        totaltaka = "" + subtotal;
        /*int total = Integer.parseInt(subtotal);
        Locale locale = new Locale("bn","BD");
        NumberFormat numberFormat = NumberFormat.getCurrencyInstance(locale);
        binding.textView16.setText(numberFormat.format(total));*/
    }

    public void btn_Checkout(View view) {

        String userid = "" + sharedPreferance.getUserId();

        if (userid.equals("")) {

            /*Intent intent = new Intent(CartActivity.this,LoginActivity.class);
            intent.putExtra("activity","cart");
            startActivity(intent);*/
            showTermsDialog();

        } else {

            Intent intent = new Intent(CartActivity.this, PaymentActivity.class);
            intent.putExtra("total", totaltaka);
            startActivity(intent);
        }


    }

    /* Login & Sign dialog */
    private void showTermsDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(CartActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_login, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);

        builder.setView(view);

        alertDialoglogin = builder.create();
        alertDialoglogin.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialoglogin.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialoglogin.setCancelable(false);
        alertDialoglogin.setCanceledOnTouchOutside(false);

        ImageView imagelogo = view.findViewById(R.id.img_home);
        TextView appname = view.findViewById(R.id.appnametv);
        ImageView close = view.findViewById(R.id.closebtn);
        Button btnLogin = view.findViewById(R.id.button_login);
        EditText phone = view.findViewById(R.id.edit_sign_in_username);
        TextView textView = view.findViewById(R.id.dthana);


        if (logo.equals("")){
            imagelogo.setVisibility(View.GONE);
            appname.setVisibility(View.VISIBLE);
        }else {
            imagelogo.setVisibility(View.VISIBLE);
            appname.setVisibility(View.GONE);

            Glide
                    .with(getApplicationContext())
                    .load("http://"+logo)
                    .into(imagelogo);

        }
        appname.setText(""+shopname);


        String text = "Don't have an account ? SignUp";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {

                alertDialoglogin.dismiss();
                showSignupDialog();


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

        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialoglogin.dismiss();
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phonee = phone.getText().toString();


                //input string
                String firstThreeNumber = "";     //substring containing first 4 characters

                if (phonee.length() == 11) {
                    firstThreeNumber = phonee.substring(0, 3);
                    //      Toast.makeText(this, ""+firstThreeNumber, Toast.LENGTH_SHORT).show();
                    if ((firstThreeNumber.contains("017") || (firstThreeNumber.contains("019")) || (firstThreeNumber.contains("018")) || (firstThreeNumber.contains("016")) || (firstThreeNumber.contains("013")) || (firstThreeNumber.contains("015")))) {

                        ModelUsers modelUsers = new ModelUsers();
                        modelUsers.setMobile("" + phonee);
                        loginAccount(modelUsers);


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

        alertDialoglogin.show();

    }

    private void loginAccount(ModelUsers modelUsers) {


        compositeDisposable.add(ApiClint.getInstance().loginAccount(""+uniqueCode, modelUsers)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelUsers>>() {
                    @Override
                    public void onNext(final Response<ModelUsers> response) {


                       /* Intent intent = new Intent(CartActivity.this,OTPWebActivity.class);
                        intent.putExtra("otpcode",""+response.body().getPassword());
                        intent.putExtra("phone",""+response.body().getMobile());
                        startActivity(intent);*/

                        String status = ""+response.body().getMsg();
                        if (status.equals("invalid")){
                            Toast.makeText(CartActivity.this, "This number is not registered", Toast.LENGTH_LONG).show();
                        }else {

                            String otpcode = ""+response.body().getPassword();
                            String mobile = ""+response.body().getUsername();

                            String userid = ""+response.body().getUser_id();
                            String name = ""+response.body().getName();
                            String address = ""+response.body().getAddress();
                            String anothernumber = ""+response.body().getPhone();
                            String email = ""+response.body().getEmail();
                            showOtpDialog(otpcode,mobile,userid,name,address,anothernumber,email);
                        }



                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    /*Sign dialog */
    private void showSignupDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(CartActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_signup, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        ImageView close = view.findViewById(R.id.closebtn);

        ImageView imagelogo = view.findViewById(R.id.img_home);
        TextView appname = view.findViewById(R.id.appnametv);

        Button btnLogin = view.findViewById(R.id.button_login);
        EditText name = view.findViewById(R.id.name);
        EditText phone = view.findViewById(R.id.mobile_number);
        EditText address = view.findViewById(R.id.addressid);
        EditText emailaddress = view.findViewById(R.id.emailaddress);
        TextView textView = view.findViewById(R.id.dthana);
        TextView textView2 = view.findViewById(R.id.termsandcondition);
        TextView mybtn = view.findViewById(R.id.textView3);
        CheckBox checkBox = view.findViewById(R.id.checkBox);

        if (logo.equals("")){
            imagelogo.setVisibility(View.GONE);
            appname.setVisibility(View.VISIBLE);
        }else {
            imagelogo.setVisibility(View.VISIBLE);
            appname.setVisibility(View.GONE);

            Glide
                    .with(getApplicationContext())
                    .load("http://"+logo)
                    .into(imagelogo);

        }
        appname.setText(""+shopname);



        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        String text = "Already have an account ? Login";

        SpannableString ss = new SpannableString(text);

        ClickableSpan clickableSpan1 = new ClickableSpan() {
            @Override
            public void onClick(@NonNull View widget) {


                /*startActivity(new Intent(CartActivity.this, LoginActivity.class));
                finish();*/
                alertDialog.dismiss();
                showTermsDialog();

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


        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (checkBox.isChecked()){
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


                        if (checkBox.isChecked()){


                            ModelUsers modelUsers = new ModelUsers();
                            modelUsers.setMobile(""+phonee);
                            modelUsers.setName(""+namee);
                            modelUsers.setAddress(""+addresss);
                            modelUsers.setEmail(""+email);
                            //  modelUsers.setEmail("ss@gmail.com");

                            createAccount(modelUsers);
                        }else {
                            Toast.makeText(CartActivity.this, "You must Agree to terms and condition", Toast.LENGTH_SHORT).show();
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


        alertDialog.show();

    }

    private void createAccount(ModelUsers modelUsers) {


        compositeDisposable.add(ApiClint.getInstance().createAccount(""+uniqueCode, modelUsers)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelUsers>>() {
                    @Override
                    public void onNext(final Response<ModelUsers> response) {

                        //     Toast.makeText(SignUpActivity.this, ""+response.body().getMsg()+" "+response.body().getPassword(), Toast.LENGTH_LONG).show();

                       /* Intent intent = new Intent(CartActivity.this,OTPWebActivity.class);
                        intent.putExtra("otpcode",""+response.body().getPassword());
                        intent.putExtra("phone",""+response.body().getMobile());
                        startActivity(intent);*/

                        String otpcode = ""+response.body().getPassword();
                        String mobile = ""+response.body().getUsername();



                        String userid = ""+response.body().getUser_id();
                        String name = ""+response.body().getName();
                        String address = ""+response.body().getAddress();
                        String anothernumber = ""+response.body().getPhone();
                        String email = ""+response.body().getEmail();

                        showOtpDialog(otpcode,mobile,userid,name,address,anothernumber,email);


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }


    /*Otp dialog */
    private void showOtpDialog(String otpcode, String mobile,String userid,String name,String address,String anothernumber,String email) {

        LayoutInflater layoutInflater = LayoutInflater.from(CartActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_otp, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(CartActivity.this);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        alertDialog.setCancelable(false);
        alertDialog.setCanceledOnTouchOutside(false);

        ImageView close = view.findViewById(R.id.closebtn);
        PinView otpedt = view.findViewById(R.id.edtOtp);
        Button btnverify = view.findViewById(R.id.verifyBtn);

        alertDialoglogin.dismiss();

        btnverify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String getcode = otpedt.getText().toString();
                if (getcode.equals(otpcode)){
                    sharedPreferance.setPhone(""+mobile);
                    sharedPreferance.setUserId(""+userid);
                    sharedPreferance.setName(""+name);
                    sharedPreferance.setAddress(""+address);
                    sharedPreferance.setMobile(""+mobile);
                    sharedPreferance.setAnotherPhone(""+anothernumber);
                    sharedPreferance.setEmail(""+email);

                    alertDialog.dismiss();

                    Intent intent = new Intent(getApplicationContext(),PaymentActivity.class);
                    intent.putExtra("total", totaltaka);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(), "OTP Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        otpedt.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence str, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence str, int start, int before, int count) {

            }

            @SuppressLint("ResourceAsColor")
            @Override
            public void afterTextChanged(Editable str) {
                if (str.toString().trim().length() == 4) {
                    btnverify.setClickable(true);
                    btnverify.setTextColor(Color.parseColor("#ffffff"));
                    btnverify.setBackgroundColor(Color.parseColor("#03A9F4"));



                } else {
                    btnverify.setClickable(false);
                    btnverify.setTextColor(Color.parseColor("#9E9797"));
                    btnverify.setBackgroundColor(Color.parseColor("#CACACA"));
                }
            }
        });


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });


 /*       btncart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

                Toast.makeText(CartActivity.this, "click", Toast.LENGTH_SHORT).show();

                String getcode = otpedt.getText().toString();

                if (getcode.equals("")){
                    Toast.makeText(getApplicationContext(), "Please insert OTP", Toast.LENGTH_SHORT).show();
                }else {

                    if (getcode.equals(otpcode)){
                        sharedPreferance.setPhone(""+mobile);
                        alertDialog.dismiss();

                    }else{
                        Toast.makeText(getApplicationContext(), "OTP Failed", Toast.LENGTH_SHORT).show();
                    }

                }

            }
        });*/

        alertDialog.show();

    }

    public void btn_back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    public void btn_home(View view) {
        Intent intent = new Intent(CartActivity.this,DrawerMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}