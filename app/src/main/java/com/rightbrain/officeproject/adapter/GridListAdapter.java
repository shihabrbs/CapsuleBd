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
import com.rightbrain.officeproject.model.ModelProducts;

import java.util.ArrayList;
import java.util.List;

import static com.rightbrain.officeproject.model.ModelProducts.GRID_VIEW_LAYOUT;
import static com.rightbrain.officeproject.model.ModelProducts.LIST_VIEW_LAYOUT;

public class GridListAdapter extends RecyclerView.Adapter {
    Context context;
    private List<ModelProducts> modelProductsList;
    List<ModelProducts> products;
    int viewTypee;

    public GridListAdapter(Context context, List<ModelProducts> modelProductsList, int viewTypee) {
        this.context = context;
        this.modelProductsList = modelProductsList;
        this.viewTypee = viewTypee;
    }

    /*@Override
    public int getItemViewType(int position) {
        switch (viewTypee){
            case 0:
                return GRID_VIEW_LAYOUT;
            case 1:
                return LIST_VIEW_LAYOUT;
            default:
                return -1;
        }
    }*/

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {


        switch (viewTypee) {
            case GRID_VIEW_LAYOUT:
                View gridLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_products, parent, false);
                return new GridLayout(gridLayout);
            case LIST_VIEW_LAYOUT:
                View listLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_list, parent, false);
                return new ListLayout(listLayout);
            default:
                return null;
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        products = new ArrayList<>();
        /* products.addAll(modelProductsList.get(position).getProducts());*/

        //  Toast.makeText(context, ""+products.size(), Toast.LENGTH_SHORT).show();

        String img = modelProductsList.get(position).getImagePath();
        String price = modelProductsList.get(position).getPrice();
        String discoutnprice = ""+modelProductsList.get(position).getDiscountPrice();
        switch (viewTypee) {
            case GRID_VIEW_LAYOUT:

                String namegrid = modelProductsList.get(position).getName();

                if (discoutnprice.equals("null")){
                    ((GridLayout)holder).price.setVisibility(View.GONE);
                    ((GridLayout)holder).constraintLayout.setVisibility(View.GONE);
                }else {
                    ((GridLayout)holder).price.setVisibility(View.VISIBLE);
                    ((GridLayout)holder).constraintLayout.setVisibility(View.VISIBLE);
                }


                ((GridLayout) holder).setGridData(namegrid, img,discoutnprice,price);
                break;

            case LIST_VIEW_LAYOUT:
                String namelist = modelProductsList.get(position).getName();

                if (discoutnprice.equals("null")){
                    ((ListLayout)holder).price.setVisibility(View.GONE);
                    ((ListLayout)holder).constraintLayout.setVisibility(View.GONE);
                }else {
                    ((ListLayout)holder).price.setVisibility(View.VISIBLE);
                    ((ListLayout)holder).constraintLayout.setVisibility(View.VISIBLE);
                }


                ((ListLayout) holder).setListData(namelist, img,discoutnprice,price);
                break;
            default:
                return;
        }

    }

    @Override
    public int getItemCount() {
        return modelProductsList.size();
    }

    class GridLayout extends RecyclerView.ViewHolder {
        TextView name, price, discountprice;
        ImageView imageView;
        ConstraintLayout constraintLayout;

        public GridLayout(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView7);
            price = itemView.findViewById(R.id.textView6);
            discountprice = itemView.findViewById(R.id.textView8);
            imageView = itemView.findViewById(R.id.imageView7);
            constraintLayout = itemView.findViewById(R.id.discountconstlayout);
        }

        private void setGridData(String n, String img, String pricee, String discountpricee) {
            name.setText(n);
            price.setText(pricee);
            discountprice.setText(discountpricee);

            Glide
                    .with(context)
                    .load("http://" + img)
                    .into(imageView);
        }
    }

    class ListLayout extends RecyclerView.ViewHolder {
        TextView name, price, discountprice;
        ImageView imageView;
        ConstraintLayout constraintLayout;
        public ListLayout(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView7);
            imageView = itemView.findViewById(R.id.imageView7);
            price = itemView.findViewById(R.id.textView6);
            discountprice = itemView.findViewById(R.id.textView8);
            constraintLayout = itemView.findViewById(R.id.discountconstlayout);
        }

        private void setListData(String n, String img, String pricee, String discountpricee) {
            name.setText(n);
            price.setText(pricee);
            discountprice.setText(discountpricee);
            Glide
                    .with(context)
                    .load("http://" + img)
                    .into(imageView);
        }
    }
}
