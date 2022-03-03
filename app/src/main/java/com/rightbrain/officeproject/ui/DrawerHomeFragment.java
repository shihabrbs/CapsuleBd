package com.rightbrain.officeproject.ui;

import android.content.Intent;
import android.os.Bundle;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.denzcoskun.imageslider.ImageSlider;
import com.denzcoskun.imageslider.interfaces.ItemClickListener;
import com.denzcoskun.imageslider.models.SlideModel;
import com.github.ybq.android.spinkit.SpinKitView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.adapter.DiscountAdapter;
import com.rightbrain.officeproject.adapter.FeatureBrandAdapter;
import com.rightbrain.officeproject.adapter.FeatureCategoryAdapter;
import com.rightbrain.officeproject.adapter.ProductsAdapter;
import com.rightbrain.officeproject.adapter.PromotionAdapter;
import com.rightbrain.officeproject.adapter.PromotionProductAdapter;
import com.rightbrain.officeproject.adapter.multiAdapter.DiscountMainAdapter;
import com.rightbrain.officeproject.adapter.multiAdapter.MainAdapter;
import com.rightbrain.officeproject.databinding.ActivityMainBinding;
import com.rightbrain.officeproject.databinding.FragmentDrawerHomeBinding;
import com.rightbrain.officeproject.databinding.FragmentHomeBinding;
import com.rightbrain.officeproject.model.Category;
import com.rightbrain.officeproject.model.ModelBrand;
import com.rightbrain.officeproject.model.ModelCartRoom;
import com.rightbrain.officeproject.model.ModelProducts;
import com.rightbrain.officeproject.model.multirecycermodel.ModelMain;
import com.rightbrain.officeproject.retrofit.ApiClint;
import com.rightbrain.officeproject.retrofit.ApiInterface;
import com.rightbrain.officeproject.room.CartRepository;
import com.rightbrain.officeproject.room.RecyclerViewItemClick;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.rightbrain.officeproject.model.ModelProducts.GRID_VIEW_LAYOUT;

public class DrawerHomeFragment extends Fragment implements RecyclerViewItemClick {
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    FragmentDrawerHomeBinding binding;
    ConstraintLayout constraintLayout, floatbg;
    ConstraintLayout myimage;
    ImageView cart_img, textView;
    String uniqueCode;
    SpinKitView spinKitView;
    //  TextView textView;
    int j;
    String color;
    ApiInterface apiInterface;
    RecyclerView recyclerViewcategory, recyclerViewPopular;
    ArrayList<Category> productList, popularProduct;
    ArrayList<Category> featureCategoryList;
    ArrayList<Category> featurePromotionList;
    ArrayList<ModelProducts> featurePromotionProductList;
    ArrayList<ModelBrand> featureBrandList;
    ArrayList<ModelMain> featureProductList;
    ArrayList<ModelMain> featureDiscountProductlist;
    FeatureCategoryAdapter featureCategoryAdapter;
    FeatureBrandAdapter featureBrandAdapter;
    ProductsAdapter productsAdapter;
    PromotionAdapter allfeaturespromotionAdapter;
    PromotionProductAdapter promotionProductAdapter;
    MainAdapter mainAdapter;
    DiscountMainAdapter discountMainAdapter;
    DiscountAdapter discountAdapter;
    ImageSlider imageSlider;
    BottomNavigationView bottomNavigationView;

