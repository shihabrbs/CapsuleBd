package com.rightbrain.officeproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.ModelAll;
import com.rightbrain.officeproject.ui.OfferActivity;

import java.util.ArrayList;

import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;

public class DiscountAdapter extends RecyclerView.Adapter<DiscountAdapter.MyViewHolder> {
    Context context;
    ArrayList<ModelAll> allcategory;


    public DiscountAdapter(Context context, ArrayList<ModelAll> allcategory) {
        this.context = context;
        this.allcategory = allcategory;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_discount,parent,false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.disount.setText("30% "+allcategory.get(position).getId());

      Glide
                .with(context)
                .load(allcategory.get(position).getThumbnailUrl())
                .centerCrop()
                .into(holder.imageView);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              /*  String category = allcategory.get(position).getCategories(); */
                Intent intent = new Intent(context, OfferActivity.class);
                intent.addFlags(FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(intent);
            }
        });
        
        holder.ordernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Order Now Product", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return allcategory.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView disount,ordernow;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            disount = itemView.findViewById(R.id.textView17);
            ordernow = itemView.findViewById(R.id.textView19);
            imageView = itemView.findViewById(R.id.imageView15);
        }
    }
}
