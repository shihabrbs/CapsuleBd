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
import com.rightbrain.officeproject.model.ModelCartRoom;
import com.rightbrain.officeproject.ui.CartActivity;

import java.util.ArrayList;

public class PaymentProductDetailsAdapter extends RecyclerView.Adapter<PaymentProductDetailsAdapter.MyViewHolder> {
    ArrayList<ModelCartRoom> carts;
    Context context;
    CartActivity cartActivity;

    public PaymentProductDetailsAdapter(ArrayList<ModelCartRoom> carts, Context context) {
        this.carts = carts;
        this.context = context;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_details_product, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(context);
        String uniqueCode = mysharedPreferanceSetup.getUniqueCode();
        String deliveryfee = mysharedPreferanceSetup.getShippingCharge();
        String currency = mysharedPreferanceSetup.getCurrency();

        holder.textView.setText(carts.get(position).getName());
        holder.quantity.setText("x " + carts.get(position).getQuantity());
        holder.price.setText(currency+"" + carts.get(position).getPrice());
        String img = carts.get(position).getUrl();

        Glide
                .with(context)
                .load(img)
                .into(holder.image);
    }

    @Override
    public int getItemCount() {
        return carts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView textView, quantity, price;
        ImageView image;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView52);
            price = itemView.findViewById(R.id.textView53);
            quantity = itemView.findViewById(R.id.textView63);
            image = itemView.findViewById(R.id.img_product);
        }
    }
}