    public DrawerHomeFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getActivity());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();

        featureCategoryList = new ArrayList<>();
        featureBrandList = new ArrayList<>();
        featurePromotionList = new ArrayList<>();
        featurePromotionProductList = new ArrayList<>();
        featureProductList = new ArrayList<>();
        featureDiscountProductlist = new ArrayList<>();
        productList = new ArrayList<>();
        popularProduct = new ArrayList<>();


        getFeature_category();
        getPromotion();

        getFeatureBrands();
        getFeatureDiscount();
        getFeatureProduct();


        getFeature_Slider();



        /*changebgcolor(2);*/

    }

    private void getPromotionProduct(String catid) {
        compositeDisposable.add(ApiClint.getInstance().getPromotionProduct("" + uniqueCode, catid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                    @Override
                    public void onNext(final Response<List<ModelProducts>> response) {

                        featurePromotionProductList.clear();
                        featurePromotionProductList.addAll(response.body());
                        promotionProductAdapter.notifyDataSetChanged();
                        binding.recyclerViewpromotionproduct.setAdapter(promotionProductAdapter);


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private void getFeatureBrands() {
        compositeDisposable.add(ApiClint.getInstance().getFeatureBrand("" + uniqueCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelBrand>>>() {
                    @Override
                    public void onNext(final Response<List<ModelBrand>> response) {
                        featureBrandList.addAll(response.body());
                        featureBrandAdapter.notifyDataSetChanged();
                        binding.featureBrands.setAdapter(featureBrandAdapter);

                        if (featureBrandList.size() > 0) {
                            binding.brands.setVisibility(View.VISIBLE);
                            binding.textView6.setVisibility(View.VISIBLE);
                            binding.imageView7.setVisibility(View.VISIBLE);
                        } else {
                            binding.brands.setVisibility(View.GONE);
                            binding.textView6.setVisibility(View.GONE);
                            binding.imageView7.setVisibility(View.GONE);
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

    private void getFeatureProduct() {

        compositeDisposable.add(ApiClint.getInstance().getMultiFeatureProduct("" + uniqueCode, "category")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelMain>>>() {
                    @Override
                    public void onNext(final Response<List<ModelMain>> response) {

                        featureProductList.addAll(response.body());
                        mainAdapter.notifyDataSetChanged();
                        binding.featureProduct.setAdapter(mainAdapter);

                        //  Toast.makeText(getContext(), ""+response.body().size(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }


    private void getFeatureDiscount() {

        compositeDisposable.add(ApiClint.getInstance().getMultiFeatureProduct("" + uniqueCode, "discount")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelMain>>>() {
                    @Override
                    public void onNext(final Response<List<ModelMain>> response) {

                        featureDiscountProductlist.addAll(response.body());
                        discountMainAdapter.notifyDataSetChanged();
                        binding.featureDiscountproduct.setAdapter(discountMainAdapter);

                        //  Toast.makeText(getContext(), ""+response.body().size(), Toast.LENGTH_SHORT).show();

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private void getFeature_category() {

        compositeDisposable.add(ApiClint.getInstance().getFeatureCategory("" + uniqueCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<Category>>>() {
                    @Override
                    public void onNext(final Response<List<Category>> response) {
                        productList.addAll(response.body());
                        featureCategoryAdapter.notifyDataSetChanged();
                        binding.recyclercategory.setAdapter(featureCategoryAdapter);

                        if (productList.size() > 0) {
                            binding.textView.setVisibility(View.VISIBLE);
                            binding.textView2.setVisibility(View.VISIBLE);
                            binding.imageView5.setVisibility(View.VISIBLE);
                        } else {
                            binding.textView.setVisibility(View.GONE);
                            binding.textView2.setVisibility(View.GONE);
                            binding.imageView5.setVisibility(View.GONE);
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

    private void getFeature_Slider() {

        compositeDisposable.add(ApiClint.getInstance().getFeatureSlider("" + uniqueCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<Category>>>() {
                    @Override
                    public void onNext(final Response<List<Category>> response) {

                        featureCategoryList.addAll(response.body());


                        sliderImage();
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


    private void getPromotion() {

        compositeDisposable.add(ApiClint.getInstance().getFeaturePromotion("" + uniqueCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<Category>>>() {
                    @Override
                    public void onNext(final Response<List<Category>> response) {

                        featurePromotionList.addAll(response.body());
                        allfeaturespromotionAdapter.notifyDataSetChanged();
                        binding.featurePromotion.setAdapter(allfeaturespromotionAdapter);


                        if (featurePromotionList.size() > 0) {
                            binding.textView5.setVisibility(View.VISIBLE);
                            binding.imageView6.setVisibility(View.VISIBLE);
                        } else {
                            binding.textView5.setVisibility(View.GONE);
                            binding.imageView6.setVisibility(View.GONE);
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


    private void sliderImage() {

        List<SlideModel> slideModels = new ArrayList<>();
        // slideModels.add(new SlideModel(imgurl1,"1 Image"));

        for (int i = 0; i < featureCategoryList.size(); i++) {
            slideModels.add(new SlideModel("http://" + featureCategoryList.get(i).getImagePath()));
        }
      /*  slideModels.add(new SlideModel("https://image.shutterstock.com/z/stock-vector-pepperoni-pizza-banner-ads-on-chalk-board-background-with-tomatoes-and-basil-leaves-d-illustration-1505158397.jpg","2 Image"));
        slideModels.add(new SlideModel("http://www.terminalbd.com/cache/images/d/5/a/5/7/d5a57afca4176c66fda563b1091ca552fa893085.jpeg","3 Image"));
        slideModels.add(new SlideModel("https://thumbs.dreamstime.com/z/order-pizza-online-banner-mobile-app-templates-concept-fast-free-delivery-service-vector-illustration-flat-cartoon-design-138516640.jpg","4 Image"));
*/

        imageSlider.setImageList(slideModels, true);

        imageSlider.setItemClickListener(new ItemClickListener() {
            @Override
            public void onItemSelected(int i) {
                Toast.makeText(getContext(), "slider position: " + i, Toast.LENGTH_SHORT).show();
            }
        });

    }


  /*  private void changebgcolor(int a) {
        j = a;
        if (j == 1) {
            color = "#FFC107";


            DrawableCompat.setTint(myimage.getBackground(), ContextCompat.getColor(getContext(), R.color.orenge));
            DrawableCompat.setTint(floatbg.getBackground(), ContextCompat.getColor(getContext(), R.color.orenge));



        } else if (j == 2) {
            color = "#ffffff";

            DrawableCompat.setTint(myimage.getBackground(), ContextCompat.getColor(getContext(), R.color.blue));
            DrawableCompat.setTint(floatbg.getBackground(), ContextCompat.getColor(getContext(), R.color.blue));


        }


        constraintLayout.setBackgroundColor(Color.parseColor(color));
       // getTheme().applyStyle(R.style.OverlayPrimaryColorRed, true);
    }*/

    /*@Override
    public Resources.Theme getTheme() {
        Resources.Theme theme = super.getTheme();
        int i = 2;
        if (i == 1) {
            theme.applyStyle(R.style.DefaultTheme, true);


        } else if (i == 2) {
            theme.applyStyle(R.style.OverlayPrimaryColorRed, true);


        }

        return theme;
    }*/



  /*  public void cartbtn(View view) {
        Intent intent = new Intent(getContext(), CartActivity.class);
        intent.putExtra("logic", j);
        startActivity(intent);
    }*/

    public void cat_see_all(View view) {
        Intent intent = new Intent(getContext(), AllCategoryActivity.class);
        startActivity(intent);
    }

    public void btn_brand(View view) {
        Intent intent = new Intent(getContext(), AllBrandActivity.class);
        startActivity(intent);
    }

    public void btn_products(View view) {
        Intent intent = new Intent(getContext(), AllProductActivity.class);
        startActivity(intent);
    }

    public void btn_more(View view) {
        Toast.makeText(getContext(), "More", Toast.LENGTH_SHORT).show();
    }

    public void btn_offer(View view) {
        Toast.makeText(getContext(), "Offers", Toast.LENGTH_SHORT).show();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        if (container != null) {

            container.removeAllViews();

        }

        binding = FragmentDrawerHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        myimage = view.findViewById(R.id.myimageview);
        cart_img = view.findViewById(R.id.cartimg);
        textView = view.findViewById(R.id.textView2);
        floatbg = view.findViewById(R.id.floatingbtn);
        recyclerViewcategory = view.findViewById(R.id.recyclercategory);
        spinKitView = view.findViewById(R.id.spin_kit_login);
        bottomNavigationView = view.findViewById(R.id.bottomnavigationviewmenu);


        setCartnumber();

        constraintLayout = view.findViewById(R.id.constraintLayout);

        imageSlider = view.findViewById(R.id.img_slider);


       /* Menu menu = bottomNavigationView.getMenu();
        menu.getItem(2).setEnabled(false);

        bottomNavigationView.setBackgroundColor(ContextCompat.getColor(getActivity(), android.R.color.transparent));*/

        featureCategoryAdapter = new FeatureCategoryAdapter(getContext(), productList);
        /*binding.recyclercategory.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));*/
        binding.recyclercategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));




       /* if (featureBrandList.size() >0){

            binding.brands.setVisibility(View.VISIBLE);
            binding.textView6.setVisibility(View.VISIBLE);
            binding.imageView7.setVisibility(View.VISIBLE);
        }else{
            binding.brands.setVisibility(View.GONE);
            binding.textView6.setVisibility(View.GONE);
            binding.imageView7.setVisibility(View.GONE);
        }*/


        allfeaturespromotionAdapter = new PromotionAdapter(getContext(), featurePromotionList);
        /* binding.featurePromotion.setLayoutManager(new GridLayoutManager(getApplicationContext(),3));*/
        binding.recyclercategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        allfeaturespromotionAdapter.SetItemClickListener(this);


//promotion product
        promotionProductAdapter = new PromotionProductAdapter(getActivity(), featurePromotionProductList,GRID_VIEW_LAYOUT);
// binding.recyclerViewpromotionproduct.setLayoutManager(new GridLayoutManager(getActivity(),3));

        binding.recyclerViewpromotionproduct.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));


        featureBrandAdapter = new FeatureBrandAdapter(getContext(), featureBrandList);
        binding.featureBrands.setLayoutManager(new GridLayoutManager(getActivity(), 4));
        /*   binding.recyclercategory.setLayoutManager(new LinearLayoutManager(getApplication(),LinearLayoutManager.HORIZONTAL,false));
         */

        //Feature Discount Product
        discountMainAdapter = new DiscountMainAdapter(getContext(), featureDiscountProductlist);
        binding.featureDiscountproduct.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));


        //Feature product
        mainAdapter = new MainAdapter(getContext(), featureProductList);
        binding.featureProduct.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));



        //   binding.featureProduct.setLayoutManager(new GridLayoutManager(getActivity(),2));
        /*   binding.recyclercategory.setLayoutManager(new LinearLayoutManager(getApplication(),LinearLayoutManager.HORIZONTAL,false));
         */

        binding.textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AllCategoryActivity.class);
                startActivity(intent);
            }
        });

      /*  binding.fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CartActivity.class);
                intent.putExtra("logic", j);
                startActivity(intent);
            }
        });*/

        binding.myimageviewfloat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), CartActivity.class);
                intent.putExtra("logic", j);
                startActivity(intent);
            }
        });

        binding.textView6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AllBrandActivity.class);
                startActivity(intent);
            }
        });

        binding.constraintLayout19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AllCategoryActivity.class);
                startActivity(intent);
            }
        });

        binding.imageView19.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), AllCategoryActivity.class);
                startActivity(intent);
            }
        });

        binding.imageView18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MysharedPreferance mysharedPreferance = MysharedPreferance.getPreferences(getContext());
                String userid = "" + mysharedPreferance.getUserId();

                if (userid.equals("")) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), UserActivity.class);
                    startActivity(intent);
                }
            }
        });

        binding.constraintLayout18.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MysharedPreferance mysharedPreferance = MysharedPreferance.getPreferences(getContext());
                String userid = "" + mysharedPreferance.getUserId();

                if (userid.equals("")) {
                    Intent intent = new Intent(getContext(), LoginActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getContext(), UserActivity.class);
                    startActivity(intent);
                }


            }
        });

        binding.imageView20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getContext(), OfferActivity.class);
                startActivity(intent);
            }
        });
        binding.constraintLayout20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getContext(), OfferActivity.class);
                startActivity(intent);
            }
        });

        binding.imageView17.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


        return view;
        // return inflater.inflate(R.layout.fragment_drawer_home, container, false);
    }

    private void setCartnumber() {
        CartRepository repository = new CartRepository(getActivity());

        repository.getAllData().observe(getActivity(), new Observer<List<ModelCartRoom>>() {
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

  /*  @Override
    public void onItemClick(int position) {

     //   Toast.makeText(getActivity(), ""+featurePromotionList.get(position).getPromotion_id(), Toast.LENGTH_SHORT).show();
      *//*  getPromotionProduct(""+featurePromotionList.get(position).getPromotion_id());*//*
    }*/

    @Override
    public void onClick(View view, int position) {
        allfeaturespromotionAdapter.SetPosition(position);
        allfeaturespromotionAdapter.notifyDataSetChanged();
        getPromotionProduct("" + featurePromotionList.get(position).getPromotion_id());


    }

    @Override
    public void onClickSize(View view, int position) {

    }

    @Override
    public void onClickImageGallery(View view, int position) {

    }
}