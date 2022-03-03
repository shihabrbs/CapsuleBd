package com.rightbrain.officeproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.sidebar.BrandItem;
import com.rightbrain.officeproject.ui.ProductsActivity;

import java.util.List;

public class SlideBarBrandAdapter extends RecyclerView.Adapter<SlideBarBrandAdapter.MyViewHolder> {

    private Context context;
    private List<BrandItem> categoryItems;

    public SlideBarBrandAdapter(Context context, List<BrandItem> categoryItems) {
        this.context = context;
        this.categoryItems = categoryItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_sidebar_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.iconName.setText(categoryItems.get(position).getName());
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("module","brand");
                intent.putExtra("catid",""+categoryItems.get(position).getBrandId());
                intent.putExtra("id",""+position);
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return categoryItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView iconName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iconName = itemView.findViewById(R.id.icon_name);
        }
    }
}
