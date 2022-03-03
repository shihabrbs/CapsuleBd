package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.databinding.ActivityMyProfileBinding;
import com.rightbrain.officeproject.model.ModelUserUpdate;
import com.rightbrain.officeproject.model.modelProductDetails.ModelLocation;
import com.rightbrain.officeproject.retrofit.ApiClint;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MyProfileActivity extends AppCompatActivity {
    MysharedPreferance sharedPreferance;
    CompositeDisposable compositeDisposable;
    ActivityMyProfileBinding binding;
    String uniqueCode;
    Spinner spinnerlocation;
    ArrayList location;
    String locationname;
    ArrayList<ModelLocation> locationArrayList = new ArrayList<>();
    ArrayList<String> locationid = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyProfileBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();

        location = new ArrayList();

        compositeDisposable = new CompositeDisposable();
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());

        binding.etName.setClickable(false);
        binding.etPhone.setClickable(false);

        String name = sharedPreferance.getName();
        String mobile = sharedPreferance.getPhone();
        String anothernumber = sharedPreferance.getAnotherPhone();
        String address = sharedPreferance.getAddress();
        String email = sharedPreferance.getemail();

        binding.etName.setText("" + name);
        binding.etPhone.setText("" + mobile);
        binding.etAdditionalnumber.setText("" + anothernumber);
        binding.etAddress.setText("" + address);
        binding.etEmail.setText("" + email);

        get_Location();

    }

    private void get_Location() {
        compositeDisposable.add(ApiClint.getInstance().getOrderLocation("" + uniqueCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelLocation>>>() {
                    @Override
                    public void onNext(final Response<List<ModelLocation>> response) {

                        locationArrayList.addAll(response.body());

                       // location.add("Select Location");

                        for (int i = 0; i < locationArrayList.size(); i++) {
                            location.add(response.body().get(i).getName());
                            locationid.add(""+response.body().get(i).getId());
                        }

                        spinnerlocation = (Spinner) findViewById(R.id.spinnerlocation);

                        ArrayAdapter adapterlocation = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, location);

                        // Specify the layout to use when the list of choices appears
                        adapterlocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                        // Apply the adapter to the spinner
                        spinnerlocation.setAdapter(adapterlocation);

                        try {
                            locationname = locationid.get(0);
                        }catch (Exception e){

                        }


                        spinnerlocation.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            public void onItemSelected(
                                    AdapterView<?> adapterView, View view,
                                    int i, long l) {
                                //  locationname = spinnerlocation.getItemAtPosition(i).toString();
                                locationname = locationid.get(i);


                            }

                            public void onNothingSelected(
                                    AdapterView<?> adapterView) {

                            }
                        });



                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    public void btn_update(View view) {
        String userid = sharedPreferance.getUserId();
        int user_id = Integer.parseInt("" + userid);
        String name = binding.etName.getText().toString();
        String phone = binding.etAdditionalnumber.getText().toString();
        String address = binding.etAddress.getText().toString();
        String email = binding.etEmail.getText().toString();

        ModelUserUpdate modelUserUpdate = new ModelUserUpdate();
        modelUserUpdate.setUserId(user_id);
        modelUserUpdate.setName("" + name);
        modelUserUpdate.setPhone("" + phone);
        modelUserUpdate.setLocation("" + locationname);
        modelUserUpdate.setAddress("" + address);
        modelUserUpdate.setEmail("" + email);

        updateData(modelUserUpdate);
    }

    private void updateData(ModelUserUpdate modelUserUpdate) {

        compositeDisposable.add(ApiClint.getInstance().userUpdate("" + uniqueCode, modelUserUpdate)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelUserUpdate>>() {
                    @Override
                    public void onNext(final Response<ModelUserUpdate> response) {


                        if (response.body().getMsg().equals("valid")) {
                            Toast.makeText(MyProfileActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();

                            sharedPreferance.setName(""+modelUserUpdate.getName());
                            sharedPreferance.setAnotherPhone(""+modelUserUpdate.getPhone());
                            sharedPreferance.setAddress(""+modelUserUpdate.getAddress());
                            sharedPreferance.setEmail(""+modelUserUpdate.getEmail());

                        } else {
                            Toast.makeText(MyProfileActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
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

    public void btn_back(View view) {
        onBackPressed();
    }
}