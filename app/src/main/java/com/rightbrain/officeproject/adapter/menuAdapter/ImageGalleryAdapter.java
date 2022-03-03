package com.rightbrain.officeproject.adapter.menuAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.sidebar.Gallery;
import com.rightbrain.officeproject.room.RecyclerViewItemClick;

import java.util.List;

public class ImageGalleryAdapter extends RecyclerView.Adapter<ImageGalleryAdapter.MyViewHolder> {
    Context context;
    List<Gallery> sizeArrayList;

    RecyclerViewItemClick clickListenerrr;
    private int row_index = 0;

    public ImageGalleryAdapter(Context context, List<Gallery> sizeArrayList) {
        this.context = context;
        this.sizeArrayList = sizeArrayList;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_image_gallery, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Glide
                .with(context)
                .load("http://" + sizeArrayList.get(position).getImagePath())
                .into(holder.imageView);


        if(row_index==position){

            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.image_border));
            //  holder.constraintLayout.setBackgroundColor(context.getResources().getColor(android.R.color.holo_orange_dark));
            // holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.blue));
            //   holder.name.setTextColor(Color.WHITE);
        }
        else
        {
            //   holder.constraintLayout.setBackgroundColor(context.getResources().getColor(android.R.color.white));
            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.image_border_gray));

        }
    }

    @Override
    public int getItemCount() {
        return sizeArrayList.size();
    }

    public void SetItemClickListenerrr(RecyclerViewItemClick itemClickListener)
    {
        this.clickListenerrr = itemClickListener;
    }

    public void SetPositionnn(int position)
    {
        this.row_index = position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ImageView imageView;
        ConstraintLayout constraintLayout;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.galleryimg);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            itemView.setOnClickListener(this);
        }
        @Override
        public void onClick(View view) {
            clickListenerrr.onClickImageGallery(view,getAdapterPosition());
        }
    }
}
