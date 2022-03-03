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
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.modelProductDetails.RelatedProduct;
import com.rightbrain.officeproject.ui.ProductDetails;

import java.util.List;

public class RelatedProductAdapter extends RecyclerView.Adapter<RelatedProductAdapter.MyViewHolder> {
    Context context;
    List<RelatedProduct> relatedProductList;


    public RelatedProductAdapter(Context context, List<RelatedProduct> relatedProductList) {
        this.context = context;
        this.relatedProductList = relatedProductList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_related_product, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(context);
        String uniqueCode = mysharedPreferanceSetup.getUniqueCode();
        String deliveryfee = mysharedPreferanceSetup.getShippingCharge();
        String currency = mysharedPreferanceSetup.getCurrency();

        holder.name.setText(""+relatedProductList.get(position).getName());
        holder.price.setText(currency+""+relatedProductList.get(position).getPrice());
        holder.unit.setText(" /"+relatedProductList.get(position).getUnitName());
        String unitname = ""+relatedProductList.get(position).getUnitName();

        if (unitname.equals("null")){
            holder.unit.setVisibility(View.GONE);
        }else {
            holder.unit.setVisibility(View.VISIBLE);
        }


        String img = relatedProductList.get(position).getImagePath();

        if (relatedProductList.get(position).getImagePath().equals("")){
            Glide
                    .with(context)
                    .load(R.drawable.demoimg)
                    .into(holder.imageView);
        }else {

            Glide
                    .with(context)
                    .load("http://"+img)
                    .into(holder.imageView);
        }

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = relatedProductList.get(position).getProductId();
                int categoryid = relatedProductList.get(position).getCategoryId();
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("productid",""+id);
                intent.putExtra("catid",""+categoryid);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = relatedProductList.get(position).getProductId();
                int categoryid = relatedProductList.get(position).getCategoryId();
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("productid",""+id);
                intent.putExtra("catid",""+categoryid);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return relatedProductList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,unit;
        ImageView imageView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView7);
            price = itemView.findViewById(R.id.textView8);
            unit = itemView.findViewById(R.id.unitText);
            imageView = itemView.findViewById(R.id.imageView7);

        }
    }
}
