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
import com.rightbrain.officeproject.model.sidebar.PromotionItem;
import com.rightbrain.officeproject.ui.ProductsActivity;

import java.util.List;

public class SlideBarPromotionAdapter extends RecyclerView.Adapter<SlideBarPromotionAdapter.MyViewHolder> {

    private Context context;
    private List<PromotionItem> promotionItems;

    public SlideBarPromotionAdapter(Context context, List<PromotionItem> promotionItems) {
        this.context = context;
        this.promotionItems = promotionItems;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_for_sidebar_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.iconName.setText(promotionItems.get(position).getName());
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("module","Promotion");
                intent.putExtra("catid",""+promotionItems.get(position).getPromotionId());
                intent.putExtra("id",""+position);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return promotionItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView iconName;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            iconName = itemView.findViewById(R.id.icon_name);
        }
    }
}
