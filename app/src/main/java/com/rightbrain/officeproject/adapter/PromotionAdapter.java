package com.rightbrain.officeproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.Category;
import com.rightbrain.officeproject.room.RecyclerViewItemClick;

import java.util.ArrayList;

public class PromotionAdapter extends RecyclerView.Adapter<PromotionAdapter.MyViewHolder> {
    Context context;
    ArrayList<Category> allcategory;

  //  RecyclerViewItemClick recyclerViewItemClick;
    RecyclerViewItemClick clickListener;
    private int row_index = -1;

    public PromotionAdapter(Context context, ArrayList<Category> allcategory/*,RecyclerViewItemClick recyclerViewItemClick*/) {
        this.context = context;
        this.allcategory = allcategory;
       // this.recyclerViewItemClick = recyclerViewItemClick;

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_promotion, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.name.setText("" + allcategory.get(position).getName());
        String img = allcategory.get(position).getImagePath();

        if (allcategory.get(position).getImagePath().equals("")){
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



        if(row_index==position){

            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.promotion_border));
           //   holder.constraintLayout.setBackgroundColor(context.getResources().getColor(android.R.color.holo_orange_dark));
            // holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.blue));
            //   holder.name.setTextColor(Color.WHITE);
        }
        else
        {
           // holder.constraintLayout.setBackgroundColor(context.getResources().getColor(android.R.color.white));
              holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.promotion_border_gray));
          //  holder.name.setTextColor(Color.BLACK);
        }


        /* Glide.with(context).load(allcategory.get(1).getImagePath()).placeholder(R.drawable.healthyfood).dontAnimate().into(holder.imageView);
         */
       /* holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                *//* String category = allcategory.get(position).getCategories();*//*
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("catid",""+allcategory.get(position).getCategoryId());
                context.startActivity(intent);

            }
        });*/
    }

    @Override
    public int getItemCount() {
        return allcategory.size();
    }

    public void SetItemClickListener(RecyclerViewItemClick itemClickListener)
    {
        this.clickListener = itemClickListener;
    }

    public void SetPosition(int position)
    {
        this.row_index = position;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name;
        ImageView imageView;
        ConstraintLayout constraintLayout;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView3);
            imageView = itemView.findViewById(R.id.item_img);
            constraintLayout = itemView.findViewById(R.id.cardView);

            itemView.setOnClickListener(this);

           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    recyclerViewItemClick.onItemClick(getAdapterPosition());


                }
            });*/

        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view,getAdapterPosition());
        }
    }
}
