package com.rightbrain.officeproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.ModelCartRoom;
import com.rightbrain.officeproject.room.CartRepository;
import com.rightbrain.officeproject.room.OnDataSend;
import com.rightbrain.officeproject.ui.CartActivity;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.MyViewHolder> {
    ArrayList<ModelCartRoom>carts;
    Context context;
    CartActivity cartActivity;

    CartRepository repository;
    private OnDataSend dataSend;
    RadioButton radioButton;
    String selecttedtext;
    int total = 0;
    int taka = 0;
    int quantity;

    int disquantity;
    int distotal = 0;
    int distaka = 0;
    int offer;
    int totaldiscount = 0;

    int dispercent;
    int sendSubtotal = 0;
    String value = "";




    public CartAdapter(ArrayList<ModelCartRoom>carts, Context context, CartRepository repository,OnDataSend dataSend) {
        this.carts = carts;
        this.context = context;
        this.repository = repository;
        this.dataSend = dataSend;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(context);
        String uniqueCode = mysharedPreferanceSetup.getUniqueCode();
        String deliveryfee = mysharedPreferanceSetup.getShippingCharge();
        String currency = mysharedPreferanceSetup.getCurrency();

        holder.textView.setText(carts.get(position).getName());

        holder.textView.setText(carts.get(position).getName());
        holder.price.setText(currency+""+carts.get(position).getPrice());
      //  holder.discountprice.setText("৳"+carts.get(position).get());
        holder.cart_quantity.setText(carts.get(position).getQuantity());
        String img = carts.get(position).getUrl();
        int qty = Integer.parseInt(carts.get(position).getQuantity());
        int price = Integer.parseInt(carts.get(position).getPrice());

        int quantity_price = qty*price;

        holder.qtyprice.setText(currency+""+quantity_price);

        Glide
                .with(context)
                .load(img)
                .into(holder.image);

        holder.deletebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // String productid = carts.get(position).getProductId();
                try {
                    CartRepository repository = new CartRepository(context);
                    ModelCartRoom modelCartRoom = new ModelCartRoom();
                    modelCartRoom.setId(carts.get(position).getId());
                    // modelCartRoom.setProductId(carts.get(position).getProductId());


                    repository.delete(modelCartRoom);
                    carts.clear();
                    carts.remove(carts.get(position).getId() - 1);

                    //taka =0;

                    notifyDataSetChanged();

                } catch (Exception e) {
                    // Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                }
                taka = 0;
                total = 0;

                distotal = 0;
                distaka = 0;
            }
        });
        
       /* holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               // String productid = carts.get(position).getProductId();
                try {
                    CartRepository repository = new CartRepository(context);
                    ModelCartRoom modelCartRoom = new ModelCartRoom();
                   modelCartRoom.setId(carts.get(position).getId());
                   // modelCartRoom.setProductId(carts.get(position).getProductId());


                    repository.delete(modelCartRoom);
                    carts.clear();
                    carts.remove(carts.get(position).getId() - 1);

                    //taka =0;

                    notifyDataSetChanged();

                } catch (Exception e) {
                    // Toast.makeText(context, ""+e, Toast.LENGTH_SHORT).show();
                }
                taka = 0;
                total = 0;

                distotal = 0;
                distaka = 0;
            }
        });*/

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
               // holder.delete.setVisibility(View.VISIBLE);
                return false;
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             //   Toast.makeText(context, ""+carts.get(position).getProductId(), Toast.LENGTH_SHORT).show();
            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(context, ""+carts.get(position).getProductId(), Toast.LENGTH_SHORT).show();

                int quantitys = Integer.parseInt(carts.get(position).getQuantity());
                quantitys += 1;

                //TODO ai plus hoya data room a update kora lagbe

                CartRepository repository = new CartRepository(context);
                carts.get(position).setQuantity("" + quantitys);
                repository.update(carts.get(position));
                holder.cart_quantity.setText(carts.get(position).getQuantity());


                taka = 0;
                total = 0;


                distotal = 0;
                distaka = 0;



            }
        });


        holder.minus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productid = ""+carts.get(position).getProductId();

             //   int productid = Integer.parseInt(ids);
                // holder.quntity.setVisibility(View.VISIBLE);
                int quantityy = Integer.parseInt(""+holder.cart_quantity.getText());
                quantityy--;
                if (quantityy <= 0) {

                    try {
                        CartRepository repository = new CartRepository(context);
                        ModelCartRoom modelCartRoom = new ModelCartRoom();
                        modelCartRoom.setId(carts.get(position).getId());

                        repository.delete(modelCartRoom);
                        carts.clear();
                        carts.remove(carts.get(position).getId() - 1);


                        notifyDataSetChanged();

                    } catch (Exception e) {

                    }


                } /*else if(quantityy<0){
                    repository.updateRowData(1, productid);
                    holder.cart_quantity.setText("1");
                }*/
                else{

                    repository.updateRowData(quantityy, productid);
                    holder.cart_quantity.setText("" + quantityy);


                }

                taka = 0;
                total = 0;

                distotal = 0;
                distaka = 0;


            }
        });

      /*  holder.itemView.setOnTouchListener(new View.OnTouchListener() {

            float _xSwipe1;
            float _xSwipe2;

            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                switch (event.getAction())
                {
                    case MotionEvent.ACTION_DOWN:
                        _xSwipe1 = event.getX();
                        break;

                    case MotionEvent.ACTION_UP:
                        _xSwipe2 = event.getX();

                        float deltaX = _xSwipe2 - _xSwipe1;

                        if (deltaX < 0)
                        {

                            holder.delete.setVisibility(View.GONE);
                            holder.plus.setVisibility(View.VISIBLE);
                            holder.minus.setVisibility(View.VISIBLE);
                            holder.constraintLayout.setVisibility(View.VISIBLE);
                        }

                        else if (deltaX >0)
                        {

                            holder.delete.setVisibility(View.VISIBLE);
                            holder.plus.setVisibility(View.GONE);
                            holder.minus.setVisibility(View.GONE);
                            holder.constraintLayout.setVisibility(View.GONE);
                        }

                        break;
                }

                return false;
            }
        });
*/
        //subtotal calculation start
        quantity = Integer.parseInt(carts.get(position).getQuantity());
        taka = Integer.parseInt(carts.get(position).getPrice());
        sendSubtotal = getSubtotal(quantity, taka);
        //subtotal calculation end


        dataSend.totalPrice(sendSubtotal+"");

    }

    public int getSubtotal(int quantity,int amount){
        int totalamount=0;


        for(int i = 0 ; i < carts.size(); i++) {
            int qun = Integer.parseInt(carts.get(i).getQuantity());
            int price = (Integer.parseInt(carts.get(i).getPrice()))*qun;




            totalamount = totalamount + price;
        }
        return totalamount;
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView,price,cart_quantity,discountprice,qtyprice;
        ImageView delete,plus,minus,image,deletebtn;
        ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView7);
            qtyprice = itemView.findViewById(R.id.textView65);
            cart_quantity = itemView.findViewById(R.id.cartquantity);
            image = itemView.findViewById(R.id.imageView13);
            deletebtn = itemView.findViewById(R.id.deletebtn);
            delete = itemView.findViewById(R.id.deleteimg);
            plus = itemView.findViewById(R.id.imageView14);
            price = itemView.findViewById(R.id.textView8);
            discountprice = itemView.findViewById(R.id.textView6);
            minus = itemView.findViewById(R.id.minusimg);
            constraintLayout = itemView.findViewById(R.id.constraintLayout7);
        }
    }
}
