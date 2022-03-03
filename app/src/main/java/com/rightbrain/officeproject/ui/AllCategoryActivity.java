package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.adapter.AllCategoryAdapter;
import com.rightbrain.officeproject.adapter.SearchAdapter;
import com.rightbrain.officeproject.adapter.ShopNameAdapter;
import com.rightbrain.officeproject.databinding.ActivityAllCategoryBinding;
import com.rightbrain.officeproject.model.Category;
import com.rightbrain.officeproject.model.ModelCartRoom;
import com.rightbrain.officeproject.model.ModelProducts;
import com.rightbrain.officeproject.model.ModelSetup;
import com.rightbrain.officeproject.retrofit.ApiClint;
import com.rightbrain.officeproject.retrofit.ApiInterface;
import com.rightbrain.officeproject.room.CartRepository;
import com.rightbrain.officeproject.room.ItemClickListenerShop;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class AllCategoryActivity extends AppCompatActivity implements ItemClickListenerShop {
    ActivityAllCategoryBinding binding;
    CompositeDisposable compositeDisposable;
    ApiInterface apiInterface;
    ArrayList<Category> productList;
    AllCategoryAdapter allCategoryAdapter;
    SpinKitView spinKitView;
    ArrayList<ModelProducts> searchProductList = new ArrayList<>();
    SearchAdapter searchAdapter;
    AlertDialog alertDialog;
    ArrayList<ModelSetup> setupArrayList = new ArrayList<>();
    ShopNameAdapter shopNameAdapter;
    String uniqueCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllCategoryBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();

        compositeDisposable = new CompositeDisposable();
        productList = new ArrayList<>();
        spinKitView = findViewById(R.id.spin_kit_login);
        allCategoryAdapter = new AllCategoryAdapter(this,productList);
        // binding.recyclerViewCategory.setLayoutManager(new GridLayoutManager(getContext(),1));
        binding.recyclerViewcategory.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));

        setCartnumber();
        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String text = binding.etSearch.getText().toString();

                    Intent intent = new Intent(AllCategoryActivity.this,SearchActivity.class);
                    intent.putExtra("searchdata",""+text);
                    startActivity(intent);
                    //searchProduct(text);
                    return true;
                }
                return false;
            }
        });

        binding.imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = binding.etSearch.getText().toString();
                Intent intent = new Intent(AllCategoryActivity.this,SearchActivity.class);
                intent.putExtra("searchdata",""+text);
                startActivity(intent);
              //  searchProduct(text);
            }
        });



        compositeDisposable.add(ApiClint.getInstance().getCategory(""+uniqueCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<Category>>>() {
                    @Override
                    public void onNext(final Response<List<Category>> response) {

                        productList.addAll(response.body());
                        allCategoryAdapter.notifyDataSetChanged();
                        binding.recyclerViewcategory.setAdapter(allCategoryAdapter);
                        spinKitView.setVisibility(View.GONE);

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


    public void categorybtn(View view) {
    }

    private void setCartnumber() {
        CartRepository repository = new CartRepository(this);

        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                if (modelCartRooms.size()==0){
                    binding.textView42.setVisibility(View.GONE);
                }else {
                    binding.textView42.setVisibility(View.VISIBLE);
                    if(modelCartRooms.size()>99){
                        binding.textView42.setText("99+");
                    }else {
                        binding.textView42.setText(""+modelCartRooms.size());
                    }


                }
            }
        });
    }


    public void cartbtn(View view) {
        Intent intent = new Intent(AllCategoryActivity.this,CartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void btn_menu(View view) {

        showTermsDialog();

    }

    public void btn_home(View view) {
        Intent intent = new Intent(AllCategoryActivity.this,DrawerMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void btn_Category(View view) {

    }

    public void btn_account(View view) {

        MysharedPreferance mysharedPreferance = MysharedPreferance.getPreferences(AllCategoryActivity.this);
        String userid = ""+mysharedPreferance.getUserId();

        if (userid.equals("")){
            Intent intent = new Intent(AllCategoryActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }else {
            Intent intent = new Intent(AllCategoryActivity.this, UserActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
       /*
        Intent intent = new Intent(AllCategoryActivity.this,LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);*/

    }

    public void btn_offer(View view) {
        Intent intent = new Intent(AllCategoryActivity.this,OfferActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    /* Shop Type dialog */
    private void showTermsDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(AllCategoryActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_shop_type, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(AllCategoryActivity.this);

        builder.setView(view);



        alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        Button close = view.findViewById(R.id.savebtnid);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewShop);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });

        shopNameAdapter = new ShopNameAdapter(this,setupArrayList);
        // binding.recyclerViewCategory.setLayoutManager(new GridLayoutManager(getContext(),1));
        recyclerView.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
        shopNameAdapter.SetItemClickListenerShop(this);




        compositeDisposable.add(ApiClint.getInstance().getShop("UBE49VBB")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelSetup>>>() {
                    @Override
                    public void onNext(final Response<List<ModelSetup>> response) {

                        setupArrayList.clear();
                        setupArrayList.addAll(response.body());
                        shopNameAdapter.notifyDataSetChanged();
                        recyclerView.setAdapter(shopNameAdapter);





                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));


        alertDialog.show();

    }



    @Override
    public void onClickShop(View view, int position) {
        shopNameAdapter.SetPositionShop(position);
        shopNameAdapter.notifyDataSetChanged();
    }
}