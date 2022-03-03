package com.rightbrain.officeproject.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.modelProductDetails.Measurement;
import com.rightbrain.officeproject.room.RecyclerViewItemClick;

import java.util.List;

public class SizeAdapter extends RecyclerView.Adapter<SizeAdapter.MyViewHolder> {
    Context context;
    List<Measurement> sizeArrayList;

    RecyclerViewItemClick clickListenerr;
    private int row_index = -1;

    public SizeAdapter(Context context, List<Measurement> sizeArrayList) {
        this.context = context;
        this.sizeArrayList = sizeArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_size, parent, false);

        return new MyViewHolder(view);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        int color = R.color.orenge;
        int whiteblue = R.color.white_blue;
        int white = R.color.white;

        //  DrawableCompat.setTint(holder.constraintLayout.getBackground(), ContextCompat.getColor(context, Color.parseColor("#2e2e2e")));

        holder.textView.setText(""+sizeArrayList.get(position).getName());

        if(row_index==position){
          //  DrawableCompat.setTint(holder.constraintLayout.getBackground(), ContextCompat.getColor(context, color));
            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.image_border));
           // holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.promotion_border));
           // holder.textView.setTextColor(white);
         //   holder.textView.setTextColor(Color.WHITE);
              // holder.constraintLayout.setBackgroundColor(context.getResources().getColor(color));
          //   holder.constraintLayout.setBackgroundColor(context.getResources().getColor(R.color.blue));
            //   holder.name.setTextColor(Color.WHITE);
        }
        else
        {
          //  holder.textView.setTextColor(Color.parseColor("#2196F3"));
          //  DrawableCompat.setTint(holder.constraintLayout.getBackground(), ContextCompat.getColor(context, whiteblue));
            holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.image_border_gray));
            // holder.constraintLayout.setBackgroundColor(context.getResources().getColor(android.R.color.white));
            ///  holder.constraintLayout.setBackground(context.getResources().getDrawable(R.drawable.promotion_border_gray));
            //  holder.name.setTextColor(Color.BLACK);
        }
    }

    @Override
    public int getItemCount() {
        return sizeArrayList.size();
    }

    public void SetItemClickListenerr(RecyclerViewItemClick itemClickListener)
    {
        this.clickListenerr = itemClickListener;
    }

    public void SetPositionn(int position)
    {
        this.row_index = position;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        ConstraintLayout constraintLayout;
        TextView textView;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            constraintLayout = itemView.findViewById(R.id.constraintLayout);
            textView = itemView.findViewById(R.id.sizename);

            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            clickListenerr.onClickSize(view,getAdapterPosition());
        }
    }
}
