package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.bumptech.glide.Glide;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.adapter.OrderDetailsAdapter;
import com.rightbrain.officeproject.adapter.PaymentProductDetailsAdapter;
import com.rightbrain.officeproject.databinding.ActivityInvoiceBinding;
import com.rightbrain.officeproject.model.ModelCartRoom;
import com.rightbrain.officeproject.model.ordermodel.ModelItems;
import com.rightbrain.officeproject.model.ordermodel.ModelOrder;
import com.rightbrain.officeproject.model.ordermodel.ModelOrderItems;
import com.rightbrain.officeproject.model.ordermodel.ModelPaymentInfo;
import com.rightbrain.officeproject.model.ordermodel.ModelUserInfo;
import com.rightbrain.officeproject.model.ordermodel.OrderItem;
import com.rightbrain.officeproject.retrofit.ApiClint;
import com.rightbrain.officeproject.retrofit.ApiClintarray;
import com.rightbrain.officeproject.retrofit.ApiInterface;
import com.rightbrain.officeproject.room.CartRepository;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class InvoiceActivity extends AppCompatActivity {
    ActivityInvoiceBinding binding;
    CartRepository repository;
    ApiInterface apiInterface;
    PaymentProductDetailsAdapter paymentProductDetailsAdapter;
    ArrayList<ModelCartRoom> carts = new ArrayList<>();

    List<ModelOrderItems> modelOrderItems;
    List<OrderItem> orderItemArrayList;
    OrderDetailsAdapter orderDetailsAdapter;

    String totaltaka="";
    String invoiceid="";
    CompositeDisposable compositeDisposable;
    ArrayList<ModelItems> modelItemsarray;
    String uniqueCode,deliveryfee,currency;
    String logo;
    String shopname;
    String id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityInvoiceBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();
        deliveryfee = mysharedPreferanceSetup.getShippingCharge();
        currency = mysharedPreferanceSetup.getCurrency();
        shopname = mysharedPreferanceSetup.getShopName();
        logo = mysharedPreferanceSetup.getLogo();

        compositeDisposable = new CompositeDisposable();
        modelOrderItems  = new ArrayList<>();
        orderItemArrayList  = new ArrayList<>();

        Intent intent = getIntent();
        totaltaka = intent.getStringExtra("total");
        invoiceid = intent.getStringExtra("invoice");
        id = intent.getStringExtra("id");

        binding.textView21.setText("Invoice : #"+invoiceid);

        if (logo.equals("")){
            binding.imageView4.setVisibility(View.GONE);
            binding.tvApp.setVisibility(View.VISIBLE);
        }else {
            binding.imageView4.setVisibility(View.VISIBLE);
            binding.tvApp.setVisibility(View.GONE);

            Glide
                    .with(getApplicationContext())
                    .load("http://"+logo)
                    .into(binding.imageView4);

        }
        binding.tvApp.setText(""+shopname);


        modelItemsarray = new ArrayList<>();
        repository = new CartRepository(getApplicationContext());


        orderDetailsAdapter = new OrderDetailsAdapter(getApplicationContext(),orderItemArrayList);
        binding.recyclerView2.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        binding.recyclerView2.setAdapter(orderDetailsAdapter);
        getOrderDetails();
        //  changebgcolor(2);



      //  getData();
    }

   /* public void getData() {

        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                carts.clear();
                carts.addAll(modelCartRooms);
                paymentProductDetailsAdapter.notifyDataSetChanged();
                binding.recyclerView2.setAdapter(paymentProductDetailsAdapter);

            }
        });
    }*/

    public void btn_home(View view) {
        Intent intent = new Intent(InvoiceActivity.this,DrawerMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);


    }

    void importentmethod(){
        apiInterface = ApiClintarray.getClient().create(ApiInterface.class);

        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> cartList) {

                try {

                   /* JSONArray jsonArrayMain = new JSONArray();
                    JSONObject jsonObjectMain = new JSONObject();
                    jsonObjectMain.put("name","shihab");
                    jsonObjectMain.put("phone","01761632579");
                    jsonArrayMain.put(jsonObjectMain);*/
                   /* JSONArray jsonArray = new JSONArray();
                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("english", "shihab");
                    jsonObject.put("bangla", "b shihab");
                    jsonArray.put(jsonObject);*/
                    JSONArray jsonArray = new JSONArray();
                    ModelItems modelItems = new ModelItems();

                    for (ModelCartRoom cart : cartList) {

                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("p_name", cart.getName());
                        jsonObject.put("p_price", cart.getPrice());
                        jsonObject.put("quantity", cart.getQuantity());
                        jsonObject.put("url", cart.getUrl());
                        jsonObject.put("size", cart.getSize());
                        jsonObject.put("productId", cart.getProductId());
                        jsonArray.put(jsonObject);


                        modelItems.setItemId(""+cart.getProductId());
                        modelItems.setOrderId("133");
                        modelItems.setName(""+cart.getName());
                        modelItems.setPrice(""+cart.getPrice());
                        modelItems.setQuantity(""+cart.getQuantity());
                        modelItems.setSize(""+cart.getSize());
                        modelItems.setColor(""+cart.getColor());
                        modelItems.setSubTotal("");
                        modelItems.setUrl(""+cart.getUrl());

                        modelItemsarray.add(modelItems);
                    }
                    /*jsonObjectMain.put("data",jsonArray);*/


                    /*ArrayList<Modeljson> alldata = new ArrayList<>();
                    Modeljson modeljson = new Modeljson();
                    modeljson.setPhone("01761632579");
                    modeljson.setItem(cartList);

                    alldata.add(modeljson);*/



                    ArrayList<ModelOrder> alldata = new ArrayList<>();
                    ArrayList<ModelUserInfo> userinfo = new ArrayList<>();
                    ArrayList<ModelPaymentInfo> paymentInfo = new ArrayList<>();
                    /* ArrayList<ModelItems> modelItemsarray = new ArrayList<>();*/

/*
                    ModelItems modelItems = new ModelItems();
                    modelItems.setItemId("2345");
                    modelItems.setOrderId("133");
                    modelItems.setName("itemname");
                    modelItems.setUnitPrice("23");
                    modelItems.setQuantity("3");
                    modelItems.setSize("25");
                    modelItems.setColor("red");
                    modelItems.setSubTotal("450");
                    modelItems.setUrl("image_url");

                    modelItemsarray.add(modelItems);*/


                    ModelUserInfo modelUserInfo = new ModelUserInfo();
                    modelUserInfo.setName("shihab");
                    modelUserInfo.setMobile("0111112222");
                    modelUserInfo.setAddress("Dhanmondi");
                    //  modelUserInfo.setLocation("Dhaka");

                    userinfo.add(modelUserInfo);

                    ModelPaymentInfo modelPaymentInfo = new ModelPaymentInfo();
                    /*modelPaymentInfo.setAccountno("01521414587");
                    modelPaymentInfo.setPaymentmobile("01761632579");
                    modelPaymentInfo.setTrxid("s34fh42gV31");
                    modelPaymentInfo.setPreferent("10:00");
                    modelPaymentInfo.setSubtotal("330");
                    modelPaymentInfo.setDeliverycharge("50");
                    modelPaymentInfo.setTotal("380");*/
                    modelPaymentInfo.setId("1271");
                    modelPaymentInfo.setUserId("25");
                    modelPaymentInfo.setSubTotal("400");
                    modelPaymentInfo.setDiscount("200");
                    modelPaymentInfo.setVat("0");
                    modelPaymentInfo.setTotal("1000");
                    modelPaymentInfo.setShippingCharge("100");
                    modelPaymentInfo.setCouponCode("24csd3f23");
                    modelPaymentInfo.setTimePeriod("10.30 AM");
                    modelPaymentInfo.setDeliveryDate("2021-06-01");
                    modelPaymentInfo.setTransactionMethod("cash");
                    modelPaymentInfo.setDiscountCoupon("134354");
                    modelPaymentInfo.setBankAccount("");
                    modelPaymentInfo.setMobileBankAccount("");
                    modelPaymentInfo.setPaymentMobile("");
                    modelPaymentInfo.setPaymentCard("");
                    modelPaymentInfo.setPaymentCardNo("");
                    modelPaymentInfo.setTransactionId("");
                    modelPaymentInfo.setRemark("");

                    paymentInfo.add(modelPaymentInfo);

                    ModelOrder modelOrder = new ModelOrder();
                    modelOrder.setUserinfo(userinfo);
                    modelOrder.setPaymentinfo(paymentInfo);
                    modelOrder.setItem(cartList);

                    alldata.add(modelOrder);




                    // Log.e("JSONArray", new Gson().toJson(alldata));


                  /*  Log.e("JSONArray1", new Gson().toJson(paymentInfo));
                    Log.e("JSONArray2", new Gson().toJson(modelItemsarray));
                    Log.e("JSONArray3", new Gson().toJson(userinfo));*/






                    /*compositeDisposable.addAll(
                            ApiClint.getInstance().postOrderFields("UBE49VBB",new Gson().toJson(paymentInfo),new Gson().toJson(modelItemsarray),new Gson().toJson(userinfo))
                                    .delay(2, TimeUnit.SECONDS)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribeWith(new DisposableObserver<Response<ResponseBody>>() {
                                        @Override
                                        public void onNext(final Response<ResponseBody> response) {

                                            int code = response.code();
                                            Toast.makeText(InvoiceActivity.this, ""+response.message(), Toast.LENGTH_SHORT).show();



                                            *//*if (response.isSuccessful()) {


                                                Toast.makeText(InvoiceActivity.this, "Success", Toast.LENGTH_SHORT).show();
                                                //After insert order it is remove all the cart item

                                                repository.deleteAll();


                                            } else {

                                            }*//*
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    }));*/



                   /* compositeDisposable.addAll(
                            ApiClint.getInstance().postOrderFields("UBE49VBB",new Gson().toJson(userinfo),new Gson().toJson(paymentInfo),new Gson().toJson(cartList))
                                    .delay(2, TimeUnit.SECONDS)
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribeOn(Schedulers.io())
                                    .subscribeWith(new DisposableObserver<Response<ResponseBody>>() {
                                        @Override
                                        public void onNext(final Response<ResponseBody> response) {

                                            if (response.isSuccessful()) {


                                                //After insert order it is remove all the cart item

                                                repository.deleteAll();


                                            } else {

                                            }
                                        }

                                        @Override
                                        public void onError(Throwable e) {
                                        }

                                        @Override
                                        public void onComplete() {

                                        }
                                    }));
*/

                    /*apiInterface.postOrderFields(new Gson().toJson(userinfo),new Gson().toJson(paymentInfo)).enqueue(new Callback<Response<ResponseBody>>() {
                        @Override
                        public void onResponse(Call<Response<ResponseBody>> call, Response<Response<ResponseBody>> response) {

                        }

                        @Override
                        public void onFailure(Call<Response<ResponseBody>> call, Throwable t) {

                        }
                    });
*/


                  /*  apiInterface.postOrder(alldata).enqueue(new Callback<Modeljson>() {
                        @Override
                        public void onResponse(Call<Modeljson> call, Response<Modeljson> response) {
                            if ( response.body().getMessage().equals("Succesful")){
                                Toast.makeText(InvoiceActivity.this, "Order Succesful", Toast.LENGTH_LONG).show();
                            }else {
                                Toast.makeText(InvoiceActivity.this, "Unsuccesful", Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Modeljson> call, Throwable t) {
                            Toast.makeText(InvoiceActivity.this, "Unsuccesful", Toast.LENGTH_LONG).show();
                        }
                    });*/

                } catch (JSONException jse) {
                    jse.printStackTrace();
                }

            }
        });

    }

    private void getOrderDetails() {
        compositeDisposable.add(ApiClint.getInstance().orderDetails(""+uniqueCode, ""+id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelOrderItems>>() {
                    @Override
                    public void onNext(final Response<ModelOrderItems> response) {



                        binding.subtotalid.setText(currency+" "+response.body().getSubTotal());
                        binding.totalid.setText(currency+" "+deliveryfee);
                       // int total = Integer.parseInt(""+response.body().getTotal()) + Integer.parseInt(deliveryfee);
                        binding.deliveryfeeid.setText(currency+" "+response.body().getTotal());

                        orderItemArrayList.addAll(response.body().getOrderItem());
                        orderDetailsAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Response", ""+e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(InvoiceActivity.this,DrawerMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    public void btn_orderpage(View view) {
        Intent intent = new Intent(InvoiceActivity.this,OrdersActivity.class);
        startActivity(intent);
    }

    public void btn_myhome(View view) {
        Intent intent = new Intent(InvoiceActivity.this,UserActivity.class);
        startActivity(intent);
    }
}