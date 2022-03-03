package com.rightbrain.officeproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.ordermodel.OrderItem;

import java.util.List;

public class OrderDetailsAdapter extends RecyclerView.Adapter<OrderDetailsAdapter.MyViewHolder> {
    Context context;
    List<OrderItem> orderItems;
    public OrderDetailsAdapter(Context context, List<OrderItem> orderItems) {
        this.context = context;
        this.orderItems = orderItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_product,parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(context);
        String uniqueCode = mysharedPreferanceSetup.getUniqueCode();
        String deliveryfee = mysharedPreferanceSetup.getShippingCharge();
        String currency = mysharedPreferanceSetup.getCurrency();


      String path = ""+orderItems.get(position).getImagePath();

      if (path.equals("")){
          Glide
                  .with(context)
                  .load("https://www.freeiconspng.com/thumbs/no-image-icon/no-image-icon-6.png")
                  .into(holder.imageView);
      }else {
          Glide
                  .with(context)
                  .load("" + orderItems.get(position).getImagePath())
                  .into(holder.imageView);
      }

        holder.name.setText(""+orderItems.get(position).getName());
        holder.quantity.setText("x"+orderItems.get(position).getQuantity());
        holder.price.setText(currency+""+orderItems.get(position).getPrice());


    }

    @Override
    public int getItemCount() {
        return orderItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,quantity,price;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView52);
            quantity = itemView.findViewById(R.id.textView63);
            price = itemView.findViewById(R.id.textView53);
            imageView = itemView.findViewById(R.id.img_product);
        }
    }
}
