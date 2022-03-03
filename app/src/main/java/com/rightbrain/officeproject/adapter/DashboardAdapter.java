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
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.ModelDashboard;
import com.rightbrain.officeproject.ui.MyProfileActivity;
import com.rightbrain.officeproject.ui.OrdersActivity;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.MyViewHolder> {
    Context context;
    ArrayList<ModelDashboard> allProducts;

    public DashboardAdapter(Context context, ArrayList<ModelDashboard> allProducts) {
        this.context = context;
        this.allProducts = allProducts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dashboard, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.text.setText(allProducts.get(position).getName());
        int img = allProducts.get(position).getPic();
        Glide
                .with(context)
                .load(img)
                .into(holder.image);

        holder.constraintLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String pos = allProducts.get(position).getName();

                if (pos.equals("My Profile")){
                    Intent intent = new Intent(context, MyProfileActivity.class);
                  //  intent.putExtra("activity","dashboard");
                    context.startActivity(intent);
                }else if (pos.equals("My Orders")){
                    Intent intent = new Intent(context, OrdersActivity.class);
                    intent.putExtra("activity","dashboard");
                    context.startActivity(intent);
                }
                else {
                    Toast.makeText(context, "Work in Progress", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return allProducts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView text;
        ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            image = itemView.findViewById(R.id.imageview);
            text = itemView.findViewById(R.id.textView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
        }
    }
}
