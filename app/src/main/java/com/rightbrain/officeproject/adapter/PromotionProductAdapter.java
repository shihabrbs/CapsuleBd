package com.rightbrain.officeproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.LifecycleOwner;
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

import java.util.ArrayList;
import java.util.List;

import static com.rightbrain.officeproject.model.ModelProducts.GRID_VIEW_LAYOUT;
import static com.rightbrain.officeproject.model.ModelProducts.LARGE_VIEW_LAYOUT;
import static com.rightbrain.officeproject.model.ModelProducts.LIST_VIEW_LAYOUT;

public class PromotionProductAdapter extends RecyclerView.Adapter {
    Context context;
    int viewTypee;
    ArrayList<ModelProducts> allProducts;
    public List<ModelCartRoom> cartList;
    int counter = 0;

    public PromotionProductAdapter(Context context, ArrayList<ModelProducts> allProducts,int viewTypee) {
        this.context = context;
        this.allProducts = allProducts;
        this.viewTypee = viewTypee;
        CartRepository repository = new CartRepository(context);

        repository.getAllData().observe((LifecycleOwner) context, new Observer<List<ModelCartRoom>>() {
            @Override
            public void onChanged(List<ModelCartRoom> carts) {
                cartList = carts;
            }
        });

    }

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
            case LARGE_VIEW_LAYOUT:
                View largeLayout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_large_product, parent, false);
                return new LargeLayout(largeLayout);
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(context);
        String uniqueCode = mysharedPreferanceSetup.getUniqueCode();
        String deliveryfee = mysharedPreferanceSetup.getShippingCharge();
        String currency = mysharedPreferanceSetup.getCurrency();

        CartRepository repository = new CartRepository(context);

        switch (viewTypee) {
            case GRID_VIEW_LAYOUT:

                repository.getAllData().observe((LifecycleOwner) context, new Observer<List<ModelCartRoom>>() {
                    @Override
                    public void onChanged(List<ModelCartRoom> cartList) {
                        for (ModelCartRoom cart : cartList) {
                            if (cart.getProductId().equals(String.valueOf(allProducts.get(position).getProductId()))) {
                                ((GridLayout)holder).quntity.setText(cart.getQuantity());
                                ((GridLayout)holder).quntity.setVisibility(View.VISIBLE);
                                ((GridLayout)holder).minusbtn.setVisibility(View.VISIBLE);
                            }
                        }


                    }
                });

                ((GridLayout)holder).name.setText(allProducts.get(position).getName());
                String disountpercentt = ""+allProducts.get(position).getDiscountAmount();
                String unite = ""+allProducts.get(position).getUnitName();
                String price = ""+allProducts.get(position).getPrice();
                String discountprice = ""+allProducts.get(position).getDiscountPrice();


                if (mysharedPreferanceSetup.getCartProcess().equals("inline")) {

                    ((GridLayout)holder).cart.setVisibility(View.VISIBLE);

                } else {

                    ((GridLayout)holder).cart.setVisibility(View.GONE);
                }

                if (unite.equals("null")){
                    ((GridLayout)holder).unitename.setVisibility(View.GONE);
                }else {
                    ((GridLayout)holder).unitename.setVisibility(View.VISIBLE);
                }

                if (disountpercentt.equals("null")){
                    ((GridLayout)holder).discountPercent.setVisibility(View.GONE);
                    ((GridLayout)holder).constraintLayoutdis.setVisibility(View.GONE);
                }else {
                    ((GridLayout)holder).discountPercent.setVisibility(View.VISIBLE);
                    ((GridLayout)holder).constraintLayoutdis.setVisibility(View.VISIBLE);
                }

                if (discountprice.equals("null")){
                    ((GridLayout)holder).normalprice.setVisibility(View.GONE);
                    //   holder.imageViewLine.setVisibility(View.GONE);
                    ((GridLayout)holder).offerprice.setText(currency+""+allProducts.get(position).getPrice());
                }else {
                    ((GridLayout)holder).normalprice.setVisibility(View.VISIBLE);
                    //holder.imageViewLine.setVisibility(View.VISIBLE);
                    ((GridLayout)holder).normalprice.setText(currency+""+allProducts.get(position).getPrice());
                    ((GridLayout)holder).offerprice.setText(currency+""+allProducts.get(position).getDiscountPrice());
                }

                ((GridLayout)holder).imageView.setOnClickListener(new View.OnClickListener() {
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

                ((GridLayout)holder).name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id = allProducts.get(position).getProductId();
                        int categoryid = allProducts.get(position).getCategoryId();
                        Intent intent = new Intent(context, ProductDetails.class);
                        intent.putExtra("productid",""+id);
                        intent.putExtra("catid",""+categoryid);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);
                    }
                });


                ((GridLayout)holder).discountPercent.setText(allProducts.get(position).getDiscountAmount()+"%");
                ((GridLayout)holder).unitename.setText("/"+allProducts.get(position).getUnitName());

                String img = allProducts.get(position).getImagePath();
                Glide
                        .with(context)
                        .load("http://"+img)
                        .into(((GridLayout)holder).imageView);


                ((GridLayout)holder).plusbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                       /* ((GridLayout)holder).quntity.setVisibility(View.VISIBLE);
                        int quantity = Integer.parseInt("" + ((GridLayout)holder).quntity.getText());
                        if (quantity > 0) {
                            int productid = allProducts.get(position).getProductId();
                            quantity++;

                            repository.updateRowData(quantity, productid);
                            ((GridLayout)holder).quntity.setText("" + quantity);

                        } else if (quantity == 0) {

                            // Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show();
                            String productid = "" + allProducts.get(position).getProductId();
                            repository.insertSingleData(new ModelCartRoom("" + allProducts.get(position).getName(), "" + allProducts.get(position).getPrice(), "1", "http://" + img, "M","red", "" + productid));
                            ((GridLayout)holder).quntity.setText("1");

                        }*/

                        int maxqnt =  allProducts.get(position).getMaxQuantity();
                        String qntapplicable =  ""+allProducts.get(position).getQuantityApplicable();

                        ((GridLayout) holder).quntity.setVisibility(View.VISIBLE);
                        ((GridLayout) holder).minusbtn.setVisibility(View.VISIBLE);
                        int quantity = Integer.parseInt("" + ((GridLayout) holder).quntity.getText());
                        if (quantity > 0) {

                            if (qntapplicable.equals("true")){
                                if (quantity==maxqnt){
                                    Toast.makeText(context, "You can't get more then "+maxqnt, Toast.LENGTH_SHORT).show();
                                }else {
                                    int productid = allProducts.get(position).getProductId();
                                    quantity++;

                                    repository.updateRowData(quantity, ""+productid);
                                    ((GridLayout) holder).quntity.setText("" + quantity);

                                }
                            }else {

                                int productid = allProducts.get(position).getProductId();
                                quantity++;

                                repository.updateRowData(quantity, ""+productid);
                                ((GridLayout) holder).quntity.setText("" + quantity);


                            }



                        } else if (quantity == 0) {

                            // Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show();
                            String productid = "" + allProducts.get(position).getProductId();
                            repository.insertSingleData(new ModelCartRoom("" + allProducts.get(position).getName(), "" + allProducts.get(position).getPrice(), "1", "http://" + img, "M", "red", "" + productid));
                            ((GridLayout) holder).quntity.setText("1");


                        }

                    }
                });

                ((GridLayout)holder).minusbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        CartRepository repository = new CartRepository(context);

                        counter = Integer.parseInt(((GridLayout)holder).quntity.getText().toString()) - 1;
                        ((GridLayout)holder).quntity.setText(String.valueOf(counter));
                        if (counter == 0) {
                            for (ModelCartRoom cart : cartList)
                                if (cart.getProductId().equals(String.valueOf(allProducts.get(position).getProductId()))) {
                                    repository.delete(cart);
                                    counter = 0;
                                    ((GridLayout)holder).quntity.setVisibility(View.GONE);
                                    ((GridLayout)holder).minusbtn.setVisibility(View.GONE);

                                }

                        } else {
                            for (ModelCartRoom contents : cartList)
                                if (contents.getProductId().equals(String.valueOf(allProducts.get(position).getProductId()))) {
                                    int productid = allProducts.get(position).getProductId();
                                    int qty = Integer.parseInt(((GridLayout)holder).quntity.getText().toString());
                                    ((GridLayout)holder).quntity.setText(""+qty);

                                    repository.updateRowData(qty, ""+productid);

                                }
                        }

                    }
                });

                break;

            case LIST_VIEW_LAYOUT:

                repository.getAllData().observe((LifecycleOwner) context, new Observer<List<ModelCartRoom>>() {
                    @Override
                    public void onChanged(List<ModelCartRoom> cartList) {
                        for (ModelCartRoom cart : cartList) {
                            if (cart.getProductId().equals(String.valueOf(allProducts.get(position).getProductId()))) {
                                ((ListLayout)holder).quntity.setText(cart.getQuantity());
                                ((ListLayout)holder).quntity.setVisibility(View.VISIBLE);
                                ((ListLayout)holder).minusbtn.setVisibility(View.VISIBLE);
                            }
                        }


                    }
                });

                ((ListLayout)holder).name.setText(allProducts.get(position).getName());
                String disountpercentt2 = ""+allProducts.get(position).getDiscountAmount();
                String unite2 = ""+allProducts.get(position).getUnitName();
                String price2 = ""+allProducts.get(position).getPrice();
                String discountprice2 = ""+allProducts.get(position).getDiscountPrice();


                if (mysharedPreferanceSetup.getCartProcess().equals("inline")) {

                    ((ListLayout)holder).cart.setVisibility(View.VISIBLE);

                } else {

                    ((ListLayout)holder).cart.setVisibility(View.GONE);
                }


                if (unite2.equals("null")){
                    ((ListLayout)holder).unitename.setVisibility(View.GONE);
                }else {
                    ((ListLayout)holder).unitename.setVisibility(View.VISIBLE);
                }

                if (disountpercentt2.equals("null")){
                    ((ListLayout)holder).discountPercent.setVisibility(View.GONE);
                    ((ListLayout)holder).constraintLayoutdis.setVisibility(View.GONE);
                }else {
                    ((ListLayout)holder).discountPercent.setVisibility(View.VISIBLE);
                    ((ListLayout)holder).constraintLayoutdis.setVisibility(View.VISIBLE);
                }

                if (discountprice2.equals("null")){
                    ((ListLayout)holder).normalprice.setVisibility(View.GONE);
                    //   holder.imageViewLine.setVisibility(View.GONE);
                    ((ListLayout)holder).offerprice.setText(currency+""+allProducts.get(position).getPrice());
                }else {
                    ((ListLayout)holder).normalprice.setVisibility(View.VISIBLE);
                    //holder.imageViewLine.setVisibility(View.VISIBLE);
                    ((ListLayout)holder).normalprice.setText(currency+""+allProducts.get(position).getPrice());
                    ((ListLayout)holder).offerprice.setText(currency+""+allProducts.get(position).getDiscountPrice());
                }

                ((ListLayout)holder).imageView.setOnClickListener(new View.OnClickListener() {
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

                ((ListLayout)holder).name.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id = allProducts.get(position).getProductId();
                        int categoryid = allProducts.get(position).getCategoryId();
                        Intent intent = new Intent(context, ProductDetails.class);
                        intent.putExtra("productid",""+id);
                        intent.putExtra("catid",""+categoryid);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);
                    }
                });


                ((ListLayout)holder).discountPercent.setText(allProducts.get(position).getDiscountAmount()+"%");
                ((ListLayout)holder).unitename.setText("/"+allProducts.get(position).getUnitName());

                String img2 = allProducts.get(position).getImagePath();
                Glide
                        .with(context)
                        .load("http://"+img2)
                        .into(((ListLayout)holder).imageView);


                ((ListLayout)holder).plusbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int maxqnt =  allProducts.get(position).getMaxQuantity();
                        String qntapplicable =  ""+allProducts.get(position).getQuantityApplicable();

                        ((ListLayout) holder).quntity.setVisibility(View.VISIBLE);
                        ((ListLayout) holder).minusbtn.setVisibility(View.VISIBLE);
                        int quantity = Integer.parseInt("" + ((ListLayout) holder).quntity.getText());
                        if (quantity > 0) {

                            if (qntapplicable.equals("true")){
                                if (quantity==maxqnt){
                                    Toast.makeText(context, "You can't get more then "+maxqnt, Toast.LENGTH_SHORT).show();
                                }else {
                                    int productid = allProducts.get(position).getProductId();
                                    quantity++;

                                    repository.updateRowData(quantity, ""+productid);
                                    ((ListLayout) holder).quntity.setText("" + quantity);

                                }
                            }else {

                                int productid = allProducts.get(position).getProductId();
                                quantity++;

                                repository.updateRowData(quantity, ""+productid);
                                ((ListLayout) holder).quntity.setText("" + quantity);


                            }



                        } else if (quantity == 0) {

                            // Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show();
                            String productid = "" + allProducts.get(position).getProductId();
                            repository.insertSingleData(new ModelCartRoom("" + allProducts.get(position).getName(), "" + allProducts.get(position).getPrice(), "1", "http://" + img2, "M", "red", "" + productid));
                            ((ListLayout) holder).quntity.setText("1");


                        }
                    }
                });

                ((ListLayout)holder).minusbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        CartRepository repository = new CartRepository(context);

                        counter = Integer.parseInt(((ListLayout)holder).quntity.getText().toString()) - 1;
                        ((ListLayout)holder).quntity.setText(String.valueOf(counter));
                        if (counter == 0) {
                            for (ModelCartRoom cart : cartList)
                                if (cart.getProductId().equals(String.valueOf(allProducts.get(position).getProductId()))) {
                                    repository.delete(cart);
                                    counter = 0;
                                    ((ListLayout)holder).quntity.setVisibility(View.GONE);
                                    ((ListLayout)holder).minusbtn.setVisibility(View.GONE);

                                }

                        } else {
                            for (ModelCartRoom contents : cartList)
                                if (contents.getProductId().equals(String.valueOf(allProducts.get(position).getProductId()))) {
                                    int productid = allProducts.get(position).getProductId();
                                    int qty = Integer.parseInt(((ListLayout)holder).quntity.getText().toString());
                                    ((ListLayout)holder).quntity.setText(""+qty);

                                    repository.updateRowData(qty, ""+productid);

                                }
                        }

                    }
                });

                break;

            case LARGE_VIEW_LAYOUT:



                ((LargeLayout)holder).productname.setText(allProducts.get(position).getName());
                ((LargeLayout)holder).unitname.setText(allProducts.get(position).getUnitName());
                ((LargeLayout)holder).name.setText(""+allProducts.get(position).getCategory());

                if (mysharedPreferanceSetup.getCartProcess().equals("inline")) {

                    ((LargeLayout)holder).cart.setVisibility(View.VISIBLE);

                } else {

                    ((LargeLayout)holder).cart.setVisibility(View.GONE);
                }

                String discountprice3 = ""+allProducts.get(position).getDiscountPrice();

                if (discountprice3.equals("null")){
                    ((LargeLayout)holder).normalprice.setVisibility(View.GONE);
                    //   holder.imageViewLine.setVisibility(View.GONE);
                    ((LargeLayout)holder).offerprice.setText(currency+""+allProducts.get(position).getPrice());
                }else {
                    ((LargeLayout)holder).normalprice.setVisibility(View.VISIBLE);
                    //holder.imageViewLine.setVisibility(View.VISIBLE);
                    ((LargeLayout)holder).normalprice.setText(currency+""+allProducts.get(position).getPrice());
                    ((LargeLayout)holder).offerprice.setText(currency+""+allProducts.get(position).getDiscountPrice());
                }

                ((LargeLayout)holder).imageView.setOnClickListener(new View.OnClickListener() {
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

                ((LargeLayout)holder).productname.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        int id = allProducts.get(position).getProductId();
                        int categoryid = allProducts.get(position).getCategoryId();
                        Intent intent = new Intent(context, ProductDetails.class);
                        intent.putExtra("productid",""+id);
                        intent.putExtra("catid",""+categoryid);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        context.startActivity(intent);
                    }
                });

                ((LargeLayout)holder).seeall.setOnClickListener(new View.OnClickListener() {
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



                String img3 = allProducts.get(position).getImagePath();
                Glide
                        .with(context)
                        .load("http://"+img3)
                        .into(((LargeLayout)holder).imageView);

                ((LargeLayout)holder).plusbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        int maxqnt =  allProducts.get(position).getMaxQuantity();
                        String qntapplicable =  ""+allProducts.get(position).getQuantityApplicable();

                        ((LargeLayout) holder).quntity.setVisibility(View.VISIBLE);
                        ((LargeLayout) holder).minusbtn.setVisibility(View.VISIBLE);
                        int quantity = Integer.parseInt("" + ((LargeLayout) holder).quntity.getText());
                        if (quantity > 0) {

                            if (qntapplicable.equals("true")){
                                if (quantity==maxqnt){
                                    Toast.makeText(context, "You can't get more then "+maxqnt, Toast.LENGTH_SHORT).show();
                                }else {
                                    int productid = allProducts.get(position).getProductId();
                                    quantity++;

                                    repository.updateRowData(quantity, ""+productid);
                                    ((LargeLayout) holder).quntity.setText("" + quantity);

                                }
                            }else {

                                int productid = allProducts.get(position).getProductId();
                                quantity++;

                                repository.updateRowData(quantity, ""+productid);
                                ((LargeLayout) holder).quntity.setText("" + quantity);


                            }



                        } else if (quantity == 0) {

                            // Toast.makeText(context, "Not Found", Toast.LENGTH_SHORT).show();
                            String productid = "" + allProducts.get(position).getProductId();
                            repository.insertSingleData(new ModelCartRoom("" + allProducts.get(position).getName(), "" + allProducts.get(position).getPrice(), "1", "http://" + img3, "M", "red", "" + productid));
                            ((LargeLayout) holder).quntity.setText("1");


                        }
                    }
                });

                ((LargeLayout)holder).minusbtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        CartRepository repository = new CartRepository(context);

                        counter = Integer.parseInt(((LargeLayout)holder).quntity.getText().toString()) - 1;
                        ((LargeLayout)holder).quntity.setText(String.valueOf(counter));
                        if (counter == 0) {
                            for (ModelCartRoom cart : cartList)
                                if (cart.getProductId().equals(String.valueOf(allProducts.get(position).getProductId()))) {
                                    repository.delete(cart);
                                    counter = 0;
                                    ((LargeLayout)holder).quntity.setVisibility(View.GONE);
                                    ((LargeLayout)holder).minusbtn.setVisibility(View.GONE);

                                }

                        } else {
                            for (ModelCartRoom contents : cartList)
                                if (contents.getProductId().equals(String.valueOf(allProducts.get(position).getProductId()))) {
                                    int productid = allProducts.get(position).getProductId();
                                    int qty = Integer.parseInt(((LargeLayout)holder).quntity.getText().toString());
                                    ((LargeLayout)holder).quntity.setText(""+qty);

                                    repository.updateRowData(qty, ""+productid);

                                }
                        }

                    }
                });


                break;


            default:
                return;
        }



    }

    @Override
    public int getItemCount() {
        return allProducts.size();
    }

  /*  public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,previous_price,quntity,normalprice,offerprice,discountPercent,imageViewLine,unitename;
        ImageView imageView,minusbtn,plusbtn;
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
            imageView = itemView.findViewById(R.id.imageView7);
            imageViewLine = itemView.findViewById(R.id.imageView28);
            unitename = itemView.findViewById(R.id.unitText);
            constraintLayoutdis = itemView.findViewById(R.id.discountconstlayout);
        }
    }*/

    class GridLayout extends RecyclerView.ViewHolder {
        TextView name, price, previous_price, quntity, normalprice, offerprice, discountPercent, imageViewLine, unitename;
        ImageView imageView, minusbtn,plusbtn;
        ConstraintLayout cart;
        ConstraintLayout constraintLayoutdis;

        public GridLayout(@NonNull View itemView) {
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

    class ListLayout extends RecyclerView.ViewHolder {
        TextView name, price, previous_price, quntity, normalprice, offerprice, discountPercent, imageViewLine, unitename;
        ImageView imageView, minusbtn,plusbtn;
        ConstraintLayout cart;
        ConstraintLayout constraintLayoutdis;
        ConstraintLayout constraintLayout;
        public ListLayout(@NonNull View itemView) {
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

    class LargeLayout extends RecyclerView.ViewHolder {
        TextView productname,name,seeall,price,quntity,unitname,normalprice, offerprice;
        ImageView imageView, minusbtn,plusbtn;
        ConstraintLayout cart;
        ConstraintLayout constraintLayoutdis;
        public LargeLayout(@NonNull View itemView) {
            super(itemView);
            productname = itemView.findViewById(R.id.textView67);
            name = itemView.findViewById(R.id.textView7);
            minusbtn = itemView.findViewById(R.id.imageView9);
            plusbtn = itemView.findViewById(R.id.imageView8);
            quntity = itemView.findViewById(R.id.textView5);
            unitname = itemView.findViewById(R.id.textView68);
            seeall = itemView.findViewById(R.id.textView75);
            cart = itemView.findViewById(R.id.constraintLayout3);
            normalprice = itemView.findViewById(R.id.textView6);
            offerprice = itemView.findViewById(R.id.textView8);
            imageView = itemView.findViewById(R.id.imageView7);
            constraintLayoutdis = itemView.findViewById(R.id.discountconstlayout);
        }


    }
    
}
