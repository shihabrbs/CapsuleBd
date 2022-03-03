package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;

import com.github.ybq.android.spinkit.SpinKitView;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.adapter.BrandNameAdapter;
import com.rightbrain.officeproject.adapter.CategoryNameAdapter;
import com.rightbrain.officeproject.adapter.DiscountNameAdapter;
import com.rightbrain.officeproject.adapter.DiscountProductAdapter;
import com.rightbrain.officeproject.adapter.DiscountProductAdapterList;
import com.rightbrain.officeproject.adapter.ProductsAdapter;
import com.rightbrain.officeproject.adapter.ProductsAdapterList;
import com.rightbrain.officeproject.adapter.PromotionNameAdapter;
import com.rightbrain.officeproject.adapter.PromotionProductAdapter;
import com.rightbrain.officeproject.adapter.PromotionProductAdapterList;
import com.rightbrain.officeproject.adapter.SearchAdapter;
import com.rightbrain.officeproject.adapter.ShopNameAdapter;
import com.rightbrain.officeproject.databinding.ActivityAllCategoryBinding;
import com.rightbrain.officeproject.databinding.ActivityProductsBinding;
import com.rightbrain.officeproject.model.Category;
import com.rightbrain.officeproject.model.ModelBrand;
import com.rightbrain.officeproject.model.ModelCartRoom;
import com.rightbrain.officeproject.model.ModelProducts;
import com.rightbrain.officeproject.model.ModelSetup;
import com.rightbrain.officeproject.model.sidebar.DiscountItem;
import com.rightbrain.officeproject.model.sidebar.PromotionItem;
import com.rightbrain.officeproject.model.sidebar.SidebarResponse;
import com.rightbrain.officeproject.retrofit.ApiClint;
import com.rightbrain.officeproject.retrofit.ApiInterface;
import com.rightbrain.officeproject.room.CartRepository;
import com.rightbrain.officeproject.room.ItemClickListener;
import com.rightbrain.officeproject.room.ItemClickListenerShop;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.rightbrain.officeproject.model.ModelProducts.GRID_VIEW_LAYOUT;
import static com.rightbrain.officeproject.model.ModelProducts.LARGE_VIEW_LAYOUT;
import static com.rightbrain.officeproject.model.ModelProducts.LIST_VIEW_LAYOUT;

public class ProductsActivity extends AppCompatActivity implements ItemClickListener, ItemClickListenerShop {
    ActivityProductsBinding binding;
    CompositeDisposable compositeDisposable;
    ApiInterface apiInterface;
    ArrayList<ModelProducts> productList;
    ArrayList<ModelProducts> discountList;
    ArrayList<Category> categorynamelist;
    ArrayList<ModelBrand> brandsnamelist;
    ArrayList<DiscountItem> discountnamelist;
    ArrayList<PromotionItem> promotionnamelist;
    CategoryNameAdapter categoryNameAdapter;
    BrandNameAdapter brandNameAdapter;
    ProductsAdapter productsAdapter;
    ProductsAdapterList productsAdapterList;
    DiscountProductAdapter discountProductAdapter;
    DiscountProductAdapterList discountProductAdapterList;
    DiscountNameAdapter discountNameAdapter;
    PromotionNameAdapter promotionNameAdapter;
    ArrayList<ModelProducts> featurePromotionProductList;
    PromotionProductAdapter promotionProductAdapter;
    PromotionProductAdapterList promotionProductAdapterList;
    ArrayList<ModelProducts> searchProductList = new ArrayList<>();
    SearchAdapter searchAdapter;
    SpinKitView spinKitView;
    MysharedPreferance sharedPreferance;
    AlertDialog alertDialog;
    ArrayList<ModelSetup> setupArrayList = new ArrayList<>();
    ShopNameAdapter shopNameAdapter;

    String ViewType;

    String module;
    String id;
    String catid;
    String uniqueCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        catid = getIntent().getStringExtra("catid");
        String catname = getIntent().getStringExtra("catname");
        module = getIntent().getStringExtra("module");
        id = getIntent().getStringExtra("id");
        compositeDisposable = new CompositeDisposable();
        productList = new ArrayList<>();
        discountList = new ArrayList<>();
        categorynamelist = new ArrayList<>();
        brandsnamelist = new ArrayList<>();
        discountnamelist = new ArrayList<>();
        promotionnamelist = new ArrayList<>();
        spinKitView = findViewById(R.id.spin_kit_login);
        featurePromotionProductList = new ArrayList<>();
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();

