package com.rightbrain.officeproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.room.RecyclerViewItemClick;

import java.util.List;

public class ColorAdapter extends RecyclerView.Adapter<ColorAdapter.MyViewHolder> {
    Context context;
    List<com.rightbrain.officeproject.model.modelProductDetails.Color> colorArrayList;

    RecyclerViewItemClick clickListener;
    private int row_index = -1;

    public ColorAdapter(Context context, List<com.rightbrain.officeproject.model.modelProductDetails.Color> colorArrayList) {
        this.context = context;
        this.colorArrayList = colorArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_colors, parent, false);

        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceType")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        String color = colorArrayList.get(position).getColorPlate();
        int nnn = Color.parseColor(""+color);
     //  DrawableCompat.setTint(holder.constraintLayout.getBackground(), ContextCompat.getColor(context, Color.parseColor("#FFC107")));
      //  DrawableCompat.setTint(holder.constraintLayout.getBackground(), ContextCompat.getColor(context, R.color.blue));


     //  DrawableCompat.setTint(holder.constraintLayout.getBackground(),ContextCompat.getColor(context,nnn));


        Drawable backgroundDrawable = DrawableCompat.wrap(holder.constraintLayout.getBackground()).mutate();
        DrawableCompat.setTint(backgroundDrawable, nnn);

       // holder.constraintLayout.setBackgroundColor(Color.parseColor("#FFC107"));

        if(row_index==position){

            ///holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.promotion_border));
            holder.checkimageview.setVisibility(View.VISIBLE);
            //   holder.constraintLayout.setBackgroundColor(context.getResources().getColor(android.R.color.holo_orange_dark));
            // holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.blue));
            //   holder.name.setTextColor(Color.WHITE);
        }
        else
        {
            holder.checkimageview.setVisibility(View.GONE);
            // holder.constraintLayout.setBackgroundColor(context.getResources().getColor(android.R.color.white));
          ///  holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.promotion_border_gray));
            //  holder.name.setTextColor(Color.BLACK);
        }

    }

    @Override
    public int getItemCount() {
        return colorArrayList.size();
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
        ConstraintLayout constraintLayout;
        ImageView checkimageview;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.constraintLayoutid);
            checkimageview = itemView.findViewById(R.id.checkimage);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            clickListener.onClick(view,getAdapterPosition());
        }
    }
}
