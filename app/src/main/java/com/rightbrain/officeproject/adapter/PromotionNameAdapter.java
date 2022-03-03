package com.rightbrain.officeproject.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.sidebar.PromotionItem;
import com.rightbrain.officeproject.room.ItemClickListener;

import java.util.List;

public class PromotionNameAdapter extends RecyclerView.Adapter<PromotionNameAdapter.MyViewHolder> {
    Context context;
    List<PromotionItem> allcategory;
    private ItemClickListener clickListener;
    private int row_index = 0;

    public PromotionNameAdapter(Context context, List<PromotionItem> allcategory) {
        this.context = context;
        this.allcategory = allcategory;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_text, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText("" + allcategory.get(position).getName());

        if(row_index==position){

            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.category_border_down));
            //  holder.constraintLayout.setBackgroundColor(context.getResources().getColor(android.R.color.holo_orange_dark));
            // holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.blue));
            //   holder.name.setTextColor(Color.WHITE);
        }
        else
        {
            holder.constraintLayout.setBackgroundColor(context.getResources().getColor(android.R.color.white));
            //   holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.signup_round_btn_gray));
            holder.name.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return allcategory.size();
    }

    public void SetItemClickListener(ItemClickListener itemClickListener)
    {
        this.clickListener = itemClickListener;
    }

    public void SetPosition(int position)
    {
        this.row_index = position;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.catname);
            constraintLayout = itemView.findViewById(R.id.constraintLayoutcolor);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            clickListener.onClickpromotion(view,getAdapterPosition());
        }
    }
}