        ViewType = sharedPreferance.getViewType();


        //category name
        categoryNameAdapter = new CategoryNameAdapter(this, categorynamelist);
        binding.recyclerViewCatName.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL, false));


        //Brand name
        brandNameAdapter = new BrandNameAdapter(this, brandsnamelist);
        binding.recyclerViewCatName.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL, false));

        //Discount name
        discountNameAdapter = new DiscountNameAdapter(this, discountnamelist);
        binding.recyclerViewCatName.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL, false));


        //Promotion name
        promotionNameAdapter = new PromotionNameAdapter(this, promotionnamelist);
        binding.recyclerViewCatName.setLayoutManager(new LinearLayoutManager(getApplication(), LinearLayoutManager.HORIZONTAL, false));


        if (module.equals("brand")) {
            if (id.equals("")) {
                brandNameAdapter.SetPosition(-1);
            } else {
                brandNameAdapter.SetPosition(Integer.parseInt(id));
            }

            brandNameAdapter.SetItemClickListener(this);
        }
        if (module.equals("discount")) {

            discountNameAdapter.SetItemClickListener(this);
            if (id.equals("")) {
                discountNameAdapter.SetPosition(-1);
            } else {
                discountNameAdapter.SetPosition(Integer.parseInt(id));
            }


        }
        if (module.equals("Promotion")) {

            promotionNameAdapter.SetItemClickListener(this);
            if (id.equals("")) {
                promotionNameAdapter.SetPosition(-1);
            } else {
                promotionNameAdapter.SetPosition(Integer.parseInt(id));
            }


        } else {
            if (id.equals("")) {
                categoryNameAdapter.SetPosition(-1);
            } else {
                categoryNameAdapter.SetPosition(Integer.parseInt(id));
            }

            categoryNameAdapter.SetItemClickListener(this);
        }

        setCartnumber();
        View_TypeMethod();




        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String text = binding.etSearch.getText().toString();
                    Intent intent = new Intent(ProductsActivity.this, SearchActivity.class);
                    intent.putExtra("searchdata", "" + text);
                    startActivity(intent);
                    //  searchProduct(text);
                    return true;
                }
                return false;
            }
        });

        binding.imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = binding.etSearch.getText().toString();

                Intent intent = new Intent(ProductsActivity.this, SearchActivity.class);
                intent.putExtra("searchdata", "" + text);
                startActivity(intent);
                //  searchProduct(text);
            }
        });

        binding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.searchlayoutid.setVisibility(View.GONE);
                binding.etSearch.setText(null);

                try {
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }

            }
        });


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

    private void View_TypeMethod() {

        if (ViewType.equals("grid")) {

            if (module.equals("brand")) {
                productsAdapter = new ProductsAdapter(this, productList,GRID_VIEW_LAYOUT);
                binding.recyclerViewcategory.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

                getBrandName();
                getBrandProduct("brand", "" + catid);
            } else if (module.equals("Promotion")) {
                promotionProductAdapter = new PromotionProductAdapter(this, featurePromotionProductList,GRID_VIEW_LAYOUT);
                binding.recyclerViewcategory.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

                getPromotionName();
                getPromotionProduct("" + catid);
            } else if (module.equals("discount")) {
                discountProductAdapter = new DiscountProductAdapter(this, discountList,GRID_VIEW_LAYOUT);
                binding.recyclerViewcategory.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

                getDiscountName();
                getDiscountProduct("discount", "" + catid);
            } else {
                productsAdapter = new ProductsAdapter(this, productList,GRID_VIEW_LAYOUT);
                binding.recyclerViewcategory.setLayoutManager(new GridLayoutManager(getApplicationContext(), 2));

                getCategoryName();
                getProduct("" + catid);

            }

        }else  if (ViewType.equals("large")) {

            if (module.equals("brand")) {
                productsAdapter = new ProductsAdapter(this, productList,LARGE_VIEW_LAYOUT);
                binding.recyclerViewcategory.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

                getBrandName();
                getBrandProduct("brand", "" + catid);
            } else if (module.equals("Promotion")) {
                promotionProductAdapter = new PromotionProductAdapter(this, featurePromotionProductList,LARGE_VIEW_LAYOUT);
                binding.recyclerViewcategory.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

                getPromotionName();
                getPromotionProduct("" + catid);
            } else if (module.equals("discount")) {
                discountProductAdapter = new DiscountProductAdapter(this, discountList,LARGE_VIEW_LAYOUT);
                binding.recyclerViewcategory.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

                getDiscountName();
                getDiscountProduct("discount", "" + catid);
            } else {
                productsAdapter = new ProductsAdapter(this, productList,LARGE_VIEW_LAYOUT);
                binding.recyclerViewcategory.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

                getCategoryName();
                getProduct("" + catid);

            }

        }
        else {


            if (module.equals("brand")) {
                productsAdapter = new ProductsAdapter(this, productList,LIST_VIEW_LAYOUT);
                binding.recyclerViewcategory.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

                getBrandName();
                getBrandProduct("brand", "" + catid);
            } else if (module.equals("Promotion")) {
                promotionProductAdapter = new PromotionProductAdapter(this, featurePromotionProductList,LIST_VIEW_LAYOUT);
                binding.recyclerViewcategory.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

                getPromotionName();
                getPromotionProduct("" + catid);
            } else if (module.equals("discount")) {
                discountProductAdapter = new DiscountProductAdapter(this, discountList,LIST_VIEW_LAYOUT);
                binding.recyclerViewcategory.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

                getDiscountName();
                getDiscountProduct("discount", "" + catid);
            } else {
                productsAdapter = new ProductsAdapter(this, productList,LIST_VIEW_LAYOUT);
                binding.recyclerViewcategory.setLayoutManager(new GridLayoutManager(getApplicationContext(), 1));

                getCategoryName();
                getProduct("" + catid);

            }

        }
    }

    private void getProductviewList(String catid) {
        compositeDisposable.add(ApiClint.getInstance().getProduct("" + uniqueCode, catid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                    @Override
                    public void onNext(final Response<List<ModelProducts>> response) {

                        productList.clear();
                        productList.addAll(response.body());

                        productsAdapterList.notifyDataSetChanged();
                        binding.recyclerViewcategory.setAdapter(productsAdapterList);
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
                        binding.recyclerViewcategory.setAdapter(promotionProductAdapter);
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

    private void getPromotionProductViewTypeList(String catid) {
        compositeDisposable.add(ApiClint.getInstance().getPromotionProduct("" + uniqueCode, catid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                    @Override
                    public void onNext(final Response<List<ModelProducts>> response) {

                        featurePromotionProductList.clear();
                        featurePromotionProductList.addAll(response.body());
                        promotionProductAdapterList.notifyDataSetChanged();
                        binding.recyclerViewcategory.setAdapter(promotionProductAdapterList);
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


    private void searchProduct(String keyword) {
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


        compositeDisposable.add(ApiClint.getInstance().productSearch("" + uniqueCode, "" + keyword)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                    @Override
                    public void onNext(final Response<List<ModelProducts>> response) {

                        searchProductList.clear();
                        searchProductList.addAll(response.body());
                        searchAdapter.notifyDataSetChanged();
                        binding.recycerviewSearch.setAdapter(searchAdapter);

                        if (searchProductList.size() > 0) {
                            binding.searchlayoutid.setVisibility(View.VISIBLE);
                        } else {
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

    private void getDiscountProduct(String module, String id) {

        compositeDisposable.add(ApiClint.getInstance().getFeatureProduct("" + uniqueCode, module, id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                    @Override
                    public void onNext(final Response<List<ModelProducts>> response) {

                        discountList.clear();
                        discountList.addAll(response.body());
                        discountProductAdapter.notifyDataSetChanged();
                        binding.recyclerViewcategory.setAdapter(discountProductAdapter);
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

    private void getDiscountProductViewTypeList(String module, String id) {

        compositeDisposable.add(ApiClint.getInstance().getFeatureProduct("" + uniqueCode, module, id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                    @Override
                    public void onNext(final Response<List<ModelProducts>> response) {

                        discountList.clear();
                        discountList.addAll(response.body());
                        discountProductAdapterList.notifyDataSetChanged();
                        binding.recyclerViewcategory.setAdapter(discountProductAdapterList);
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


    private void getBrandProduct(String module, String id) {

        compositeDisposable.add(ApiClint.getInstance().getFeatureProduct("" + uniqueCode, module, id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                    @Override
                    public void onNext(final Response<List<ModelProducts>> response) {

                        productList.clear();
                        productList.addAll(response.body());
                        productsAdapter.notifyDataSetChanged();
                        binding.recyclerViewcategory.setAdapter(productsAdapter);
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

    private void getBrandProductViewTypeList(String module, String id) {

        compositeDisposable.add(ApiClint.getInstance().getFeatureProduct("" + uniqueCode, module, id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                    @Override
                    public void onNext(final Response<List<ModelProducts>> response) {

                        productList.clear();
                        productList.addAll(response.body());
                        productsAdapterList.notifyDataSetChanged();
                        binding.recyclerViewcategory.setAdapter(productsAdapterList);
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

    private void getPromotionProduct(String module, String id) {

        compositeDisposable.add(ApiClint.getInstance().getFeatureProduct("" + uniqueCode, module, id)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                    @Override
                    public void onNext(final Response<List<ModelProducts>> response) {

                        productList.clear();
                        productList.addAll(response.body());
                        productsAdapter.notifyDataSetChanged();
                        binding.recyclerViewcategory.setAdapter(productsAdapter);
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

    private void getProductitemclick(String catid) {

        compositeDisposable.add(ApiClint.getInstance().getProduct("" + uniqueCode, catid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                    @Override
                    public void onNext(final Response<List<ModelProducts>> response) {

                        productList.clear();
                        productList.addAll(response.body());
                        productsAdapter.notifyDataSetChanged();
                        binding.recyclerViewcategory.setAdapter(productsAdapter);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private void getProductitemclickTypeList(String catid) {

        compositeDisposable.add(ApiClint.getInstance().getProduct("" + uniqueCode, catid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                    @Override
                    public void onNext(final Response<List<ModelProducts>> response) {

                        productList.clear();
                        productList.addAll(response.body());
                        productsAdapterList.notifyDataSetChanged();
                        binding.recyclerViewcategory.setAdapter(productsAdapterList);

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private void getProduct(String catid) {

        compositeDisposable.add(ApiClint.getInstance().getProduct("" + uniqueCode, catid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelProducts>>>() {
                    @Override
                    public void onNext(final Response<List<ModelProducts>> response) {

                        productList.clear();
                        productList.addAll(response.body());

                        productsAdapter.notifyDataSetChanged();
                        binding.recyclerViewcategory.setAdapter(productsAdapter);
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


    private void getCategoryName() {
        compositeDisposable.add(ApiClint.getInstance().getCategory("" + uniqueCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<Category>>>() {
                    @Override
                    public void onNext(final Response<List<Category>> response) {

                        categorynamelist.addAll(response.body());
                        categoryNameAdapter.notifyDataSetChanged();
                        // binding.recyclerViewCatName.addItemDecoration(new DividerItemDecoration(ProductsActivity.this,DividerItemDecoration.HORIZONTAL));
                        binding.recyclerViewCatName.setAdapter(categoryNameAdapter);


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }


    private void getBrandName() {
        compositeDisposable.add(ApiClint.getInstance().getBrand("" + uniqueCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelBrand>>>() {
                    @Override
                    public void onNext(final Response<List<ModelBrand>> response) {

                        brandsnamelist.addAll(response.body());
                        brandNameAdapter.notifyDataSetChanged();
                        binding.recyclerViewCatName.setAdapter(brandNameAdapter);


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    private void getDiscountName() {
        compositeDisposable.add(ApiClint.getInstance().getSidebarItems("" + uniqueCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<Response<SidebarResponse>>() {
                    @Override
                    public void onSuccess(Response<SidebarResponse> sidebarResponseResponse) {

                        if (sidebarResponseResponse.isSuccessful()) {
                            if (sidebarResponseResponse.body() != null) {

                                discountnamelist.addAll(sidebarResponseResponse.body().getDiscount());

                                discountNameAdapter.notifyDataSetChanged();
                                binding.recyclerViewCatName.setAdapter(discountNameAdapter);


                                /*binding.recyclerViewCatName.setHasFixedSize(true);

                                binding.recyclerViewCatName.setLayoutManager(new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false));

                                // SidebarDiscountAdapter discountAdapter = new SidebarDiscountAdapter(DrawerMainActivity.this, sidebarResponseResponse.body().getDiscount());
                                 discountNameAdapter = new DiscountNameAdapter(getApplicationContext(), sidebarResponseResponse.body().getDiscount());

                                binding.recyclerViewCatName.setAdapter(discountNameAdapter);

                                discountNameAdapter.notifyDataSetChanged();*/
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    private void getPromotionName() {
        compositeDisposable.add(ApiClint.getInstance().getSidebarItems("" + uniqueCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<Response<SidebarResponse>>() {
                    @Override
                    public void onSuccess(Response<SidebarResponse> sidebarResponseResponse) {

                        if (sidebarResponseResponse.isSuccessful()) {
                            if (sidebarResponseResponse.body() != null) {

                                promotionnamelist.addAll(sidebarResponseResponse.body().getPromotion());

                                promotionNameAdapter.notifyDataSetChanged();
                                binding.recyclerViewCatName.setAdapter(promotionNameAdapter);


                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

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

    public void cartbtn(View view) {
        Intent intent = new Intent(ProductsActivity.this, CartActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);

    }

    public void btn_home(View view) {
        Intent intent = new Intent(ProductsActivity.this, DrawerMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void btn_Category(View view) {
        Intent intent = new Intent(ProductsActivity.this, AllCategoryActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

/*    @Override
    public void onItemClick(int position) {
       // Toast.makeText(this, ""+categorynamelist.get(position).getCategoryId(), Toast.LENGTH_SHORT).show();
        getProduct(""+categorynamelist.get(position).getCategoryId());
        binding.textView41.setText(""+categorynamelist.get(position).getName());
    }*/

    public void btn_offer(View view) {
        Intent intent = new Intent(ProductsActivity.this, OfferActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }


    public void btn_menu(View view) {
        showTermsDialog();
    }

    public void btn_account(View view) {
        MysharedPreferance mysharedPreferance = MysharedPreferance.getPreferences(ProductsActivity.this);
        String userid = "" + mysharedPreferance.getUserId();

        if (userid.equals("")) {
            Intent intent = new Intent(ProductsActivity.this, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        } else {
            Intent intent = new Intent(ProductsActivity.this, UserActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }

    }

    @Override
    public void onClick(View view, int position) {
        /*shopNameAdapter.SetPositionShop(position);
        shopNameAdapter.notifyDataSetChanged();
*/
        String module = getIntent().getStringExtra("module");
/*


        brandNameAdapter.SetPosition(position);
        brandNameAdapter.notifyDataSetChanged();
        getProductitemclick("" + brandsnamelist.get(position).getBrandId());

*/


        categoryNameAdapter.SetPosition(position);
        categoryNameAdapter.notifyDataSetChanged();

        if (ViewType.equals("grid")) {
            getProductitemclick("" + categorynamelist.get(position).getCategoryId());
        }
       else if (ViewType.equals("large")) {
            getProductitemclick("" + categorynamelist.get(position).getCategoryId());
        }
        else {
            getProductitemclick("" + categorynamelist.get(position).getCategoryId());
        }


    }

    @Override
    public void onClickbrand(View view, int position) {
        brandNameAdapter.SetPosition(position);
        brandNameAdapter.notifyDataSetChanged();
        if (ViewType.equals("grid")) {
            getBrandProduct("brand", "" + brandsnamelist.get(position).getBrandId());
        }
        else if (ViewType.equals("large")) {
            getBrandProduct("brand", "" + brandsnamelist.get(position).getBrandId());
        }
        else {

            getBrandProduct("brand", "" + brandsnamelist.get(position).getBrandId());
        }

    }

    @Override
    public void onClickdiscountt(View view, int position) {
        discountNameAdapter.SetPosition(position);
        discountNameAdapter.notifyDataSetChanged();

        if (ViewType.equals("grid")) {
            getDiscountProduct("discount", "" + discountnamelist.get(position).getDiscountId());
        } else if (ViewType.equals("grid")) {
            getDiscountProduct("discount", "" + discountnamelist.get(position).getDiscountId());
        }
            else {

            getDiscountProduct("discount", "" + discountnamelist.get(position).getDiscountId());
        }

    }

    @Override
    public void onClickpromotion(View view, int position) {
        promotionNameAdapter.SetPosition(position);
        promotionNameAdapter.notifyDataSetChanged();
        //       getPromotionProduct("Promotion","" + promotionnamelist.get(position).getPromotionId());

        if (ViewType.equals("grid")) {
            getPromotionProduct("" + promotionnamelist.get(position).getPromotionId());

        }else  if (ViewType.equals("grid")) {
            getPromotionProduct("" + promotionnamelist.get(position).getPromotionId());

        } else {

            getPromotionProduct("" + promotionnamelist.get(position).getPromotionId());
        }


    }

    /* Shop Type dialog */
    private void showTermsDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(ProductsActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_shop_type, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(ProductsActivity.this);

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