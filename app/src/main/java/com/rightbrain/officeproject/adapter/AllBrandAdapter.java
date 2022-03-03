package com.rightbrain.officeproject.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.ModelBrand;
import com.rightbrain.officeproject.ui.AllBrandActivity;
import com.rightbrain.officeproject.ui.ProductsActivity;

import java.util.ArrayList;

public class AllBrandAdapter extends RecyclerView.Adapter<AllBrandAdapter.MyViewHolder> {
    AllBrandActivity context;
    ArrayList<ModelBrand> allbrand;


    public AllBrandAdapter(AllBrandActivity context, ArrayList<ModelBrand> allbrand) {
        this.context = context;
        this.allbrand = allbrand;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_brand, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText("" + allbrand.get(position).getName());
        String img = allbrand.get(position).getImagePath();
        Glide
                .with(context)
                .load("http://"+img)
                .into(holder.imageView);

        /* Glide.with(context).load(allcategory.get(1).getImagePath()).placeholder(R.drawable.healthyfood).dontAnimate().into(holder.imageView);
         */
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("module","brand");
                intent.putExtra("catid",""+allbrand.get(position).getBrandId());
                intent.putExtra("id",""+position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allbrand.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView imageView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView3);
            imageView = itemView.findViewById(R.id.item_img);
        }
    }
}
