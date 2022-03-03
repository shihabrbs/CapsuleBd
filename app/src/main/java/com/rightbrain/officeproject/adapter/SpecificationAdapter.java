package com.rightbrain.officeproject.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.modelProductDetails.Specification;

import java.util.List;

public class SpecificationAdapter extends RecyclerView.Adapter<SpecificationAdapter.MyViewHolder> {

    Context context;
    List<Specification> specificationArrayList;


    public SpecificationAdapter(Context context, List<Specification> specificationArrayList) {
        this.context = context;
        this.specificationArrayList = specificationArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_specification, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(""+specificationArrayList.get(position).getLabel());
        holder.details.setText(""+specificationArrayList.get(position).getValue());

    }

    @Override
    public int getItemCount() {
        return specificationArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,details;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            name = itemView.findViewById(R.id.tv_name);
            details = itemView.findViewById(R.id.tv_details);
        }
    }
}
