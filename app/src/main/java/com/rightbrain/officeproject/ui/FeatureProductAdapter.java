package com.rightbrain.officeproject.ui;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.ModelProducts;

import java.util.ArrayList;

public class FeatureProductAdapter extends RecyclerView.Adapter<FeatureProductAdapter.MyViewHolder> {
    Context context;
    ArrayList<ModelProducts> allcategory;


    public FeatureProductAdapter(Context context, ArrayList<ModelProducts> allcategory) {
        this.context = context;
        this.allcategory = allcategory;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_products, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText("" + allcategory.get(position).getName());
        String img = allcategory.get(position).getImagePath();
        Glide
                .with(context)
                .load("http://"+img)
                .into(holder.imageView);

        /* Glide.with(context).load(allcategory.get(1).getImagePath()).placeholder(R.drawable.healthyfood).dontAnimate().into(holder.imageView);
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* String category = allcategory.get(position).getCategories();*/
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("catid",""+allcategory.get(position).getCategoryId());
                context.startActivity(intent);

            }
        });
        
        holder.cartbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Add to cart", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return allcategory.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageView;
        ConstraintLayout cartbtn;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView7);
            imageView = itemView.findViewById(R.id.imageView7);
            cartbtn = itemView.findViewById(R.id.constraintLayout3);
        }
    }
}
