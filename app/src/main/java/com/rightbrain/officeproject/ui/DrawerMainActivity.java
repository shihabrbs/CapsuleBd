package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.adapter.SearchAdapter;
import com.rightbrain.officeproject.adapter.ShopNameAdapter;
import com.rightbrain.officeproject.adapter.SlideBarBrandAdapter;
import com.rightbrain.officeproject.adapter.SlideBarPromotionAdapter;
import com.rightbrain.officeproject.adapter.SlideBarrCategoryAdapter;
import com.rightbrain.officeproject.adapter.SlideBarrDiscountAdapter;
import com.rightbrain.officeproject.adapter.menuAdapter.MenuNameAdapter;
import com.rightbrain.officeproject.databinding.ActivityDrawerMainBinding;
import com.rightbrain.officeproject.model.ModelProducts;
import com.rightbrain.officeproject.model.ModelSetup;
import com.rightbrain.officeproject.model.sidebar.ModelMenu;
import com.rightbrain.officeproject.model.sidebar.SidebarResponse;
import com.rightbrain.officeproject.retrofit.ApiClint;
import com.rightbrain.officeproject.room.ItemClickListenerShop;
import com.rightbrain.officeproject.utils.Common;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

import static com.rightbrain.officeproject.model.ModelProducts.GRID_VIEW_LAYOUT;
import static com.rightbrain.officeproject.model.ModelProducts.LARGE_VIEW_LAYOUT;
import static com.rightbrain.officeproject.model.ModelProducts.LIST_VIEW_LAYOUT;

public class DrawerMainActivity extends AppCompatActivity implements ItemClickListenerShop {
    static DrawerLayout drawer;




    @BindView(R.id.navHome)
    LinearLayout navHome;


    @BindView(R.id.navDiscount)
    LinearLayout navDiscount;
    @BindView(R.id.nav_discount_rv)
    RecyclerView navDiscountRv;
    @BindView(R.id.navCategory)
    LinearLayout navCategory;
    @BindView(R.id.nav_category_rv)
    RecyclerView navCategoryRv;
    @BindView(R.id.navBrand)
    LinearLayout navBrand;
    @BindView(R.id.nav_brand_rv)
    RecyclerView navBrandRv;
    @BindView(R.id.navPromotion)
    LinearLayout navPromotion;
    @BindView(R.id.nav_promotion_rv)
    RecyclerView navPromotionRv;
    AlertDialog alertDialog;
    CompositeDisposable compositeDisposable;
    boolean isDiscountExpanded = false;
    boolean isCategoryExpanded = false;
    boolean isBrandExpanded = false;
    boolean isPromotionExpanded = false;

    @BindView(R.id.discount_arrow)
    ImageView discountArrow;
    @BindView(R.id.category_arrow)
    ImageView categoryArrow;
    @BindView(R.id.brand_arrow)
    ImageView brandArrow;
    @BindView(R.id.promotion_arrow)
    ImageView promotionArrow;
    String uniqueCode;
    ActivityDrawerMainBinding binding;
    ArrayList<ModelProducts> searchProductList;
    SearchAdapter searchAdapter;
MysharedPreferance sharedPreferance;
MysharedPreferanceSetup mysharedPreferanceSetup;

    ArrayList<ModelSetup> setupArrayList = new ArrayList<>();
    ShopNameAdapter shopNameAdapter;

    ArrayList<ModelMenu> modelMenuArrayList;
    MenuNameAdapter menuNameAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDrawerMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        sharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        uniqueCode = mysharedPreferanceSetup.getUniqueCode();

        compositeDisposable = new CompositeDisposable();

        binding.shopnamemenu.setText("  "+mysharedPreferanceSetup.getShopName());

        searchProductList = new ArrayList<>();
        modelMenuArrayList = new ArrayList<>();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
//        navigationView = findViewById(R.id.nav_view);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
//        navigationView.setNavigationItemSelectedListener(this);

//        View view = navigationView.getHeaderView(0);

