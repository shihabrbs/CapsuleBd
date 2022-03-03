package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.widget.NestedScrollView;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.facebook.shimmer.ShimmerFrameLayout;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.adapter.ColorAdapter;
import com.rightbrain.officeproject.adapter.ImageGalleryAdapter;
import com.rightbrain.officeproject.adapter.RelatedProductAdapter;
import com.rightbrain.officeproject.adapter.SizeAdapter;
import com.rightbrain.officeproject.adapter.SpecificationAdapter;
import com.rightbrain.officeproject.databinding.ActivityProductDetailsBinding;
import com.rightbrain.officeproject.model.ModelCartRoom;
import com.rightbrain.officeproject.model.ModelColor;
import com.rightbrain.officeproject.model.ModelProducts;
import com.rightbrain.officeproject.model.ModelSize;
import com.rightbrain.officeproject.model.modelProductDetails.Color;
import com.rightbrain.officeproject.model.modelProductDetails.Gallery;
import com.rightbrain.officeproject.model.modelProductDetails.Measurement;
import com.rightbrain.officeproject.model.modelProductDetails.ProductDetails;
import com.rightbrain.officeproject.model.modelProductDetails.RelatedProduct;
import com.rightbrain.officeproject.model.modelProductDetails.Specification;
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

public class ProductDetailsActivity extends AppCompatActivity implements RecyclerViewItemClick {
    ActivityProductDetailsBinding binding;
    CompositeDisposable compositeDisposable;
    ArrayList<ModelProducts> productList;
    ImageView imageView;
    TextView textView, price, details, quantity, detext;
    String detailtext = "";
    ConstraintLayout constraintLayout;
    Spinner spinner;
    ArrayList<String> arrayList = new ArrayList<>();
    String image;
    ArrayList<ModelColor> colorArrayList;
    ColorAdapter colorAdapter;

    CartRepository repository;

    ArrayList<ModelSize> sizeArrayList;
    SizeAdapter sizeAdapter;
    ImageGalleryAdapter imageGalleryAdapter;
    List<Color> modelcolorarray;
    List<Measurement> modelmeasurementarray;
    List<Gallery> modelgalleryarray;
    List<ProductDetails> modelproductdetailsyarray;
    List<Specification> specificationList;
    List<RelatedProduct> relatedProductList;

    SpecificationAdapter specificationAdapter;
    RelatedProductAdapter relatedProductAdapter;

    String color = "";
    String size = "";
    String unitname;
    String brandid;
    String categoryid;

    int quan = 1;
    String uniqueCode;
    String product_price;
    String id;
    String realprice;

    ShimmerFrameLayout layout;
    Handler handler = new Handler();
    NestedScrollView nestedScrollView;
    ConstraintLayout bottommenu;
    String currency;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductDetailsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        layout = findViewById(R.id.shimmerlayout);
        nestedScrollView = findViewById(R.id.nestedView);
        bottommenu = findViewById(R.id.myimageview);

  /*      handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                layout.stopShimmer();
                layout.hideShimmer();
                layout.setVisibility(View.GONE);

                nestedScrollView.setVisibility(View.VISIBLE);
            }
        },8000);*/

        setCartnumber();
        repository = new CartRepository(getApplicationContext());

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();
        currency = mysharedPreferanceSetup.getCurrency();


        colorArrayList = new ArrayList<>();
        modelcolorarray = new ArrayList<>();
        modelmeasurementarray = new ArrayList<>();
        modelgalleryarray = new ArrayList<>();
        sizeArrayList = new ArrayList<>();
        modelproductdetailsyarray = new ArrayList<>();
        specificationList = new ArrayList<>();
        relatedProductList = new ArrayList<>();

        imageView = findViewById(R.id.imageView11);
        textView = findViewById(R.id.textView10);
        price = findViewById(R.id.textView11);
        details = findViewById(R.id.textView15);
        quantity = findViewById(R.id.textView12);
        detext = findViewById(R.id.textView14);
        constraintLayout = findViewById(R.id.detailsid);
        compositeDisposable = new CompositeDisposable();
        id = getIntent().getStringExtra("productid");
        String categoryid = getIntent().getStringExtra("catid");
        ArrayList<String> arrayList = new ArrayList<>();

    //   Toast.makeText(this, ""+id, Toast.LENGTH_SHORT).show();
         getProductDetails();
       // getProductDetailsWithColor();

        String s1 = "23,45,42,12,1121";

