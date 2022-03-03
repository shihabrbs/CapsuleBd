package com.rightbrain.officeproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.ordermodel.ModelRecentOrder;
import com.rightbrain.officeproject.ui.OrderDetailsActivity;
import com.rightbrain.officeproject.ui.TrackOrderActivity;

import java.util.ArrayList;

public class RecentOrderAdapter  extends RecyclerView.Adapter<RecentOrderAdapter.MyViewHolder> {
    Context context;
    ArrayList<ModelRecentOrder> allcategory;

    public RecentOrderAdapter(Context context, ArrayList<ModelRecentOrder> allcategory) {
        this.context = context;
        this.allcategory = allcategory;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_order, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(context);
        String uniqueCode = mysharedPreferanceSetup.getUniqueCode();
        String deliveryfee = mysharedPreferanceSetup.getShippingCharge();
        String currency = mysharedPreferanceSetup.getCurrency();

        holder.invoicenumber.setText("#" + allcategory.get(position).getInvoice());
        holder.price.setText(currency+"" + allcategory.get(position).getTotal());
        holder.status.setText("" + allcategory.get(position).getProcess());
        holder.date.setText("" + allcategory.get(position).getCreated()+", "+allcategory.get(position).getCreatedTime());

        String orderstatus = allcategory.get(position).getProcess();

        if (orderstatus.equals("created")){
            holder.status.setBackgroundColor(Color.parseColor("#ADFF7065"));
            holder.status.setTextColor(Color.parseColor("#FFFFFF"));
        }
        else if(orderstatus.equals("wfc")){
            holder.status.setBackgroundColor(Color.parseColor("#A303A9F4"));
            holder.status.setTextColor(Color.parseColor("#FFFFFF"));

        }
        else if(orderstatus.equals("confirm")){
            holder.status.setBackgroundColor(Color.parseColor("#FF9800"));
            holder.status.setTextColor(Color.parseColor("#FFFFFF"));
        }else {
            holder.status.setBackgroundColor(Color.parseColor("#F44336"));
            holder.status.setTextColor(Color.parseColor("#FFFFFF"));
        }

        holder.track.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TrackOrderActivity.class);
                intent.putExtra("status",""+allcategory.get(position).getProcess());
                context.startActivity(intent);
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, OrderDetailsActivity.class);
                intent.putExtra("id",""+allcategory.get(position).getOrderId());
                intent.putExtra("status",""+allcategory.get(position).getProcess());
                intent.putExtra("invoice",""+allcategory.get(position).getInvoice());
                intent.putExtra("date",""+allcategory.get(position).getCreated());
                intent.putExtra("deliverymethod",""+allcategory.get(position).getCashOnDelivery());
                context.startActivity(intent);
            }
        });

        holder.number.setText(String.valueOf(position+1));
    }

    @Override
    public int getItemCount() {
        return allcategory.size();
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,invoicenumber,price,date,status,number;
        ImageView track;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView20);
            price = itemView.findViewById(R.id.textView34);
            date = itemView.findViewById(R.id.textView36);
            status = itemView.findViewById(R.id.textView35);
            number = itemView.findViewById(R.id.number);
            invoicenumber = itemView.findViewById(R.id.textView31);
            track = itemView.findViewById(R.id.imageView19);
        }
    }
}
