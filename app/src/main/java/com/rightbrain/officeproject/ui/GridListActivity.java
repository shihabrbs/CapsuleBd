package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.adapter.GridListAdapter;
import com.rightbrain.officeproject.databinding.ActivityGridListBinding;
import com.rightbrain.officeproject.model.ModelProducts;
import com.rightbrain.officeproject.retrofit.ApiClint;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.rightbrain.officeproject.model.ModelProducts.GRID_VIEW_LAYOUT;
import static com.rightbrain.officeproject.model.ModelProducts.LIST_VIEW_LAYOUT;

public class GridListActivity extends AppCompatActivity {
ActivityGridListBinding binding;
    CompositeDisposable compositeDisposable;
    List<ModelProducts> modelProducts;
    GridListAdapter gridListAdapter;
    ModelProducts modelProduct;
    MysharedPreferance mysharedPreferance;
    boolean flag = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGridListBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mysharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());

        String product_view = ""+mysharedPreferance.getViewType();

        compositeDisposable = new CompositeDisposable();
        modelProducts = new ArrayList<>();

        getData();


        if (product_view.equals("grid")){
            gridListAdapter = new GridListAdapter(getApplicationContext(),modelProducts,GRID_VIEW_LAYOUT);
            binding.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));


        }else {

            gridListAdapter = new GridListAdapter(getApplicationContext(),modelProducts,LIST_VIEW_LAYOUT);
            binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false));


        }


  /*      ArrayList<ModelGrideList> modelProductsList = new ArrayList<>();
        modelProductsList.add(new ModelGrideList(GRID_VIEW_LAYOUT,"arnaf"));
        modelProductsList.add(new ModelGrideList(LIST_VIEW_LAYOUT,"arshan"));
        modelProductsList.add(new ModelGrideList(LIST_VIEW_LAYOUT,"a"));
        modelProductsList.add(new ModelGrideList(GRID_VIEW_LAYOUT,"v"));
        modelProductsList.add(new ModelGrideList(GRID_VIEW_LAYOUT,"eeee"));


         gridListAdapter = new GridListAdapter(getApplicationContext(),modelProducts,GRID_VIEW_LAYOUT);
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false));
*/


        binding.change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == false){

                    mysharedPreferance.setViewType("grid");
                    String product_view = ""+mysharedPreferance.getViewType();
                    getData();


                    if (product_view.equals("grid")){
                        gridListAdapter = new GridListAdapter(getApplicationContext(),modelProducts,GRID_VIEW_LAYOUT);
                        binding.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));


                    }else {

                        gridListAdapter = new GridListAdapter(getApplicationContext(),modelProducts,LIST_VIEW_LAYOUT);
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false));


                    }
                    flag = true;
                }else{
                    mysharedPreferance.setViewType("list");
                    String product_view = ""+mysharedPreferance.getViewType();
                    getData();


                    if (product_view.equals("grid")){
                        gridListAdapter = new GridListAdapter(getApplicationContext(),modelProducts,GRID_VIEW_LAYOUT);
                        binding.recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));


                    }else {

                        gridListAdapter = new GridListAdapter(getApplicationContext(),modelProducts,LIST_VIEW_LAYOUT);
                        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false));


                    }
                    flag = false;


                }
            }
        });


    }

    private void getData() {
            compositeDisposable.add(ApiClint.getInstance().getProduct("UBE49VBB", "2085")
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribeOn(Schedulers.io())
                    .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                        @Override
                        public void onNext(final Response<List<ModelProducts>> response) {

                            modelProducts.clear();
                            modelProducts.addAll(response.body());
                            gridListAdapter.notifyDataSetChanged();
                            binding.recyclerView.setAdapter(gridListAdapter);

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