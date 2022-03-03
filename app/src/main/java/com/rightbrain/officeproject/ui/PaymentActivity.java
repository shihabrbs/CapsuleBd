package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import com.github.ybq.android.spinkit.SpinKitView;
import com.google.gson.Gson;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.adapter.PaymentProductDetailsAdapter;
import com.rightbrain.officeproject.databinding.ActivityDrawerMainBinding;
import com.rightbrain.officeproject.databinding.ActivityPaymentBinding;
import com.rightbrain.officeproject.model.ModelCartRoom;
import com.rightbrain.officeproject.model.ModelTimeSpinner;
import com.rightbrain.officeproject.model.modelProductDetails.ModelLocation;
import com.rightbrain.officeproject.model.modelProductDetails.ModelTimePeriod;
import com.rightbrain.officeproject.model.ordermodel.ModelItems;
import com.rightbrain.officeproject.model.ordermodel.ModelOrder;
import com.rightbrain.officeproject.model.ordermodel.ModelPaymentInfo;
import com.rightbrain.officeproject.model.ordermodel.ModelUserInfo;
import com.rightbrain.officeproject.model.ordermodel.Modelstatus;
import com.rightbrain.officeproject.retrofit.ApiClint;
import com.rightbrain.officeproject.retrofit.ApiInterface;
import com.rightbrain.officeproject.room.CartRepository;
import com.rightbrain.officeproject.utils.Common;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class PaymentActivity extends AppCompatActivity {
    boolean isBrandExpanded = false;
    boolean isPrefTimeExpanded = false;
    boolean isOrderDetailsExpanded = false;
   // boolean isPaymentExpanded = true;
    ActivityPaymentBinding binding;
    Switch swth;
    Spinner spinner, spinner2, spinnerlocation;
    ArrayList timename, paymentname, location;

    ArrayList<ModelTimeSpinner> timespinner;
    String totaltaka = "";
    MysharedPreferance sharedPreferance;
    CompositeDisposable compositeDisposable;
    CartRepository repository;
    ApiInterface apiInterface;
    PaymentProductDetailsAdapter paymentProductDetailsAdapter;
    ArrayList<ModelCartRoom> carts = new ArrayList<>();
    ArrayList<ModelTimePeriod> timePeriods = new ArrayList<>();
    ArrayList<ModelLocation> locationArrayList = new ArrayList<>();
    int total;
    String mobile;
    String uniqueCode, deliveryfee, currency;
    int count = 1;
    SpinKitView spinKitView;
    String time_id;
    ArrayList<String> locationid = new ArrayList<>();
    String locationname;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPaymentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Intent intent = getIntent();
        totaltaka = intent.getStringExtra("total");
        repository = new CartRepository(getApplicationContext());
        compositeDisposable = new CompositeDisposable();
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());
        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();
        deliveryfee = mysharedPreferanceSetup.getShippingCharge();
        currency = mysharedPreferanceSetup.getCurrency();


        spinKitView = findViewById(R.id.spin_kit_login);


        binding.subtotalid.setText(currency + " " + totaltaka);
        binding.totalid.setText(currency + " " + deliveryfee);
        total = Integer.parseInt(totaltaka) + Integer.parseInt(deliveryfee);
        binding.textView61.setText("Total: " + currency + " " + total);
        binding.deliveryfeeid.setText(currency + " " + total);
        timespinner = new ArrayList();
        timename = new ArrayList();
        location = new ArrayList();
        paymentname = new ArrayList();

        String name = sharedPreferance.getName();
        mobile = sharedPreferance.getPhone();
        String address = sharedPreferance.getAddress();

        binding.etName.setText("" + name);
        binding.etPhone.setText("" + mobile);
        binding.addressid.setText("" + address);

        binding.navBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isBrandExpanded) {


                    isBrandExpanded = true;
                    binding.navBrandRv.setVisibility(View.VISIBLE);
                    Common.toggleArrowRightDown(binding.brandArrow);

                } else {
                    isBrandExpanded = false;
                    Common.toggleArrowRightDown(binding.brandArrow);
                    binding.navBrandRv.setVisibility(View.GONE);
                }
            }
        });

        binding.navPrefTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPrefTimeExpanded) {


                    isPrefTimeExpanded = true;
                    binding.preftimeRv.setVisibility(View.VISIBLE);
                    Common.toggleArrowRightDown(binding.preftimeArrow);

                } else {
                    isPrefTimeExpanded = false;
                    Common.toggleArrowRightDown(binding.preftimeArrow);
                    binding.preftimeRv.setVisibility(View.GONE);
                }
            }
        });

        /*binding.navPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPaymentExpanded) {


                    isPaymentExpanded = true;
                    binding.paymentRv.setVisibility(View.VISIBLE);
                    Common.toggleArrowRightDownOpen(binding.paymentArrow);

                } else {
                    isPaymentExpanded = false;
                    Common.toggleArrowRightDownOpen(binding.paymentArrow);
                    binding.paymentRv.setVisibility(View.GONE);
                }
            }
        });*/


        binding.navOrderDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isOrderDetailsExpanded) {


                    isOrderDetailsExpanded = true;
                    binding.orderDetailsRv.setVisibility(View.VISIBLE);
                    Common.toggleArrowRightDown(binding.orderDetailsArrow);

                } else {
                    isOrderDetailsExpanded = false;
                    Common.toggleArrowRightDown(binding.orderDetailsArrow);
                    binding.orderDetailsRv.setVisibility(View.GONE);
                }
            }
        });


        swth = findViewById(R.id.switch1);

        swth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {


                if (isChecked) {
                    binding.relativeLayout3.setVisibility(View.GONE);
                    binding.paymentRv.setVisibility(View.GONE);

                } else {
                    binding.relativeLayout3.setVisibility(View.VISIBLE);
                    binding.paymentRv.setVisibility(View.VISIBLE);

                }

            }
        });

        get_Location();
        getTime_Period();


        //Payment method
        paymentname.add("0134739372,Bkash");
        paymentname.add("0134739372,Rocket");
        paymentname.add("0134739372,Nagad");

        spinner2 = (Spinner) findViewById(R.id.spinner2);

        ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_spinner_item, paymentname);

