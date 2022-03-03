package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.TextView;

import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.adapter.DiscountAdapter;
import com.rightbrain.officeproject.adapter.OfferAdapter;
import com.rightbrain.officeproject.databinding.ActivityOfferBinding;
import com.rightbrain.officeproject.model.ModelAll;
import com.rightbrain.officeproject.model.ModelCartRoom;
import com.rightbrain.officeproject.model.sidebar.DiscountItem;
import com.rightbrain.officeproject.model.sidebar.SidebarResponse;
import com.rightbrain.officeproject.retrofit.ApiClint;
import com.rightbrain.officeproject.retrofit.ApiInterface;
import com.rightbrain.officeproject.room.CartRepository;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class OfferActivity extends AppCompatActivity {
    ActivityOfferBinding binding;
    ApiInterface apiInterface;
    ArrayList<ModelAll> discountlist;
    DiscountAdapter discountAdapter;
    ArrayList<DiscountItem> discountnamelist;
    CompositeDisposable compositeDisposable;
    OfferAdapter offerAdapter;
    String uniqueCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOfferBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        compositeDisposable = new CompositeDisposable();
        discountnamelist = new ArrayList<>();

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();

        setCartnumber();

        //Discount name
        offerAdapter = new OfferAdapter(this, discountnamelist);
        binding.recyclerViewoffer.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.VERTICAL, false));
        getDiscountName();

        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String text = binding.etSearch.getText().toString();
                    Intent intent = new Intent(OfferActivity.this,SearchActivity.class);
                    intent.putExtra("searchdata",""+text);
                    startActivity(intent);
                    // searchProduct(text);
                    return true;
                }
                return false;
            }
        });

    }



    public void discountbtn(View view) {

    }

    public void cartbtn(View view) {
        Intent intent = new Intent(OfferActivity.this,CartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void btn_search(View view) {
        String text = binding.etSearch.getText().toString();
        Intent intent = new Intent(OfferActivity.this,SearchActivity.class);
        intent.putExtra("searchdata",""+text);
        startActivity(intent);
    }

    public void btn_home(View view) {
        Intent intent = new Intent(OfferActivity.this,DrawerMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void btn_Category(View view) {
        Intent intent = new Intent(OfferActivity.this,AllCategoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void btn_account(View view) {

        MysharedPreferance mysharedPreferance = MysharedPreferance.getPreferences(OfferActivity.this);
        String userid = ""+mysharedPreferance.getUserId();

        if (userid.equals("")){
            Intent intent = new Intent(OfferActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }else {
            Intent intent = new Intent(OfferActivity.this, UserActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

    public void btn_offer(View view) {

    }

    private void getDiscountName() {
        compositeDisposable.add(ApiClint.getInstance().getSidebarItems(""+uniqueCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<Response<SidebarResponse>>() {
                    @Override
                    public void onSuccess(Response<SidebarResponse> sidebarResponseResponse) {

                        if (sidebarResponseResponse.isSuccessful()) {
                            if (sidebarResponseResponse.body() != null) {

                                discountnamelist.addAll(sidebarResponseResponse.body().getDiscount());

                                offerAdapter.notifyDataSetChanged();
                                binding.recyclerViewoffer.setAdapter(offerAdapter);


                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
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

    public void btn_back(View view) {
        onBackPressed();
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}