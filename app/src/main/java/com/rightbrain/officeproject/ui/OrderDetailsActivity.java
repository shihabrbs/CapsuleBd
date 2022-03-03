package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;

import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.adapter.OrderDetailsAdapter;
import com.rightbrain.officeproject.databinding.ActivityOrderDetailsBinding;
import com.rightbrain.officeproject.model.ModelCartRoom;
import com.rightbrain.officeproject.model.ordermodel.ModelOrderItems;
import com.rightbrain.officeproject.model.ordermodel.OrderItem;
import com.rightbrain.officeproject.retrofit.ApiClint;
import com.rightbrain.officeproject.room.CartRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class OrderDetailsActivity extends AppCompatActivity {

    ActivityOrderDetailsBinding binding;
    CompositeDisposable compositeDisposable;
    String uniqueCode;
    List<ModelOrderItems> modelOrderItems;
    List<OrderItem> orderItemArrayList;
    OrderDetailsAdapter orderDetailsAdapter;
    ModelOrderItems modelOrderItem;
    String id;
    String userid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrderDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        compositeDisposable = new CompositeDisposable();
        modelOrderItems = new ArrayList<>();
        orderItemArrayList = new ArrayList<>();

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();

        MysharedPreferance mysharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());
        userid = "" + mysharedPreferance.getUserId();

        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        String status = intent.getStringExtra("status");
        String invoice = intent.getStringExtra("invoice");
        String date = intent.getStringExtra("date");
        String deliverymethod = intent.getStringExtra("deliverymethod");

       /* if (deliverymethod.equals("true")){
            Toast.makeText(this, "Cash on delivery", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "Online payment", Toast.LENGTH_SHORT).show();
        }*/

        binding.textView44.setText("Order Id : #" + invoice);
        binding.textView46.setText("" + date);

        binding.textView45.setText("" + status);

        if (status.equals("created")) {
            binding.textView45.setTextColor(Color.parseColor("#2e2e2e"));
        } else if (status.equals("wfc")) {
            binding.textView45.setBackgroundColor(Color.parseColor("#A303A9F4"));
            binding.textView45.setTextColor(Color.parseColor("#FFFFFF"));

        } else if (status.equals("confirm")) {
            binding.textView45.setBackgroundColor(Color.parseColor("#FF9800"));
            binding.textView45.setTextColor(Color.parseColor("#FFFFFF"));
        } else {
            binding.textView45.setBackgroundColor(Color.parseColor("#F44336"));
            binding.textView45.setTextColor(Color.parseColor("#FFFFFF"));
        }


        orderDetailsAdapter = new OrderDetailsAdapter(getApplicationContext(), orderItemArrayList);
        binding.recyclerView2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        binding.recyclerView2.setAdapter(orderDetailsAdapter);
        getOrderDetails();

    }

    public void btn_back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }


    private void getOrderDetails() {
        compositeDisposable.add(ApiClint.getInstance().orderDetails("1586331178", "" + id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelOrderItems>>() {
                    @Override
                    public void onNext(final Response<ModelOrderItems> response) {

                        Log.e("Response", "" + response.message());

                        String method = response.body().getMethod();

                        if (method.equals("")) {
                            binding.textView49.setVisibility(View.GONE);
                            binding.textView50.setVisibility(View.GONE);
                        } else {
                            binding.textView49.setVisibility(View.VISIBLE);
                            binding.textView50.setVisibility(View.VISIBLE);
                        }

                        binding.textView48.setText("" + response.body().getAddress());
                        binding.textView50.setText("" + response.body().getMethod());
                        binding.textView66.setText("" + response.body().getCreatedTime());
                        binding.textView57.setText("৳" + response.body().getSubTotal());
                        binding.textView58.setText("৳" + response.body().getShippingCharge());
                        binding.textView59.setText("৳" + response.body().getTotal());

                        // modelOrderItems.addAll(response.body());
                        /*for(int i=0;i<modelOrderItems.size();i++){
                            orderItemArrayList.addAll(modelOrderItems.get(i).getOrderItem());
                        }*/

                        //  Toast.makeText(getApplicationContext(), ""+response.body().getOrderItem().size(), Toast.LENGTH_SHORT).show();

                        orderItemArrayList.addAll(response.body().getOrderItem());
                        orderDetailsAdapter.notifyDataSetChanged();

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e("Response", "" + e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }

    public void btn_delete(View view) {

        showDeleteConfermation();

    }

    public void btn_reOrder(View view) {


        showTermsDialog();


    }

    /* Confermation dialog */
    private void showTermsDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(OrderDetailsActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_change_shop_confirm, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetailsActivity.this);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button yesbtn = view.findViewById(R.id.btn_yes);
        Button nobtn = view.findViewById(R.id.btn_no);
        ImageView close = view.findViewById(R.id.closebtn);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        yesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                reOrder();
            }
        });

        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });

        alertDialog.show();

    }

    /*Delete Confermation dialog */
    private void showDeleteConfermation() {
        LayoutInflater layoutInflater = LayoutInflater.from(OrderDetailsActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_order_delete, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(OrderDetailsActivity.this);

        builder.setView(view);

        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        Button yesbtn = view.findViewById(R.id.btn_yes);
        Button nobtn = view.findViewById(R.id.btn_no);
        ImageView close = view.findViewById(R.id.closebtn);


        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        yesbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                deleteOrder(id);
            }
        });

        nobtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();

            }
        });

        alertDialog.show();

    }

    private void deleteOrder(String id) {

        compositeDisposable.add(ApiClint.getInstance().orderDelete("" + uniqueCode, "" + id, "" + userid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelOrderItems>>() {
                    @Override
                    public void onNext(final Response<ModelOrderItems> response) {


                        // onBackPressed();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

        Intent intent = new Intent(OrderDetailsActivity.this, OrdersActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        //  finish();

    }

    public void reOrder() {
        CartRepository repository = new CartRepository(getApplicationContext());

        for (int i = 0; i < orderItemArrayList.size(); i++) {

            repository.insertSingleData(new ModelCartRoom(

                    "" + orderItemArrayList.get(i).getName(),
                    "" + orderItemArrayList.get(i).getPrice(),
                    "" + orderItemArrayList.get(i).getQuantity(),
                    "" + orderItemArrayList.get(i).getImagePath(),
                    "" + orderItemArrayList.get(i).getSize(),
                    "" + orderItemArrayList.get(i).getColor(),
                    "" + orderItemArrayList.get(i).getItemId()));

        }

        Intent intent = new Intent(OrderDetailsActivity.this, CartActivity.class);
        startActivity(intent);
        finish();
    }
}