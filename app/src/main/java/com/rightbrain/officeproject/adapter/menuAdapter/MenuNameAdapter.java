package com.rightbrain.officeproject.adapter.menuAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.sidebar.ModelMenu;
import com.rightbrain.officeproject.ui.MenuDetailsActivity;

import java.util.ArrayList;

public class MenuNameAdapter extends RecyclerView.Adapter<MenuNameAdapter.MyViewHolder> {
    Context context;
    ArrayList<ModelMenu> modelMenuArrayList;

    public MenuNameAdapter(Context context, ArrayList<ModelMenu> modelMenuArrayList) {

        this.context = context;
        this.modelMenuArrayList = modelMenuArrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_page_menu, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.name.setText(""+modelMenuArrayList.get(position).getMenu());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, MenuDetailsActivity.class);
                intent.putExtra("id",""+modelMenuArrayList.get(position).getId());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return modelMenuArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.menuname);
        }
    }
}
