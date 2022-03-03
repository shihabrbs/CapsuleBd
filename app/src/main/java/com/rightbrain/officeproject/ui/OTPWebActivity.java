package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.chaos.view.PinView;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.R;

public class OTPWebActivity extends AppCompatActivity {

    PinView editTextotp;
    Button verifybtn;
    MysharedPreferance sharedPreferance;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_o_t_p_web);
        editTextotp = findViewById(R.id.edtOtp);
        verifybtn = findViewById(R.id.verifyBtn);
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());


        editTextotp.addTextChangedListener(new TextWatcher() {
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
                    verifybtn.setClickable(true);
                    verifybtn.setTextColor(Color.parseColor("#ffffff"));
                    verifybtn.setBackgroundColor(Color.parseColor("#03A9F4"));
                    verifybtn.setBackgroundResource(R.drawable.btn_orenge);


                } else {
                    verifybtn.setClickable(false);
                    verifybtn.setTextColor(Color.parseColor("#000000"));
                    verifybtn.setBackgroundResource(R.drawable.btn_gray);
                }
            }
        });

        
    }

    public void btnverify(View view) {
        String getcode = editTextotp.getText().toString();
        
        if (getcode.equals("")){
            Toast.makeText(this, "Please insert OTP", Toast.LENGTH_SHORT).show();
        }else {
            String otp_code = getIntent().getStringExtra("otpcode");
            String mobile = getIntent().getStringExtra("phone");
            String userid = getIntent().getStringExtra("userid");
            String name = getIntent().getStringExtra("name");
            String address = getIntent().getStringExtra("address");
            String anothernumber = getIntent().getStringExtra("anothernumber");
            String email = getIntent().getStringExtra("email");

            if (getcode.equals(otp_code)){
                sharedPreferance.setPhone(""+mobile);
                sharedPreferance.setUserId(""+userid);
                sharedPreferance.setName(""+name);
                sharedPreferance.setAddress(""+address);
                sharedPreferance.setAnotherPhone(""+anothernumber);
                sharedPreferance.setEmail(""+email);
                Intent intent = new Intent(OTPWebActivity.this,DrawerMainActivity.class);
                startActivity(intent);

            }else{
                Toast.makeText(this, "OTP Failed", Toast.LENGTH_SHORT).show();
            }

        }
        
        
        
    }
}