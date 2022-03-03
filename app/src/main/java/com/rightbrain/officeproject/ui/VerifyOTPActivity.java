package com.rightbrain.officeproject.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseException;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.PhoneAuthCredential;
import com.google.firebase.auth.PhoneAuthProvider;
import com.rightbrain.officeproject.R;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import dmax.dialog.SpotsDialog;
import io.reactivex.disposables.CompositeDisposable;

public class VerifyOTPActivity extends AppCompatActivity {
    @BindView(R.id.resendBtn)
    Button resendBtn;
    @BindView(R.id.verifyBtn)
    Button verifyBtn;

    String cc="";
    String phone = "";
    String userType = "";
    String password = "";

    String shop_name = "";
    String text = "";
    String emailll = "";
    String setup_iddd ="";
    String username ="";

    @BindView(R.id.edtOtp)
    EditText edtOtp;

    CompositeDisposable compositeDisposable;
    AlertDialog alertDialog;
    @BindView(R.id.registerCard)
    CardView registerCard;


    //firebase phone auth
    private String verificationID;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_verify_o_t_p);


        phone = getIntent().getStringExtra("phone");
        cc = getIntent().getStringExtra("cc");



        ButterKnife.bind(this);

        //initializing firebase auth
        mAuth = FirebaseAuth.getInstance();

        compositeDisposable = new CompositeDisposable();
        alertDialog = new SpotsDialog.Builder().setContext(VerifyOTPActivity.this).build();
        sendVerificationCode("+8801701068060");


        edtOtp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString())) {

                    verifyBtn.setEnabled(true);
                    registerCard.setCardBackgroundColor(getResources().getColor(R.color.colorPrimary));

                }else
                {
                    verifyBtn.setEnabled(false);
                    registerCard.setCardBackgroundColor(getResources().getColor(R.color.gray));
                }
            }
        });
    }

    @OnClick(R.id.resendBtn)
    public void onResendBtnClicked() {
        Intent intent = getIntent();
        startActivity(intent);
        finish();
    }

    @OnClick(R.id.verifyBtn)
    public void onVerifyBtnClicked() {

        if (TextUtils.isEmpty(edtOtp.getText().toString())) {
            edtOtp.setError("Please enter OTP code");
            edtOtp.requestFocus();
            return;
        }
        alertDialog.show();

        verifyCode(edtOtp.getText().toString());

    }

    private void verifyCode(String code) {
        try {
            //creating the credential
            PhoneAuthCredential credential = PhoneAuthProvider.getCredential(verificationID, code);
            //signing the user
            signInWithPhoneAuthCredential(credential);
        } catch (Exception e) {
            Toast.makeText(this, "Wrong otp code . Try Again", Toast.LENGTH_SHORT).show();
        }
    }

    private void signInWithPhoneAuthCredential(PhoneAuthCredential phoneAuthCredential) {
        mAuth.signInWithCredential(phoneAuthCredential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {
                             Toast.makeText(VerifyOTPActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                           /* if (userType.equals("single")){
                                singleUser();

                            }
                            if (userType.equals("multi")){
                                multiUser();
                            }*/


                        }
                    }
                });
    }


    private void sendVerificationCode(String number) {
        PhoneAuthProvider.getInstance().verifyPhoneNumber(
                number,        // Phone number to verify
                20,                 // Timeout duration
                TimeUnit.SECONDS,   // Unit of timeout
                this,               // Activity (for callback binding)
                mCallbacks);
    }

    private PhoneAuthProvider.OnVerificationStateChangedCallbacks
            mCallbacks = new PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        @Override
        public void onCodeSent(String s, PhoneAuthProvider.ForceResendingToken forceResendingToken) {
            super.onCodeSent(s, forceResendingToken);

            verificationID = s;
        }

        @Override
        public void onVerificationCompleted(PhoneAuthCredential phoneAuthCredential) {

            String code = phoneAuthCredential.getSmsCode();

            if (code != null) {
                edtOtp.setText(code);

                verifyCode(code);
            }
        }

        @Override
        public void onVerificationFailed(FirebaseException e) {
            Toast.makeText(VerifyOTPActivity.this, ""+e.getMessage(), Toast.LENGTH_LONG).show();
        }

        @Override
        public void onCodeAutoRetrievalTimeOut(String s) {
            super.onCodeAutoRetrievalTimeOut(s);
        }
    };


}