package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.adapter.AllCategoryAdapter;
import com.rightbrain.officeproject.adapter.DiscountAdapter;
import com.rightbrain.officeproject.adapter.FeatureBrandAdapter;
import com.rightbrain.officeproject.adapter.PromotionAdapter;
import com.rightbrain.officeproject.databinding.ActivityMainBinding;
import com.rightbrain.officeproject.model.Category;
import com.rightbrain.officeproject.model.ModelAll;
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


public class MainActivity extends AppCompatActivity {
    CompositeDisposable compositeDisposable;
    ActivityMainBinding binding;
    ConstraintLayout constraintLayout, floatbg;
    ConstraintLayout myimage;
    ImageView cart_img;
    TextView textView;
    int j;
    String color;
    ApiInterface apiInterface;
    RecyclerView recyclerViewcategory,recyclerViewPopular;
    ArrayList<Category> productList,popularProduct;
    ArrayList<Category> featureCategoryList;
    ArrayList<Category> featurePromotionList;
    ArrayList<Category> featureBrandList;
    ArrayList<ModelProducts> featureProductList;
    ArrayList<ModelAll> discountlist;
    AllCategoryAdapter allCategoryAdapter;
    FeatureBrandAdapter featureBrandAdapter;
    PromotionAdapter allfeaturespromotionAdapter;
    FeatureProductAdapter featureProductAdapter;
    DiscountAdapter discountAdapter;
    ImageSlider imageSlider;

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        compositeDisposable = new CompositeDisposable();
        myimage = findViewById(R.id.myimageview);
        cart_img = findViewById(R.id.cartimg);
        textView = findViewById(R.id.textView2);
        floatbg = findViewById(R.id.floatingbtn);
        recyclerViewcategory = findViewById(R.id.recyclercategory);
     //   recyclerViewPopular = findViewById(R.id.recyclerPopuler);


        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String text = binding.etSearch.getText().toString();
                    Toast.makeText(MainActivity.this, ""+text, Toast.LENGTH_SHORT).show();
                    return true;
                }
                return false;
            }
        });

        constraintLayout = findViewById(R.id.constraintLayout);


        featureCategoryList = new ArrayList<>();
        featureBrandList = new ArrayList<>();
        featurePromotionList = new ArrayList<>();
        featureProductList = new ArrayList<>();
        productList = new ArrayList<>();
        popularProduct = new ArrayList<>();


        allCategoryAdapter = new AllCategoryAdapter(this,productList);
        /*binding.recyclercategory.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));*/
        binding.recyclercategory.setLayoutManager(new LinearLayoutManager(getApplication(),LinearLayoutManager.HORIZONTAL,false));
        getFeature_category();


      /*  allfeaturespromotionAdapter = new PromotionAdapter(this,featurePromotionList,this);
        *//* binding.featurePromotion.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));*//*
        binding.recyclercategory.setLayoutManager(new LinearLayoutManager(getApplication(),LinearLayoutManager.HORIZONTAL,false));
        getPromotion();*/


    /*    featureBrandAdapter = new FeatureBrandAdapter(this,featureBrandList);
        binding.featureBrands.setLayoutManager(new GridLayoutManager(getApplicationContext(),4));
        *//*   binding.recyclercategory.setLayoutManager(new LinearLayoutManager(getApplication(),LinearLayoutManager.HORIZONTAL,false));
         *//*
        getFeatureBrands();
*/

        featureProductAdapter = new FeatureProductAdapter(this,featureProductList);
        binding.featureProduct.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        /*   binding.recyclercategory.setLayoutManager(new LinearLayoutManager(getApplication(),LinearLayoutManager.HORIZONTAL,false));
         */
        getFeatureProduct();





        getFeature_Slider();

        imageSlider = findViewById(R.id.img_slider);

        changebgcolor(2);


    }

   /* private void getFeatureBrands() {
        compositeDisposable.add(ApiClint.getInstance().getFeatureBrand("UBE49VBB")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<Category>>>() {
                    @Override
                    public void onNext(final Response<List<Category>> response) {
                        featureBrandList.addAll(response.body());
                        featureBrandAdapter.notifyDataSetChanged();
                        binding.featureBrands.setAdapter(featureBrandAdapter);


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }*/

    private void getFeatureProduct(){

        compositeDisposable.add(ApiClint.getInstance().getFeatureProduct("UBE49VBB","category","2051")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                    @Override
                    public void onNext(final Response<List<ModelProducts>> response) {

                        featureProductList.addAll(response.body());
                        featureProductAdapter.notifyDataSetChanged();
                        binding.featureProduct.setAdapter(featureProductAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private void getFeature_category(){

        compositeDisposable.add(ApiClint.getInstance().getFeatureCategory("UBE49VBB")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<Category>>>() {
                    @Override
                    public void onNext(final Response<List<Category>> response) {
                        productList.addAll(response.body());
                        allCategoryAdapter.notifyDataSetChanged();
                        binding.recyclercategory.setAdapter(allCategoryAdapter);


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private void getFeature_Slider() {

        compositeDisposable.add(ApiClint.getInstance().getFeatureSlider("UBE49VBB")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<Category>>>() {
                    @Override
                    public void onNext(final Response<List<Category>> response) {

                        featureCategoryList.addAll(response.body());

                        Toast.makeText(MainActivity.this, ""+response.body().get(0).getName(), Toast.LENGTH_SHORT).show();

                        sliderImage();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }




    private void getPromotion() {

        compositeDisposable.add(ApiClint.getInstance().getFeaturePromotion("UBE49VBB")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<Category>>>() {
                    @Override
                    public void onNext(final Response<List<Category>> response) {

                        featurePromotionList.addAll(response.body());
                        allfeaturespromotionAdapter.notifyDataSetChanged();
                        binding.featurePromotion.setAdapter(allfeaturespromotionAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));

    }


    private void sliderImage() {

        List<SlideModel> slideModels = new ArrayList<>();
        // slideModels.add(new SlideModel(imgurl1,"1 Image"));

        for(int i=0;i<featureCategoryList.size();i++){
            slideModels.add(new SlideModel("http://"+featureCategoryList.get(i).getImagePath()));
        }
      /*  slideModels.add(new SlideModel("https://image.shutterstock.com/z/stock-vector-pepperoni-pizza-banner-ads-on-chalk-board-background-with-tomatoes-and-basil-leaves-d-illustration-1505158397.jpg","2 Image"));
        slideModels.add(new SlideModel("http://www.terminalbd.com/cache/images/d/5/a/5/7/d5a57afca4176c66fda563b1091ca552fa893085.jpeg","3 Image"));
        slideModels.add(new SlideModel("https://thumbs.dreamstime.com/z/order-pizza-online-banner-mobile-app-templates-concept-fast-free-delivery-service-vector-illustration-flat-cartoon-design-138516640.jpg","4 Image"));
*/

        imageSlider.setImageList(slideModels,true);

        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Toast.makeText(MainActivity.this, "slider position: "+i, Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void changebgcolor(int a) {
        j = a;
        if (j == 1) {
            color = "#FFC107";


            DrawableCompat.setTint(myimage.getBackground(), ContextCompat.getColor(getApplicationContext(), R.color.orenge));
            DrawableCompat.setTint(floatbg.getBackground(), ContextCompat.getColor(getApplicationContext(), R.color.orenge));



        } else if (j == 2) {
            color = "#ffffff";

            DrawableCompat.setTint(myimage.getBackground(), ContextCompat.getColor(getApplicationContext(), R.color.blue));
            DrawableCompat.setTint(floatbg.getBackground(), ContextCompat.getColor(getApplicationContext(), R.color.blue));


        }


        constraintLayout.setBackgroundColor(Color.parseColor(color));
        getTheme().applyStyle(R.style.OverlayPrimaryColorRed, true);
    }

    @Override
    public Resources.Theme getTheme() {
        Resources.Theme theme = super.getTheme();
        int i = 2;
        if (i == 1) {
            theme.applyStyle(R.style.DefaultTheme, true);


        } else if (i == 2) {
            theme.applyStyle(R.style.OverlayPrimaryColorRed, true);


        }

        return theme;
    }



    public void cartbtn(View view) {
        Intent intent = new Intent(MainActivity.this, CartActivity.class);
        intent.putExtra("logic", j);
        startActivity(intent);
    }

    public void cat_see_all(View view) {
        Intent intent = new Intent(MainActivity.this, AllCategoryActivity.class);
        startActivity(intent);
    }

    public void btn_brand(View view) {
        Intent intent = new Intent(MainActivity.this, AllBrandActivity.class);
        startActivity(intent);
    }

    public void btn_products(View view) {
        Intent intent = new Intent(MainActivity.this, AllProductActivity.class);
        startActivity(intent);
    }

    public void btn_more(View view) {
        Toast.makeText(this, "More", Toast.LENGTH_SHORT).show();
    }

    public void btn_offer(View view) {
        Toast.makeText(this, "Offers", Toast.LENGTH_SHORT).show();
    }

    public void promotion_see_all(View view) {

    }

    public void brand_see_all(View view) {

    }
}