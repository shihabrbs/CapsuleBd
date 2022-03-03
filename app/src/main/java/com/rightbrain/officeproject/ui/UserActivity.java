package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.adapter.DashboardAdapter;
import com.rightbrain.officeproject.adapter.RecentOrderAdapter;
import com.rightbrain.officeproject.databinding.ActivitySearchBinding;
import com.rightbrain.officeproject.databinding.ActivityUserBinding;
import com.rightbrain.officeproject.model.ModelDashboard;
import com.rightbrain.officeproject.model.ordermodel.DateTime;
import com.rightbrain.officeproject.model.ordermodel.ModelRecentOrder;
import com.rightbrain.officeproject.retrofit.ApiClint;
import com.rightbrain.officeproject.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {
    ActivityUserBinding binding;

    ArrayList<ModelDashboard> list = new ArrayList<>();
    DashboardAdapter dashboardAdapter;

    CompositeDisposable compositeDisposable;
    ApiInterface apiInterface;
    ArrayList<ModelRecentOrder> orders;
    RecentOrderAdapter recentOrderAdapter;

    ArrayList<String> timearrylist;
    ArrayList<DateTime> dateTimes = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityUserBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        timearrylist = new ArrayList<>();
        compositeDisposable = new CompositeDisposable();
        orders = new ArrayList<>();

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        String uniqueCode = mysharedPreferanceSetup.getUniqueCode();

        MysharedPreferance mysharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());
        String userid = ""+mysharedPreferance.getUserId();

        getOrders(uniqueCode,userid);

        list.add(new ModelDashboard("My Profile",R.drawable.ic_usericon));
        list.add(new ModelDashboard("My Orders",R.drawable.ic_box));
        list.add(new ModelDashboard("Notification",R.drawable.ic_notification));
        list.add(new ModelDashboard("Wishlist",R.drawable.ic_favorite));

        dashboardAdapter = new DashboardAdapter(this,list);
        binding.recyclerviewMenu.setLayoutManager(new GridLayoutManager(getApplicationContext(),2));
        dashboardAdapter.notifyDataSetChanged();
        binding.recyclerviewMenu.setAdapter(dashboardAdapter);





        recentOrderAdapter = new RecentOrderAdapter(this,orders);
       /* LinearLayoutManager mLayoutManager ;
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setReverseLayout(false); // THIS ALSO SETS setStackFromBottom to true
        mLayoutManager.setStackFromEnd(true);*/
        binding.recyclerViewOrders.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));



    }

    private void getOrders(String uniqueCode,String userid) {
        compositeDisposable.add(ApiClint.getInstance().orderProcess(""+uniqueCode,""+userid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelRecentOrder>>>() {
                    @Override
                    public void onNext(final Response<List<ModelRecentOrder>> response) {


                        orders.addAll(response.body());
                        recentOrderAdapter.notifyDataSetChanged();
                        binding.recyclerViewOrders.setAdapter(recentOrderAdapter);


                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                }));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();

  /*      Intent intent = new Intent(UserActivity.this,DrawerMainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);*/
    }

    public void btn_back(View view) {
        onBackPressed();
    }
}