        DrawerHomeFragment homeFragment = new DrawerHomeFragment();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.main_vendor_frame, homeFragment)
                .commitAllowingStateLoss();
        getMenuItems();

        binding.navDiscount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isDiscountExpanded) {

                    isDiscountExpanded = true;
                    isCategoryExpanded = false;
                    isBrandExpanded = false;
                    isPromotionExpanded = false;
                    binding.navCategoryRv.setVisibility(View.GONE);
                    binding.navBrandRv.setVisibility(View.GONE);
                    binding.navPromotionRv.setVisibility(View.GONE);
                    binding.navDiscountRv.setVisibility(View.VISIBLE);
                    Common.toggleArrow(binding.discountArrow);


                }else
                {
                    isDiscountExpanded = false;
                    Common.toggleArrow(binding.discountArrow);
                    binding.navDiscountRv.setVisibility(View.GONE);
                }
            }
        });

        binding.navSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DrawerMainActivity.this,SetupActivity.class);
                startActivity(intent);
            }
        });

        binding.navCategory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isCategoryExpanded) {

                    isDiscountExpanded = false;
                    isCategoryExpanded = true;
                    isBrandExpanded = false;
                    isPromotionExpanded = false;
                    binding.navCategoryRv.setVisibility(View.VISIBLE);
                    binding.navBrandRv.setVisibility(View.GONE);
                    binding.navPromotionRv.setVisibility(View.GONE);
                    binding.navDiscountRv.setVisibility(View.GONE);
                    Common.toggleArrow(binding.categoryArrow);

                }else
                {
                    isCategoryExpanded = false;
                    Common.toggleArrow(binding.categoryArrow);
                    binding.navCategoryRv.setVisibility(View.GONE);
                }
            }
        });

        binding.navBrand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isBrandExpanded) {

                    isDiscountExpanded = false;
                    isCategoryExpanded = false;
                    isBrandExpanded = true;
                    isPromotionExpanded = false;
                    binding.navCategoryRv.setVisibility(View.GONE);
                    binding.navBrandRv.setVisibility(View.VISIBLE);
                    binding.navPromotionRv.setVisibility(View.GONE);
                    binding.navDiscountRv.setVisibility(View.GONE);
                    Common.toggleArrow(binding.brandArrow);

                }else
                {
                    isBrandExpanded = false;
                    Common.toggleArrow(binding.brandArrow);
                    binding.navBrandRv.setVisibility(View.GONE);
                }
            }
        });

        binding.navPromotion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isPromotionExpanded) {

                    isDiscountExpanded = false;
                    isCategoryExpanded = false;
                    isBrandExpanded = false;
                    isPromotionExpanded = true;
                    binding.navCategoryRv.setVisibility(View.GONE);
                    binding.navBrandRv.setVisibility(View.GONE);
                    binding.navPromotionRv.setVisibility(View.VISIBLE);
                    binding.navDiscountRv.setVisibility(View.GONE);
                    Common.toggleArrow(binding.promotionArrow);

                }else
                {
                    isPromotionExpanded= false;
                    Common.toggleArrow(binding.promotionArrow);
                    binding.navPromotionRv.setVisibility(View.GONE);
                }
            }
        });

        //nav items
        getSidebarItems();


        binding.etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    String text = binding.etSearch.getText().toString();

                 //   searchProduct(text);
                    Intent intent = new Intent(DrawerMainActivity.this,SearchActivity.class);
                    intent.putExtra("searchdata",""+text);
                    startActivity(intent);

                    return true;
                }
                return false;
            }
        });

        binding.imageView9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = binding.etSearch.getText().toString();

             //   searchProduct(text);

                Intent intent = new Intent(DrawerMainActivity.this,SearchActivity.class);
                intent.putExtra("searchdata",""+text);
                startActivity(intent);
            }
        });

        binding.backbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.searchlayoutid.setVisibility(View.GONE);
                binding.etSearch.setText(null);

                try {
                    InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
                } catch (Exception e) {
                    // TODO: handle exception
                }

            }
        });


        Switch swth= findViewById(R.id.switchid) ;

        String userid =  ""+sharedPreferance.getUserId();

        if (userid.equals("")){
            swth.setVisibility(View.GONE);
          /*  swth.setClickable(false);
           swth.setChecked(false);


           swth.setOnTouchListener(new View.OnTouchListener() {
               @Override
               public boolean onTouch(View view, MotionEvent motionEvent) {
                   Intent intent = new Intent(DrawerMainActivity.this,LoginActivity.class);
                   startActivity(intent);
                   return false;
               }
           });*/

        }else {

            swth.setVisibility(View.VISIBLE);
            swth.setChecked(true);
            swth.setClickable(true);
        }