// Specify the layout to use when the list of choices appears
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner2.setAdapter(adapter2);


        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        paymentProductDetailsAdapter = new PaymentProductDetailsAdapter(carts, getApplicationContext());
        binding.recyclerView2.setAdapter(paymentProductDetailsAdapter);

        //  changebgcolor(2);


        getData();

        binding.textView62.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                goto_invoicepage();
            }
        });

     /*  if (clicklistener == true){
           binding.textView62.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {

                   binding.textView62.setEnabled(false);
                   Toast.makeText(PaymentActivity.this, "Click", Toast.LENGTH_SHORT).show();
               }
           });
           clicklistener = false;
       }else {
           Toast.makeText(this, "Click hoi na", Toast.LENGTH_SHORT).show();
       }*/


    }

    private void get_Location() {
        compositeDisposable.add(ApiClint.getInstance().getOrderLocation("" + uniqueCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelLocation>>>() {
                    @Override
                    public void onNext(final Response<List<ModelLocation>> response) {

                        locationArrayList.addAll(response.body());



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

                                Toast.makeText(PaymentActivity.this, ""+locationname, Toast.LENGTH_SHORT).show();

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

    private void getTime_Period() {
        compositeDisposable.add(ApiClint.getInstance().getTimePeriod("" + uniqueCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelTimePeriod>>>() {
                    @Override
                    public void onNext(final Response<List<ModelTimePeriod>> response) {

                        timePeriods.addAll(response.body());


                        for (int i = 0; i < timePeriods.size(); i++) {

                            timespinner.add(new ModelTimeSpinner(response.body().get(i).getName(), "" + response.body().get(i).getId()));
                        }

                        for (ModelTimeSpinner helper : timespinner) {

                            timename.add(helper.getName());

                        }

                        spinner = (Spinner) findViewById(R.id.spinner);

                        ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_spinner_item, timename);

// Specify the layout to use when the list of choices appears
                        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
                        spinner.setAdapter(adapter);

                        try {
                            time_id = timespinner.get(0).getId();
                        }catch (Exception e){

                        }



                        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                            @Override
                            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                                //  String valu = adapterView.getItemAtPosition(i).toString();
                                time_id = timespinner.get(i).getId();

                            }

                            @Override
                            public void onNothingSelected(AdapterView<?> adapterView) {

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
/*
        for (ModelTimePeriod timePeriod : timePeriods) {

            timespinner.add(""+timePeriod.getName());


        }*/

    }

    public void getData() {

        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                carts.clear();
                carts.addAll(modelCartRooms);
                paymentProductDetailsAdapter.notifyDataSetChanged();


            }
        });
    }

    public void btn_back(View view) {
        onBackPressed();
    }

    public void goto_invoicepage() {


        String name = binding.etName.getText().toString();
        String phone = binding.etPhone.getText().toString();
        String address = binding.addressid.getText().toString();
       // String location = spinnerlocation.getSelectedItem().toString();
        /* String preferenttime = spinner.getSelectedItem().toString();*/


        String mobilebankaccount;
        String transactionId;
        String paymentMobile;
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        if (swth.isChecked()) {
            Long tsLong = System.currentTimeMillis() / 1000;
            String randomnumber = tsLong.toString();
            spinKitView.setVisibility(View.VISIBLE);
            mobilebankaccount = "";
            transactionId = "";
            paymentMobile = "";
            binding.textView62.setEnabled(false);
            insertOrder(randomnumber, "0", "" + total, "0", "0", "" + totaltaka,
                    "" + deliveryfee, "", ""+time_id, "" + date,
                    "", "1", "", "",
                    "" + mobilebankaccount, "" + paymentMobile,
                    "", "", "", "", ""+locationname
            );

        } else {

            mobilebankaccount = binding.spinner2.getSelectedItem().toString();
            paymentMobile = binding.paymentNumber.getText().toString();
            transactionId = binding.trxid.getText().toString();
            if (paymentMobile.length() > 10) {


            } else {
                binding.paymentNumber.setError("Insert valid number");
                binding.paymentNumber.requestFocus();
                return;
            }

            if (transactionId.equals("")) {
                binding.trxid.setError("Insert valid Transaction Id");
                binding.trxid.requestFocus();
                return;
            } else {
                Long tsLong = System.currentTimeMillis() / 1000;
                String randomnumber = tsLong.toString();
                spinKitView.setVisibility(View.VISIBLE);
                // Toast.makeText(this, "account: " + mobilebankaccount + " Trx no: " + transactionId, Toast.LENGTH_SHORT).show();
                binding.textView62.setEnabled(false);
                //order method implement here
                String trxid = binding.trxid.getText().toString();
                insertOrder(randomnumber, "0", "" + total, "0", "0", "" + totaltaka,
                        "" + deliveryfee, "", ""+time_id, "" + date,
                        "mobile", "0", "", "",
                        "bkash" + mobilebankaccount, "" + paymentMobile,
                        "", "", "" + trxid, "", ""+locationname
                );

            }

            if (!paymentMobile.equals("") && !transactionId.equals("")) {

            }


        }

        //   Toast.makeText(this, ""+name+" "+phone+" "+address+" Time: "+preferenttime, Toast.LENGTH_SHORT).show();
    }

    public static String getRandomNumberString() {
        // It will generate 9 digit random Number.
        // from 0 to 999999
        Random rnd = new Random();
        int number = rnd.nextInt(999999999);

        // this will convert any number sequence into 6 character.
        return String.format("%09d", number);
    }

    private void insertOrder(String randomid, String userid, String subtotal, String discount,
                             String vat, String total, String deliveryfee, String cuponcode,
                             String timeperiod, String deliverydate, String transactionmode,
                             String cashondelivery, String discoundcoupon, String bankaccount,
                             String mobilebankaccount, String paymentmobile, String paymentcard,
                             String paymentcardno, String transactionid, String remark, String location
    ) {

        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> cartList) {

                try {

                    JSONArray jsonArray = new JSONArray();

                    ArrayList<ModelItems> modelItemsarray = new ArrayList<>();


                    for (ModelCartRoom cart : cartList) {

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("p_name", cart.getName());
                        jsonObject.put("p_price", cart.getPrice());
                        jsonObject.put("quantity", cart.getQuantity());
                        jsonObject.put("url", cart.getUrl());
                        jsonObject.put("size", cart.getSize());
                        jsonObject.put("productId", cart.getProductId());
                        jsonArray.put(jsonObject);

                        jsonArray.put(jsonObject);

                        ModelItems modelItems = new ModelItems();
                        modelItems.setId("" + randomid + "" + count);
                        modelItems.setItemId("" + cart.getProductId());
                        modelItems.setOrderId("" + randomid);
                        modelItems.setName("" + cart.getName());
                        modelItems.setPrice("" + cart.getPrice());
                        modelItems.setQuantity("" + cart.getQuantity());
                        modelItems.setSize("" + cart.getSize());
                        modelItems.setColor("" + cart.getColor());
                        modelItems.setSubTotal("");
                        modelItems.setUrl("" + cart.getUrl());
                        count++;

                        modelItemsarray.add(modelItems);

                    }
/*

                    for (int i=0;i<cartList.size();i++){

                        modelItems.setItemId("" + cartList.get(i).getProductId());
                        modelItems.setOrderId("1323");
                        modelItems.setName("" + cartList.get(i).getName());
                        modelItems.setUnitPrice("" + cartList.get(i).getPrice());
                        modelItems.setQuantity("" + cartList.get(i).getQuantity());
                        modelItems.setSize("" + cartList.get(i).getSize());
                        modelItems.setColor("" + cartList.get(i).getColor());
                        modelItems.setSubTotal("");
                        modelItems.setUrl("" + cartList.get(i).getUrl());

                        modelItemsarray.add(modelItems);
                    }
*/


                    ArrayList<ModelOrder> alldata = new ArrayList<>();
                    ArrayList<ModelUserInfo> userinfo = new ArrayList<>();
                    ArrayList<ModelPaymentInfo> paymentInfo = new ArrayList<>();


                    ModelUserInfo modelUserInfo = new ModelUserInfo();
                    modelUserInfo.setName("" + sharedPreferance.getName());
                    modelUserInfo.setMobile("" + mobile);
                    modelUserInfo.setLocation("" + location);
                    modelUserInfo.setAddress("" + binding.addressid.getText().toString());
                    modelUserInfo.setUserId("" + sharedPreferance.getUserId());

                    sharedPreferance.setAddress("" + binding.addressid.getText().toString());

                    userinfo.add(modelUserInfo);

                    ModelPaymentInfo modelPaymentInfo = new ModelPaymentInfo();


                    modelPaymentInfo.setId("" + randomid);
                    modelPaymentInfo.setUserId("" + sharedPreferance.getUserId());
                    modelPaymentInfo.setSubTotal("" + subtotal);
                    modelPaymentInfo.setDiscount("" + discount);
                    modelPaymentInfo.setVat("" + vat);
                    modelPaymentInfo.setTotal("" + total);
                    modelPaymentInfo.setShippingCharge("" + deliveryfee);
                    modelPaymentInfo.setCouponCode("" + cuponcode);
                    modelPaymentInfo.setTimePeriod("" + timeperiod);
                    modelPaymentInfo.setDeliveryDate("" + deliverydate);
                    modelPaymentInfo.setTransactionMethod("" + transactionmode);
                    modelPaymentInfo.setCashOnDelivery("" + cashondelivery);
                    modelPaymentInfo.setDiscountCoupon("" + discoundcoupon);
                    modelPaymentInfo.setBankAccount("" + bankaccount);
                    modelPaymentInfo.setMobileBankAccount("" + mobilebankaccount);
                    modelPaymentInfo.setPaymentMobile("" + paymentmobile);
                    modelPaymentInfo.setPaymentCard("" + paymentcard);
                    modelPaymentInfo.setPaymentCardNo("" + paymentcardno);
                    modelPaymentInfo.setTransactionId("" + transactionid);
                    modelPaymentInfo.setRemark("" + remark);

                    paymentInfo.add(modelPaymentInfo);

                    ModelOrder modelOrder = new ModelOrder();
                    modelOrder.setUserinfo(userinfo);
                    modelOrder.setPaymentinfo(paymentInfo);
                    modelOrder.setItem(cartList);

                    alldata.add(modelOrder);

                    Log.e("JSONArray3", new Gson().toJson(userinfo));
                    Log.e("JSONArray1", new Gson().toJson(paymentInfo));
                    Log.e("JSONArray2", new Gson().toJson(modelItemsarray));


                    compositeDisposable.addAll(
                            ApiClint.getInstance().postOrderFields("" + uniqueCode, new Gson().toJson(paymentInfo), new Gson().toJson(modelItemsarray), new Gson().toJson(userinfo))
                                    .delay(2, TimeUnit.SECONDS)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribeWith(new DisposableObserver<Response<Modelstatus>>() {
                                        @Override
                                        public void onNext(final Response<Modelstatus> response) {


                                            if (response.body().getStatus().equals("success")) {
                                                repository.deleteAll();
                                                spinKitView.setVisibility(View.GONE);
                                                Intent intent = new Intent(PaymentActivity.this, InvoiceActivity.class);
                                                intent.putExtra("total", totaltaka);
                                                intent.putExtra("invoice", "" + response.body().getInvoice());
                                                intent.putExtra("id", "" + response.body().getOrderId());
                                                startActivity(intent);

                                            }

                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    }));


                } catch (JSONException jse) {
                    jse.printStackTrace();
                }

            }
        });


    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}