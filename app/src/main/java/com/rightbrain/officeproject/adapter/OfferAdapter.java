package com.rightbrain.officeproject.adapter;

import android.content.Context;
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
import com.rightbrain.officeproject.model.sidebar.DiscountItem;
import com.rightbrain.officeproject.ui.ProductsActivity;

import java.util.List;

public class OfferAdapter extends RecyclerView.Adapter<OfferAdapter.MyViewHolder> {
    Context context;
    List<DiscountItem> allcategory;

    public OfferAdapter(Context context, List<DiscountItem> allcategory) {
        this.context = context;
        this.allcategory = allcategory;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_discount, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText("" + allcategory.get(position).getName());

        String img = allcategory.get(position).getImagePath();

        if (img.equals("")){
            Glide
                    .with(context)
                    .load("https://www.freeiconspng.com/thumbs/no-image-icon/no-image-icon-6.png")
                    .into(holder.imageView);
        }else{
            Glide
                    .with(context)
                    .load("http://"+img)
                    .into(holder.imageView);
        }

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("module","discount");
                intent.putExtra("catid",""+allcategory.get(position).getDiscountId());
                intent.putExtra("id",""+position);
                context.startActivity(intent);
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
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView17);
            imageView = itemView.findViewById(R.id.imageView15);
        }
    }
}