//////attach a listener to check for changes in state
        swth.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) {



                if(isChecked){
                  /*  Intent intent = new Intent(DrawerMainActivity.this,UserActivity.class);
                    startActivity(intent);*/

                }else{
                    MysharedPreferance mysharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());
                    mysharedPreferance.setUserId("");
                    swth.setVisibility(View.GONE);
                    /*Intent intent = new Intent(DrawerMainActivity.this,LoginActivity.class);
                    startActivity(intent);*/
                }

            }
        });





    }

    private void getMenuItems() {
        menuNameAdapter = new MenuNameAdapter(this,modelMenuArrayList);
        binding.recyclerViewMenu.setLayoutManager(new LinearLayoutManager(getApplicationContext(),LinearLayoutManager.VERTICAL,false));


        compositeDisposable.add(ApiClint.getInstance().getPageMenu(""+uniqueCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelMenu>>>() {
                                   @Override
                                   public void onNext(final Response<List<ModelMenu>> response) {

                                       modelMenuArrayList.addAll(response.body());
                                       menuNameAdapter.notifyDataSetChanged();
                                       binding.recyclerViewMenu.setAdapter(menuNameAdapter);


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


    private void setupshop() {

        ModelSetup modelSetup = new ModelSetup();
        modelSetup.setUniqueCode("UBE49VBB");
        modelSetup.setMobile("01828148149");

        compositeDisposable.add(ApiClint.getInstance().setupShop(modelSetup)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<ModelSetup>>() {
                    @Override
                    public void onNext(final Response<ModelSetup> response) {

                        mysharedPreferanceSetup.setUniqueCode(""+response.body().getUniqueCode());
                        //    Toast.makeText(SetupActivity.this, ""+response.body().getWebsite(), Toast.LENGTH_SHORT).show();

                       /* if (response.body().getMsg().equals("valid")) {
                            Toast.makeText(MyProfileActivity.this, "Update Successful", Toast.LENGTH_SHORT).show();
                        }else{
                            Toast.makeText(MyProfileActivity.this, "Update Failed", Toast.LENGTH_SHORT).show();
                        }*/

                        Intent intent = new Intent(getApplicationContext(),DrawerMainActivity.class);
                        startActivity(intent);

                       /* Toast.makeText(getApplicationContext(), ""+mysharedPreferanceSetup.getUniqueCode(), Toast.LENGTH_SHORT).show();
*/
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

    public static void closeDrawer() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();

        }
    }



    public void getSidebarItems() {
        compositeDisposable.add(ApiClint.getInstance().getSidebarItems(""+uniqueCode)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableSingleObserver<Response<SidebarResponse>>() {
                    @Override
                    public void onSuccess(Response<SidebarResponse> sidebarResponseResponse) {

                        if (sidebarResponseResponse.isSuccessful()) {
                            if (sidebarResponseResponse.body() != null) {
                                binding.navDiscountRv.setHasFixedSize(true);
                                binding.navCategoryRv.setHasFixedSize(true);
                                binding.navBrandRv.setHasFixedSize(true);
                                binding.navPromotionRv.setHasFixedSize(true);

                                binding.navDiscountRv.setLayoutManager(new LinearLayoutManager(DrawerMainActivity.this, RecyclerView.VERTICAL, false));
                                binding.navCategoryRv.setLayoutManager(new LinearLayoutManager(DrawerMainActivity.this, RecyclerView.VERTICAL, false));
                                binding.navBrandRv.setLayoutManager(new LinearLayoutManager(DrawerMainActivity.this, RecyclerView.VERTICAL, false));
                                binding.navPromotionRv.setLayoutManager(new LinearLayoutManager(DrawerMainActivity.this, RecyclerView.VERTICAL, false));

                               // SidebarDiscountAdapter discountAdapter = new SidebarDiscountAdapter(DrawerMainActivity.this, sidebarResponseResponse.body().getDiscount());
                                SlideBarrCategoryAdapter categoryAdapter = new SlideBarrCategoryAdapter(DrawerMainActivity.this, sidebarResponseResponse.body().getCategory());
                                SlideBarrDiscountAdapter discountAdapter = new SlideBarrDiscountAdapter(DrawerMainActivity.this, sidebarResponseResponse.body().getDiscount());
                                SlideBarBrandAdapter BrandAdapter = new SlideBarBrandAdapter(DrawerMainActivity.this, sidebarResponseResponse.body().getBrand());
                                SlideBarPromotionAdapter promotionAdapter = new SlideBarPromotionAdapter(DrawerMainActivity.this, sidebarResponseResponse.body().getPromotion());
                            //    SidebarBrandAdapter brandAdapter = new SidebarBrandAdapter(DrawerMainActivity.this, sidebarResponseResponse.body().getBrand());
                             //   SidebarPromotionAdapter promotionAdapter = new SideobarPromotionAdapter(DrawerMainActivity.this, sidebarResponseResponse.body().getPromotion());

                                binding.navDiscountRv.setAdapter(discountAdapter);
                                binding.navCategoryRv.setAdapter(categoryAdapter);
                                binding.navBrandRv.setAdapter(BrandAdapter);
                                binding.navPromotionRv.setAdapter(promotionAdapter);

                             //   discountAdapter.notifyDataSetChanged();
                                categoryAdapter.notifyDataSetChanged();
                             //   brandAdapter.notifyDataSetChanged();
                             //   promotionAdapter.notifyDataSetChanged();
                            }
                        }
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));
    }

    public void btn_menu(View view) {

        showTermsDialog();
    }

    /* Shop Type dialog */
    private void showTermsDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(DrawerMainActivity.this);
        View view = layoutInflater.inflate(R.layout.dialog_shop_type, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(DrawerMainActivity.this);

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