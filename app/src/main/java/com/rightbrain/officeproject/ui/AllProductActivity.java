package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.adapter.AllProductAdapter;
import com.rightbrain.officeproject.databinding.ActivityAllBrandBinding;
import com.rightbrain.officeproject.model.ModelProducts;
import com.rightbrain.officeproject.retrofit.ApiClint;
import com.rightbrain.officeproject.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AllProductActivity extends AppCompatActivity {

    ActivityAllBrandBinding binding;
    CompositeDisposable compositeDisposable;
    ApiInterface apiInterface;
    ArrayList<ModelProducts> productList;
    AllProductAdapter allProductAdapter;
    String uniqueCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllBrandBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       // String catid = getIntent().getStringExtra("catid");
        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();

        compositeDisposable = new CompositeDisposable();
        productList = new ArrayList<>();
        allProductAdapter = new AllProductAdapter(this,productList);
        // binding.recyclerViewCategory.setLayoutManager(new GridLayoutManager(getContext(),1));
        binding.recyclerViewcategory.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));




        compositeDisposable.add(ApiClint.getInstance().getProduct(""+uniqueCode,"2087")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                    @Override
                    public void onNext(final Response<List<ModelProducts>> response) {

                        productList.addAll(response.body());
                        allProductAdapter.notifyDataSetChanged();
                        binding.recyclerViewcategory.setAdapter(allProductAdapter);

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
    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}