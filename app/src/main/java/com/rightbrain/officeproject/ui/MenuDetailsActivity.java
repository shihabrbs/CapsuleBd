package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.adapter.menuAdapter.ImageGalleryAdapter;
import com.rightbrain.officeproject.adapter.menuAdapter.SpecificationAdapter;
import com.rightbrain.officeproject.databinding.ActivityMenuDetailsBinding;
import com.rightbrain.officeproject.databinding.ActivityProductDetailsBinding;
import com.rightbrain.officeproject.model.ModelProducts;
import com.rightbrain.officeproject.model.sidebar.Gallery;
import com.rightbrain.officeproject.model.sidebar.ModelMenu;
import com.rightbrain.officeproject.model.sidebar.Specification;
import com.rightbrain.officeproject.retrofit.ApiClint;
import com.rightbrain.officeproject.room.CartRepository;
import com.rightbrain.officeproject.room.RecyclerViewItemClick;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MenuDetailsActivity extends AppCompatActivity implements RecyclerViewItemClick {
    ActivityMenuDetailsBinding binding;
    CompositeDisposable compositeDisposable;
    ArrayList<ModelProducts> productList;
    ImageView imageView;
    TextView textView, price, details, quantity, detext;
    String detailtext = "";
    ConstraintLayout constraintLayout;
    Spinner spinner;
    ArrayList<String> arrayList = new ArrayList<>();
    String image;
    CartRepository repository;

    ImageGalleryAdapter imageGalleryAdapter;
    List<Gallery> modelgalleryarray;
    List<ModelMenu> modelproductdetailsyarray;
    List<Specification> specificationList;

    SpecificationAdapter specificationAdapter;

    String uniqueCode;
String id;
    ShimmerFrameLayout layout;
    Handler handler = new Handler();
    NestedScrollView nestedScrollView;
    ConstraintLayout bottommenu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMenuDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        layout = findViewById(R.id.shimmerlayout);
        nestedScrollView = findViewById(R.id.nestedView);
        bottommenu = findViewById(R.id.myimageview);


        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();


        modelgalleryarray = new ArrayList<>();
        modelproductdetailsyarray = new ArrayList<>();
        specificationList = new ArrayList<>();

        imageView = findViewById(R.id.imageView11);
        textView = findViewById(R.id.textView10);
        price = findViewById(R.id.textView11);
        details = findViewById(R.id.textView15);
        quantity = findViewById(R.id.textView12);
        detext = findViewById(R.id.textView14);
        constraintLayout = findViewById(R.id.detailsid);
        compositeDisposable = new CompositeDisposable();
        id = getIntent().getStringExtra("id");
        String categoryid = getIntent().getStringExtra("catid");
        ArrayList<String> arrayList = new ArrayList<>();




        // getProductDetails(id);
        getProductDetailsWithColor(""+id);

        String s1 = "23,45,42,12,1121";

        String[] array = s1.split(",");


        Collections.addAll(arrayList, array);

      /*  imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailsActivity.this, FullImageActivity.class);
                intent.putExtra("url", "" + image);
                startActivity(intent);
            }
        });


*/







        imageGalleryAdapter = new ImageGalleryAdapter(this, modelgalleryarray);
        binding.recyclerViewGallery.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        imageGalleryAdapter.SetItemClickListenerrr(this);




    }

    private void getProductDetailsWithColor(String id) {
        // "25292"

        compositeDisposable.add(ApiClint.getInstance().getPageMenuDetails(""+uniqueCode, ""+id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelMenu>>() {
                                   @Override
                                   public void onNext(final Response<ModelMenu> response) {
                                       // detailtext = "" + response.body().getContent();





                                       try {
                                           modelgalleryarray.addAll(response.body().getGallery());
                                       }catch (Exception e){

                                       }

                                       try {
                                           specificationList.addAll(response.body().getSpecification());
                                       }catch (Exception e){

                                       }

                                       String description = ""+response.body().getDescription();
                                       String shortdescription = ""+response.body().getShortDescription();

                                       if (shortdescription.equals("null")){
                                          // binding.constraintLayout23.setVisibility(View.GONE);
                                           binding.textView14.setVisibility(View.GONE);

                                       }else {
                                        //  binding.constraintLayout23.setVisibility(View.VISIBLE);
                                           binding.textView14.setVisibility(View.VISIBLE);

                                       }


                                       if (description.equals("null")){
                                          // binding.constraintLayout23.setVisibility(View.GONE);
                                           binding.textView15.setVisibility(View.GONE);

                                       }else {
                                         //  binding.constraintLayout23.setVisibility(View.VISIBLE);
                                           binding.textView15.setVisibility(View.VISIBLE);

                                       }


                                           if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                                               binding.textView15.setText(Html.fromHtml(""+response.body().getDescription(), Html.FROM_HTML_MODE_LEGACY));
                                           } else {
                                               binding.textView15.setText(""+response.body().getDescription());
                                           }

                                       binding.textView14.setText(""+response.body().getShortDescription());


                                       // textView.setText("" + response.body().getName());
                                       binding.textView4.setText("" + response.body().getName());
                                       







                                       if (response.body().getImage().equals("")){
                                           binding.nestedView.setVisibility(View.VISIBLE);
                                           imageView.setVisibility(View.GONE);
                                          /* Glide
                                                   .with(MenuDetailsActivity.this)
                                                   .load("https://www.freeiconspng.com/thumbs/no-image-icon/no-image-icon-6.png")
                                                   .into(imageView);*/
                                       }else {
                                           image = "http://" + response.body().getImage();

                                           Glide
                                                   .with(MenuDetailsActivity.this)
                                                   .load(image)
                                                   .into(imageView);
                                       }

                                       if (!details.equals("")) {
                                           binding.constraintLayout23.setVisibility(View.VISIBLE);




                                       } else {
                                           binding.constraintLayout23.setVisibility(View.GONE);

                                       }

                                       layout.stopShimmer();
                                       layout.hideShimmer();
                                       layout.setVisibility(View.GONE);

                                       binding.textView12.setText(1 + "");









                                       imageGalleryAdapter.notifyDataSetChanged();
                                       binding.recyclerViewGallery.setAdapter(imageGalleryAdapter);


                                       if (specificationList.size() > 0) {

                                           binding.linearLayoutSpecification.setVisibility(View.VISIBLE);
                                           getSpecificationData();

                                       }




                                   }

                                   @Override
                                   public void onError(@NonNull Throwable e) {

                                   }

                                   @Override
                                   public void onComplete() {

                                   }
                               }
                )
        );


    }

    private void getSpecificationData() {

        specificationAdapter = new SpecificationAdapter(getApplicationContext(), specificationList);
        // binding.recyclerViewCategory.setLayoutManager(new GridLayoutManager(getContext(),1));
        // binding.recyclerViewSpecification.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));
        binding.recyclerViewSpecification.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));
        specificationAdapter.notifyDataSetChanged();
        binding.recyclerViewSpecification.setAdapter(specificationAdapter);
    }

    @Override
    public void onClick(View view, int position) {

    }

    @Override
    public void onClickSize(View view, int position) {

    }

    @Override
    public void onClickImageGallery(View view, int position) {
        imageGalleryAdapter.SetPositionnn(position);
        imageGalleryAdapter.notifyDataSetChanged();
        image = "http://" + modelgalleryarray.get(position).getImagePath();
        Glide
                .with(MenuDetailsActivity.this)
                .load("http://" + modelgalleryarray.get(position).getImagePath())
                .into(binding.imageView11);
    }

    public void btn_back(View view) {
        onBackPressed();
    }
}