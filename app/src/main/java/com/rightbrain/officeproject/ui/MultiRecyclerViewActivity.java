package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.adapter.multiAdapter.MainAdapter;
import com.rightbrain.officeproject.databinding.ActivityDrawerMainBinding;
import com.rightbrain.officeproject.databinding.ActivityMainBinding;
import com.rightbrain.officeproject.databinding.ActivityMultiRecyclerViewBinding;
import com.rightbrain.officeproject.model.multirecycermodel.ModelMain;
import com.rightbrain.officeproject.model.multirecycermodel.Product;
import com.rightbrain.officeproject.retrofit.ApiClint;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MultiRecyclerViewActivity extends AppCompatActivity {
    ActivityMultiRecyclerViewBinding binding;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ArrayList<ModelMain> featureCategoryList;
    ArrayList<Product> allproductsList;
    MainAdapter allCategoryAdapter;
    String uniqueCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMultiRecyclerViewBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();


        featureCategoryList = new ArrayList<>();
        allproductsList = new ArrayList<>();

        allCategoryAdapter = new MainAdapter(getApplicationContext(),featureCategoryList);
        /*binding.recyclercategory.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));*/
        binding.mainrecycerview.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        getFeatureCategory();

    }


    private void getFeatureCategory(){

        compositeDisposable.add(ApiClint.getInstance().getMultiFeatureProduct(""+uniqueCode,"category")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelMain>>>() {
                    @Override
                    public void onNext(final Response<List<ModelMain>> response) {

                        featureCategoryList.addAll(response.body());
                        allCategoryAdapter.notifyDataSetChanged();
                        binding.mainrecycerview.setAdapter(allCategoryAdapter);



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