        String[] array = s1.split(",");


        Collections.addAll(arrayList, array);

    /*    spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arrayList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);*/

        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ProductDetailsActivity.this, FullImageActivity.class);
                intent.putExtra("url", "" + image);
                startActivity(intent);
            }
        });




        /*colorArrayList.add(new ModelColor("black", "#2e2e2e"));
        colorArrayList.add(new ModelColor("blue", "#03A9F4"));
        colorArrayList.add(new ModelColor("orenge", "#FFC107"));
        colorArrayList.add(new ModelColor("blue", "#03A9F4"));
        colorArrayList.add(new ModelColor("blue", "#03A9F4"));*/


        sizeArrayList.add(new ModelSize("XXL"));
        sizeArrayList.add(new ModelSize("XL"));
        sizeArrayList.add(new ModelSize("L"));
        sizeArrayList.add(new ModelSize("M"));
        sizeArrayList.add(new ModelSize("S"));

        colorAdapter = new ColorAdapter(this, modelcolorarray);
        binding.recyclerViewColor.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        colorAdapter.SetItemClickListener(this);


        sizeAdapter = new SizeAdapter(this, modelmeasurementarray);
        binding.recyclerViewSize.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        sizeAdapter.SetItemClickListenerr(this);


        imageGalleryAdapter = new ImageGalleryAdapter(this, modelgalleryarray);
        binding.recyclerViewGallery.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));
        imageGalleryAdapter.SetItemClickListenerrr(this);


        relatedProductAdapter = new RelatedProductAdapter(this, relatedProductList);
        binding.recyclerViewRelatedProduct.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.HORIZONTAL, false));


        if (categoryid.equals("0")) {

        } else {
            getRelatedProductData("" + categoryid);
        }


    }

    private void getRelatedProductData(String catname) {

        compositeDisposable.add(ApiClint.getInstance().getRelatedProduct("" + uniqueCode, "" + catname)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<RelatedProduct>>>() {
                    @Override
                    public void onNext(final Response<List<RelatedProduct>> response) {
                        

                        relatedProductList.addAll(response.body());
                        relatedProductAdapter.notifyDataSetChanged();
                        binding.recyclerViewRelatedProduct.setAdapter(relatedProductAdapter);

                        if (relatedProductList.size() > 0) {
                            binding.linearLayoutRelatedProduct.setVisibility(View.VISIBLE);

                            layout.stopShimmer();
                            layout.hideShimmer();
                            layout.setVisibility(View.GONE);

                            nestedScrollView.setVisibility(View.VISIBLE);
                            /*bottommenu.setVisibility(View.VISIBLE);*/
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

    private void getProductDetailsWithColor() {
        // "25292"

        compositeDisposable.add(ApiClint.getInstance().getProductDetails("UBE49VBB", "24643")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ProductDetails>>() {
                                   @Override
                                   public void onNext(final Response<ProductDetails> response) {
                                       // detailtext = "" + response.body().getContent();


                                       try {
                                           modelcolorarray.addAll(response.body().getColor());
                                       } catch (Exception e) {

                                       }
                                       try {
                                           modelmeasurementarray.addAll(response.body().getMeasurement());
                                       } catch (Exception e) {

                                       }
                                       try {
                                           modelgalleryarray.addAll(response.body().getGallery());
                                       } catch (Exception e) {

                                       }

                                       try {
                                           specificationList.addAll(response.body().getSpecification());
                                       } catch (Exception e) {

                                       }


                                       String unitname = "" + response.body().getUnitName();
                                       String brandname = "" + response.body().getBrand();
                                     //  brandid = "" + response.body().getBrandId();
                                       String categoryname = "" + response.body().getCategory();
                                      // categoryid = "" + response.body().getCategoryId();
                                       Toast.makeText(ProductDetailsActivity.this, ""+response.body().getName(), Toast.LENGTH_SHORT).show();

                                   /*      if (unitname.equals("null")) {
                                           binding.textView19.setVisibility(View.GONE);
                                       } else {
                                           binding.textView19.setVisibility(View.VISIBLE);
                                       }


                                       if (brandname.equals("null")) {
                                           binding.tvBrand.setVisibility(View.GONE);
                                           binding.tvBrandText.setVisibility(View.GONE);
                                       } else {
                                           binding.tvBrand.setVisibility(View.VISIBLE);
                                           binding.tvBrandText.setVisibility(View.VISIBLE);
                                       }

                                       if (categoryname.equals("null")) {
                                           binding.tvCategory.setVisibility(View.GONE);
                                           binding.tvCategoryText.setVisibility(View.GONE);
                                       } else {
                                           binding.tvCategory.setVisibility(View.VISIBLE);
                                           binding.tvCategoryText.setVisibility(View.VISIBLE);
                                       }*/

                                       binding.tvBrandText.setText("" + brandname);
                                       binding.tvCategoryText.setText("" + categoryname);

                                       binding.textView33.setVisibility(View.VISIBLE);
                                       // textView.setText("" + response.body().getName());
                                       binding.textView4.setText("" + response.body().getName());
                                       binding.textView19.setText("/" + response.body().getUnitName());
                                       price.setText(currency + " " + response.body().getPrice());
                                       realprice = "" + response.body().getPrice();
                                       product_price = "" + response.body().getPrice();

                                       unitname = response.body().getUnitName();
                                       binding.textView15.setText("" + response.body().getContent());
                                       binding.textView61.setText(currency + "" + realprice + " | 1");

                                       String details = binding.textView15.getText().toString();


                                       if (response.body().getImagePath().equals("")) {
                                           binding.nestedView.setVisibility(View.VISIBLE);
                                           Glide
                                                   .with(ProductDetailsActivity.this)
                                                   .load("https://www.freeiconspng.com/thumbs/no-image-icon/no-image-icon-6.png")
                                                   .into(imageView);
                                       } else {
                                           image = "http://" + response.body().getImagePath();

                                           Glide
                                                   .with(ProductDetailsActivity.this)
                                                   .load(image)
                                                   .into(imageView);
                                       }

                                     /*  if (!details.equals("")) {
                                           binding.constraintLayout23.setVisibility(View.VISIBLE);


                                       } else {
                                           binding.constraintLayout23.setVisibility(View.GONE);

                                       }*/

                                    /*   layout.stopShimmer();
                                       layout.hideShimmer();
                                       layout.setVisibility(View.GONE);
                                       binding.textView33.setVisibility(View.GONE);
                                       binding.tvBrand.setVisibility(View.GONE);
                                       binding.tvCategory.setVisibility(View.GONE);*/

                                       binding.textView12.setText(1 + "");


                                       sizeAdapter.notifyDataSetChanged();
                                       binding.recyclerViewSize.setAdapter(sizeAdapter);

                                       imageGalleryAdapter.notifyDataSetChanged();
                                       binding.recyclerViewGallery.setAdapter(imageGalleryAdapter);

                                       colorAdapter.notifyDataSetChanged();
                                       binding.recyclerViewColor.setAdapter(colorAdapter);

                                       if (modelmeasurementarray.size() > 0) {
                                           // binding.textView66.setVisibility(View.VISIBLE);
                                           binding.constraintLayout9.setVisibility(View.VISIBLE);
                                       }

                                       if (modelcolorarray.size() > 0) {
                                           binding.constraintLayout9.setVisibility(View.VISIBLE);
                                           binding.textView74.setVisibility(View.VISIBLE);
                                           binding.recyclerViewColor.setVisibility(View.VISIBLE);
                                           //   binding.textView67.setVisibility(View.VISIBLE);
                                       }

                                       if (specificationList.size() > 0) {

                                           binding.linearLayoutSpecification.setVisibility(View.VISIBLE);
                                           binding.textView73.setVisibility(View.VISIBLE);
                                           binding.recyclerViewSize.setVisibility(View.VISIBLE);
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
        binding.recyclerViewSpecification.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        specificationAdapter.notifyDataSetChanged();
        binding.recyclerViewSpecification.setAdapter(specificationAdapter);
    }

    private void getProductDetails() {


        compositeDisposable.add(ApiClint.getInstance().getProductDetails("UBE49VBB", ""+id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ProductDetails>>() {
                                   @Override
                                   public void onNext(final Response<ProductDetails> response) {

                                       Toast.makeText(ProductDetailsActivity.this, ""+response.body().getName(), Toast.LENGTH_SHORT).show();


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


    public void btn_cart(View view) {

        // Toast.makeText(this, "Add to cart", Toast.LENGTH_SHORT).show();


    }

    public void btn_back(View view) {
        onBackPressed();
    }


    @Override
    public void onClick(View view, int position) {
        colorAdapter.SetPosition(position);
        colorAdapter.notifyDataSetChanged();
        color = "" + modelcolorarray.get(position).getName();
        //  Toast.makeText(this, "" + modelcolorarray.get(position).getName(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickSize(View view, int position) {
        sizeAdapter.SetPositionn(position);
        sizeAdapter.notifyDataSetChanged();
        size = "" + modelmeasurementarray.get(position).getName();
    }

    @Override
    public void onClickImageGallery(View view, int position) {
        imageGalleryAdapter.SetPositionnn(position);
        imageGalleryAdapter.notifyDataSetChanged();
        image = "http://" + modelgalleryarray.get(position).getImagePath();
        Glide
                .with(ProductDetailsActivity.this)
                .load("http://" + modelgalleryarray.get(position).getImagePath())
                .into(binding.imageView11);
    }

    public void btnplus(View view) {
        quan = quan + 1;

        int price = Integer.parseInt(product_price);

        int totalprice = price * quan;
        binding.textView61.setText("৳" + totalprice + " | " + quan);
        binding.textView12.setText(quan + "");
    }

    public void btnminus(View view) {
        int price = Integer.parseInt(product_price);
        if (quan > 1) {
            quan = quan - 1;
            int totalprice = price * quan;
            binding.textView61.setText("৳" + totalprice + " | " + quan);
            binding.textView12.setText(quan + "");

        } else {


        }

    }

    public void cartbtn(View view) {

        Intent intent = new Intent(ProductDetailsActivity.this, CartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void btn_AddtoCart(View view) {


        if (modelmeasurementarray.size() > 0 && modelcolorarray.size() > 0) {

            if (size == "" && color == "") {
                Toast.makeText(this, "Please Select Color and Size", Toast.LENGTH_SHORT).show();
            } else {
                addtocartWithSizeColor();
            }

        } else if (modelmeasurementarray.size() > 0) {
            if (size == "") {
                Toast.makeText(this, "Please Select Size", Toast.LENGTH_SHORT).show();
            } else {
                addtocartWithSize();
            }

        } else if (modelcolorarray.size() > 0) {
            if (color == "") {
                Toast.makeText(this, "Please Select Color", Toast.LENGTH_SHORT).show();
            } else {
                addtocartWithColor();
            }

        } else {
            addtocartWithOutSizeColor();
        }

    }

    private void addtocartWithOutSizeColor() {

        repository.insertSingleData(new ModelCartRoom(
                "" + binding.textView4.getText().toString(),
                "" + realprice,
                "" + binding.textView12.getText().toString(),
                "" + image,
                "",
                "",
                "" + id));


    }

    private void addtocartWithSizeColor() {

        repository.insertSingleData(new ModelCartRoom(
                "" + binding.textView4.getText().toString(),
                "" + realprice,
                "" + binding.textView12.getText().toString(),
                "" + image,
                "" + size,
                "" + color,
                "" + id));


    }

    private void addtocartWithSize() {

        repository.insertSingleData(new ModelCartRoom(
                "" + binding.textView4.getText().toString(),
                "" + realprice,
                "" + binding.textView12.getText().toString(),
                "" + image,
                "" + size,
                "",
                "" + id));


    }

    private void addtocartWithColor() {

        repository.insertSingleData(new ModelCartRoom(
                "" + binding.textView4.getText().toString(),
                "" + realprice,
                "" + binding.textView12.getText().toString(),
                "" + image,
                "",
                "" + color,
                "" + id));


    }

    private void setCartnumber() {
        CartRepository repository = new CartRepository(this);

        repository.getAllData().observe(this, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> modelCartRooms) {

                if (modelCartRooms.size() == 0) {
                    binding.textView42.setVisibility(View.GONE);
                } else {
                    binding.textView42.setVisibility(View.VISIBLE);
                    if (modelCartRooms.size() > 99) {
                        binding.textView42.setText("99+");
                    } else {
                        binding.textView42.setText("" + modelCartRooms.size());
                    }


                }
            }
        });
    }

    public void btn_brand(View view) {
        /*Intent intent = new Intent(getApplicationContext(), ProductsActivity.class);
        intent.putExtra("module", "brand");
        intent.putExtra("catid", "1052" );
        intent.putExtra("id","");
        startActivity(intent);*/

    }

    public void btn_category(View view) {
       /* Intent intent = new Intent(getApplicationContext(), ProductsActivity.class);
        intent.putExtra("catid","");
        intent.putExtra("module","");
        intent.putExtra("catname","");
        intent.putExtra("id","");
        startActivity(intent);*/
    }
}