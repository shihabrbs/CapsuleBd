package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.rightbrain.officeproject.adapter.SearchAdapter;
import com.rightbrain.officeproject.adapter.ShopNameAdapter;
import com.rightbrain.officeproject.databinding.ActivityAllCategoryBinding;
import com.rightbrain.officeproject.databinding.ActivitySearchBinding;
import com.rightbrain.officeproject.model.ModelCartRoom;
import com.rightbrain.officeproject.model.ModelProducts;
import com.rightbrain.officeproject.model.ModelSetup;
import com.rightbrain.officeproject.retrofit.ApiClint;
import com.rightbrain.officeproject.room.CartRepository;
import com.rightbrain.officeproject.room.ItemClickListenerShop;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.rightbrain.officeproject.model.ModelProducts.GRID_VIEW_LAYOUT;
import static com.rightbrain.officeproject.model.ModelProducts.LARGE_VIEW_LAYOUT;
import static com.rightbrain.officeproject.model.ModelProducts.LIST_VIEW_LAYOUT;

public class SearchActivity extends AppCompatActivity implements ItemClickListenerShop {
    ActivitySearchBinding binding;
    ArrayList<ModelProducts> searchProductList = new ArrayList<>();
    SearchAdapter searchAdapter;
    CompositeDisposable compositeDisposable;
    SpinKitView spinKitView;
    AlertDialog alertDialog;
    ArrayList<ModelSetup> setupArrayList = new ArrayList<>();
    ShopNameAdapter shopNameAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySearchBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        compositeDisposable = new CompositeDisposable();
        spinKitView = findViewById(R.id.spin_kit_login);
        String searchText = getIntent().getStringExtra("searchdata");
        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        String uniqueCode = mysharedPreferanceSetup.getUniqueCode();
        binding.etSearch.setText(""+searchText);
        searchProduct(""+searchText,uniqueCode);


        setCartnumber();

        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String text = binding.etSearch.getText().toString();

                    searchProduct(text,uniqueCode);
                    return true;
                }
                return false;
            }
        });

        binding.imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = binding.etSearch.getText().toString();

                searchProduct(text,uniqueCode);
            }
        });

    }

    public void btn_back(View view) {
      /*  Intent intent = new Intent(SearchActivity.this,DrawerMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);*/
        onBackPressed();
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    private void searchProduct(String keyword,String uniqueCode) {
        MysharedPreferance mysharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());
        String viewType = mysharedPreferance.getViewType();

        if(viewType.equals("grid")){
            searchAdapter = new SearchAdapter(this,searchProductList,GRID_VIEW_LAYOUT);
            binding.recycerviewSearch.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));

        }else if (viewType.equals("large")){
            searchAdapter = new SearchAdapter(this,searchProductList,LARGE_VIEW_LAYOUT);
            binding.recycerviewSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        }
        else {
            searchAdapter = new SearchAdapter(this,searchProductList,LIST_VIEW_LAYOUT);
            binding.recycerviewSearch.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));

        }



        compositeDisposable.add(ApiClint.getInstance().productSearch(""+uniqueCode,""+keyword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                    @Override
                    public void onNext(final Response<List<ModelProducts>> response) {

                        searchProductList.clear();
                        searchProductList.addAll(response.body());
                        searchAdapter.notifyDataSetChanged();
                        binding.recycerviewSearch.setAdapter(searchAdapter);

                        if (searchProductList.size() > 0){
                            binding.searchlayoutid.setVisibility(View.VISIBLE);
                            spinKitView.setVisibility(View.GONE);
                        }else {
                            binding.searchlayoutid.setVisibility(View.GONE);
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

    public void btn_home(View view) {
        Intent intent = new Intent(SearchActivity.this,DrawerMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void btn_Category(View view) {
        Intent intent = new Intent(SearchActivity.this,AllCategoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void btn_offer(View view) {
        Intent intent = new Intent(SearchActivity.this,OfferActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void btn_account(View view) {

        MysharedPreferance mysharedPreferance = MysharedPreferance.getPreferences(SearchActivity.this);
        String userid = ""+mysharedPreferance.getUserId();

        if (userid.equals("")){
            Intent intent = new Intent(SearchActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }else {
            Intent intent = new Intent(SearchActivity.this, UserActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

    public void btn_menu(View view) {
        showTermsDialog();
    }

    private void showTermsDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(SearchActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_shop_type, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(SearchActivity.this);

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

    public void cartbtn(View view) {
        Intent intent = new Intent(SearchActivity.this, CartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }



    @Override
    public void onClickShop(View view, int position) {
        shopNameAdapter.SetPositionShop(position);
        shopNameAdapter.notifyDataSetChanged();
    }
}