package com.rightbrain.officeproject.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.ModelCartRoom;
import com.rightbrain.officeproject.model.ModelProducts;
import com.rightbrain.officeproject.room.CartRepository;
import com.rightbrain.officeproject.ui.ProductDetails;
import com.rightbrain.officeproject.ui.ProductDetailsActivity;
import com.rightbrain.officeproject.ui.ProductsActivity;

import java.util.ArrayList;
import java.util.List;

public class ProductsAdapterList extends RecyclerView.Adapter<ProductsAdapterList.MyViewHolder> {
    ProductsActivity context;

    ArrayList<ModelProducts> allProducts;

    public List<ModelCartRoom> cartList;
    int counter = 0;



    public ProductsAdapterList(ProductsActivity context, ArrayList<ModelProducts> allProducts) {
        this.context = context;
        this.allProducts = allProducts;


        CartRepository repository = new CartRepository(context);

        repository.getAllData().observe(context, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> carts) {
                cartList = carts;
            }
        });

    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_product_list, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(context);
        String uniqueCode = mysharedPreferanceSetup.getUniqueCode();
        String deliveryfee = mysharedPreferanceSetup.getShippingCharge();
        String currency = mysharedPreferanceSetup.getCurrency();

        holder.name.setText(allProducts.get(position).getName());
        String disountpercentt = ""+allProducts.get(position).getDiscountAmount();
        String unite = ""+allProducts.get(position).getUnitName();
        String price = ""+allProducts.get(position).getPrice();
        String discountprice = ""+allProducts.get(position).getDiscountPrice();


        CartRepository repository = new CartRepository(context);

        repository.getAllData().observe(context, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> cartList) {
                for (ModelCartRoom cart : cartList) {
                    if (cart.getProductId().equals(String.valueOf(allProducts.get(position).getProductId()))) {
                        holder.quntity.setText(cart.getQuantity());
                        holder.quntity.setVisibility(View.VISIBLE);
                        holder.minusbtn.setVisibility(View.VISIBLE);
                    }
                }


            }
        });

        if (mysharedPreferanceSetup.getCartProcess().equals("inline")) {

            holder.cart.setVisibility(View.VISIBLE);

        } else {

            holder.cart.setVisibility(View.GONE);
        }


        if (unite.equals("null")){
            holder.unitename.setVisibility(View.GONE);
        }else {
            holder.unitename.setVisibility(View.VISIBLE);
        }

        if (disountpercentt.equals("null")){
            holder.discountPercent.setVisibility(View.GONE);
            holder.constraintLayoutdis.setVisibility(View.GONE);
        }else {
            holder.discountPercent.setVisibility(View.VISIBLE);
            holder.constraintLayoutdis.setVisibility(View.VISIBLE);
        }

        if (discountprice.equals("null")){
            holder.normalprice.setVisibility(View.GONE);
            holder.imageViewLine.setVisibility(View.GONE);
            holder.offerprice.setText(currency+""+allProducts.get(position).getPrice());
        }else {
            holder.normalprice.setVisibility(View.VISIBLE);
            holder.imageViewLine.setVisibility(View.VISIBLE);
            holder.normalprice.setText(currency+""+allProducts.get(position).getPrice());
            holder.offerprice.setText(currency+""+allProducts.get(position).getDiscountPrice());
        }


        holder.discountPercent.setText(allProducts.get(position).getDiscountAmount()+"%");
        holder.unitename.setText("/"+allProducts.get(position).getUnitName());

        String img = allProducts.get(position).getImagePath();
        if (allProducts.get(position).getImagePath().equals("")){
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

        /* Glide.with(context).load(allcategory.get(1).getImagePath()).placeholder(R.drawable.healthyfood).dontAnimate().into(holder.imageView);
         */
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = allProducts.get(position).getProductId();
                int categoryid = allProducts.get(position).getCategoryId();
                Intent intent = new Intent(context, ProductDetails.class);
                intent.putExtra("productid",""+id);
                intent.putExtra("catid",""+categoryid);
                context.startActivity(intent);
            }
        });


        holder.name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = allProducts.get(position).getProductId();
                int categoryid = allProducts.get(position).getCategoryId();
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("productid",""+id);
                intent.putExtra("catid",""+categoryid);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                context.startActivity(intent);
            }
        });


        holder.plusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                holder.quntity.setVisibility(View.VISIBLE);
                int quantity = Integer.parseInt("" + holder.quntity.getText());
                if (quantity > 0) {
                    int productid = allProducts.get(position).getProductId();
                    quantity++;

                    repository.updateRowData(quantity, ""+productid);
                    holder.quntity.setText("" + quantity);

                } else if (quantity == 0) {

                    // Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show();
                    String productid = "" + allProducts.get(position).getProductId();
                    repository.insertSingleData(new ModelCartRoom("" + allProducts.get(position).getName(), "" + allProducts.get(position).getPrice(), "1", "http://" + img, "M","red", "" + productid));
                    holder.quntity.setText("1");

                }
            }
        });

        holder.minusbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                CartRepository repository = new CartRepository(context);

                counter = Integer.parseInt(holder.quntity.getText().toString()) - 1;
                holder.quntity.setText(String.valueOf(counter));
                if (counter == 0) {
                    for (ModelCartRoom cart : cartList)
                        if (cart.getProductId().equals(String.valueOf(allProducts.get(position).getProductId()))) {
                            repository.delete(cart);
                            counter = 0;
                            holder.quntity.setVisibility(View.GONE);
                            holder.minusbtn.setVisibility(View.GONE);

                        }

                } else {
                    for (ModelCartRoom contents : cartList)
                        if (contents.getProductId().equals(String.valueOf(allProducts.get(position).getProductId()))) {
                            int productid = allProducts.get(position).getProductId();
                            int qty = Integer.parseInt(holder.quntity.getText().toString());
                            holder.quntity.setText(""+qty);

                            repository.updateRowData(qty, ""+productid);

                        }
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return allProducts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name, price, previous_price, quntity, normalprice, offerprice, discountPercent, imageViewLine, unitename;
        ImageView imageView, minusbtn,plusbtn;
        ConstraintLayout cart;
        ConstraintLayout constraintLayoutdis;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView7);
            imageView = itemView.findViewById(R.id.imageView7);
            minusbtn = itemView.findViewById(R.id.imageView9);
            plusbtn = itemView.findViewById(R.id.imageView8);
            quntity = itemView.findViewById(R.id.textView5);
            price = itemView.findViewById(R.id.textView8);
            previous_price = itemView.findViewById(R.id.textView6);
            cart = itemView.findViewById(R.id.constraintLayout3);
            normalprice = itemView.findViewById(R.id.textView6);
            discountPercent = itemView.findViewById(R.id.percentid);
            offerprice = itemView.findViewById(R.id.textView8);
            imageViewLine = itemView.findViewById(R.id.imageView28);
            unitename = itemView.findViewById(R.id.unitText);
            constraintLayoutdis = itemView.findViewById(R.id.discountconstlayout);
        }
    }
}
