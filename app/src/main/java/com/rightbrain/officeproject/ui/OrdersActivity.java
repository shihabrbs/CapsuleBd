package com.rightbrain.officeproject.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.View;

import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.adapter.OrdersAdapter;
import com.rightbrain.officeproject.databinding.ActivityOrdersBinding;
import com.rightbrain.officeproject.model.ModelOrderList;
import com.rightbrain.officeproject.model.ordermodel.DateTime;
import com.rightbrain.officeproject.retrofit.ApiClint;
import com.rightbrain.officeproject.retrofit.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class OrdersActivity extends AppCompatActivity {
    ActivityOrdersBinding binding;
    CompositeDisposable compositeDisposable;
    ApiInterface apiInterface;
    ArrayList<ModelOrderList> orders;
    ArrayList<String> timearrylist;
    OrdersAdapter ordersAdapter;
    ArrayList<DateTime> dateTimes = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityOrdersBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(getApplicationContext());
        String uniqueCode = mysharedPreferanceSetup.getUniqueCode();

        MysharedPreferance mysharedPreferance = MysharedPreferance.getPreferences(getApplicationContext());
        String userid = ""+mysharedPreferance.getUserId();
        compositeDisposable = new CompositeDisposable();
        orders = new ArrayList<>();
        timearrylist = new ArrayList<>();




        ordersAdapter = new OrdersAdapter(this,orders);
    /*    binding.recyclerViewOrders.setLayoutManager(new GridLayoutManager(getApplicationContext(),1));
*/
       //  mLayoutManager.setReverseLayout(false); // THIS ALSO SETS setStackFromBottom to true
        //mLayoutManager.setStackFromEnd(true);
        LinearLayoutManager mLayoutManager ;
        mLayoutManager = new LinearLayoutManager(getApplicationContext());
        mLayoutManager.setReverseLayout(false); // THIS ALSO SETS setStackFromBottom to true

        binding.recyclerViewOrders.setLayoutManager(mLayoutManager);


        getOrders(uniqueCode,userid);

    }

    private void getOrders(String uniqueCode,String userid) {

        compositeDisposable.add(ApiClint.getInstance().orderList(""+uniqueCode, ""+userid)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribeWith(new DisposableObserver<Response<List<ModelOrderList>>>() {
                    @Override
                    public void onNext(final Response<List<ModelOrderList>> response) {

                     //   Toast.makeText(OrdersActivity.this, ""+response.body().get(0).getDateTime().getDate(), Toast.LENGTH_SHORT).show();
                        orders.addAll(response.body());
                        ordersAdapter.notifyDataSetChanged();
                        binding.recyclerViewOrders.setAdapter(ordersAdapter);


                     /*    String datetime =response.body().get(i).getDateTime().getDate();

                         String[] datetimedata = datetime.split(" ");

                       String datee = datetimedata[0];
                        String  time = datetimedata[1];

                         //String date_s = "00:30:59.000000";
                         SimpleDateFormat dt = new SimpleDateFormat("hh:mm:ss");
                         Date date = null;
                         try {
                             date = dt.parse(time);
                         } catch (ParseException e) {
                             e.printStackTrace();
                         }
                         SimpleDateFormat dt1 = new SimpleDateFormat("hh:mm:ss");

                         //Toast.makeText(OrdersActivity.this, ""+dt1.format(date), Toast.LENGTH_SHORT).show();
                         timearrylist.add(""+dt1.format(date));*/






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
    }

    public void btn_back(View view) {
        onBackPressed();
    }

    /*   @Override
    public void onBackPressed() {

            Intent intent = new Intent(OrdersActivity.this,DrawerMainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);

    }*/
}