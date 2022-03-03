package com.rightbrain.officeproject.adapter.multiAdapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.rightbrain.officeproject.MyPreferance.MysharedPreferance;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.multirecycermodel.ModelMain;
import com.rightbrain.officeproject.ui.ProductsActivity;

import java.util.ArrayList;

import static com.rightbrain.officeproject.model.ModelProducts.GRID_VIEW_LAYOUT;
import static com.rightbrain.officeproject.model.ModelProducts.LARGE_VIEW_LAYOUT;
import static com.rightbrain.officeproject.model.ModelProducts.LIST_VIEW_LAYOUT;

public class DiscountMainAdapter extends RecyclerView.Adapter<DiscountMainAdapter.MyViewHolder> {
    Context context;
    ArrayList<ModelMain> allcategory;

    public DiscountMainAdapter(Context context,ArrayList<ModelMain> allcategory) {
        this.context = context;
        this.allcategory = allcategory;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_multi_catname, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.categoryname.setText("" + allcategory.get(position).getName());

        String id = ""+allcategory.get(position).getId();


        MultiAdapter multiAdapter;



        MysharedPreferance mysharedPreferance = MysharedPreferance.getPreferences(context);
        String viewType = mysharedPreferance.getViewType();


        if(viewType.equals("grid")){
            multiAdapter = new MultiAdapter(context,allcategory.get(position).getProducts(),GRID_VIEW_LAYOUT);
            holder.recyclerViewcatItem.setLayoutManager(new GridLayoutManager(context,2));

        }else if (viewType.equals("large")){
            multiAdapter = new MultiAdapter(context,allcategory.get(position).getProducts(),LARGE_VIEW_LAYOUT);
            holder.recyclerViewcatItem.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

        }
        else {
            multiAdapter = new MultiAdapter(context,allcategory.get(position).getProducts(),LIST_VIEW_LAYOUT);
            holder.recyclerViewcatItem.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.VERTICAL,false));

        }

     /*   multiAdapter = new MultiAdapter(context,allcategory.get(position).getProducts(),GRID_VIEW_LAYOUT);
        holder.recyclerViewcatItem.setLayoutManager(new GridLayoutManager(context,2));*/

        multiAdapter.notifyDataSetChanged();
        holder.recyclerViewcatItem.setAdapter(multiAdapter);



        holder.seeall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ProductsActivity.class);
                intent.putExtra("catid", ""+id);
                intent.putExtra("module", "discount");
                intent.putExtra("id", ""+position);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return allcategory.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView categoryname;
        ImageView seeall;
        RecyclerView recyclerViewcatItem;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryname = itemView.findViewById(R.id.catnameid);
            recyclerViewcatItem = itemView.findViewById(R.id.recyclermulti_id);
            seeall = itemView.findViewById(R.id.allcatproduct);
        }
    }
}
