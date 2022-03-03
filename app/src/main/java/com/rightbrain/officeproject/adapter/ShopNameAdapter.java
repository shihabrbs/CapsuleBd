package com.rightbrain.officeproject.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.ModelSetup;
import com.rightbrain.officeproject.room.CartRepository;
import com.rightbrain.officeproject.room.ItemClickListenerShop;
import com.rightbrain.officeproject.ui.DrawerMainActivity;

import java.util.ArrayList;

import butterknife.BindView;

public class ShopNameAdapter extends RecyclerView.Adapter<ShopNameAdapter.MyViewHolder> {
    Context context;
    ArrayList<ModelSetup> setupArrayList;
    boolean isItemExpanded = false;
    private ItemClickListenerShop clickListener;
    private int row_index = -1;
    CartRepository repository;

    @BindView(R.id.discount_arrow)
    ImageView discountArrow;

    public ShopNameAdapter(Context context, ArrayList<ModelSetup> setupArrayList) {
        this.context = context;
        this.setupArrayList = setupArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_shop_name, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText("" + setupArrayList.get(position).getName());
        holder.description.setText(""+setupArrayList.get(position).getDescription());

        if(row_index==position){

          //  holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.category_border_down));
            //  holder.constraintLayout.setBackgroundColor(context.getResources().getColor(android.R.color.holo_orange_dark));
            // holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.blue));
            //   holder.name.setTextColor(Color.WHITE);

            holder.linearLayout.setVisibility(View.VISIBLE);
           /* if (!isItemExpanded) {

                isItemExpanded = true;
                holder.linearLayout.setVisibility(View.VISIBLE);
                Common.toggleArrow(discountArrow);


            }else
            {
                isItemExpanded = false;
                Common.toggleArrow(discountArrow);
                holder.linearLayout.setVisibility(View.GONE);
            }*/
        }
        else
        {
           // holder.constraintLayout.setBackgroundColor(context.getResources().getColor(android.R.color.white));
            //   holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.signup_round_btn_gray));
           // holder.name.setTextColor(Color.BLACK);
            holder.linearLayout.setVisibility(View.GONE);
        }

        holder.changeshop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                repository = new CartRepository(context);

                MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(context);
                repository.deleteAll();
                mysharedPreferanceSetup.setUniqueCode(""+setupArrayList.get(position).getActiveKey());
                mysharedPreferanceSetup.setShopName(""+setupArrayList.get(position).getName());
                mysharedPreferanceSetup.setShippingCharge(""+setupArrayList.get(position).getShippingCharge());
                mysharedPreferanceSetup.setCurrency(""+setupArrayList.get(position).getCurrency());
                mysharedPreferanceSetup.setLogo(""+setupArrayList.get(position).getLogo());
                mysharedPreferanceSetup.setCartProcess(""+setupArrayList.get(position).getCartProcess());

                if (setupArrayList.get(position).getLogo().equals("")){

                    mysharedPreferanceSetup.setLogo("");
                }else {
                    mysharedPreferanceSetup.setLogo(""+setupArrayList.get(position).getLogo());

                }

                String name = setupArrayList.get(position).getName();
                if (name.equals("Hawa Mithi")){
                    mysharedPreferanceSetup.setShopName("FamilyMart");
                }else {
                    mysharedPreferanceSetup.setShopName(""+setupArrayList.get(position).getName());
                }

                Intent  intent = new Intent(context, DrawerMainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
              //  showTermsDialog();
            }
        });

    }

    /* Shop Type dialog */
    private void showTermsDialog() {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.dialog_change_shop_confirm, null);
        final AlertDialog.Builder builder = new AlertDialog.Builder(context);

        builder.setView(view);



        final AlertDialog alertDialog = builder.create();
        alertDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        alertDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));


        Button yes = view.findViewById(R.id.btn_yes);
        Button no = view.findViewById(R.id.btn_no);

        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Toast.makeText(context, "All Ok", Toast.LENGTH_SHORT).show();
            }
        });

        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
                Toast.makeText(context, "Not Ok", Toast.LENGTH_SHORT).show();
            }
        });


      /*  Button close = view.findViewById(R.id.savebtnid);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerViewShop);
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alertDialog.dismiss();
            }
        });*/
/*
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
                }));*/


        alertDialog.show();

    }

    @Override
    public int getItemCount() {
        return setupArrayList.size();
    }

    public void SetItemClickListenerShop(ItemClickListenerShop itemClickListener)
    {
        this.clickListener = itemClickListener;
    }

    public void SetPositionShop(int position)
    {
        this.row_index = position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name,description;
        ConstraintLayout constraintLayout;
        LinearLayout linearLayout;
        Button changeshop;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.shopname);
            description = itemView.findViewById(R.id.tv_description);
            constraintLayout = itemView.findViewById(R.id.constraintLayout2);
            linearLayout = itemView.findViewById(R.id.linearLayout);
            changeshop = itemView.findViewById(R.id.btn_changeshop);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListener.onClickShop(view,getAdapterPosition());
        }
    }
}
