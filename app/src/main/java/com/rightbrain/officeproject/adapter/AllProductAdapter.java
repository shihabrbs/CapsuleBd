package com.rightbrain.officeproject.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.rightbrain.officeproject.MyPreferance.MysharedPreferanceSetup;
import com.rightbrain.officeproject.R;
import com.rightbrain.officeproject.model.ModelProducts;
import com.rightbrain.officeproject.room.CartRepository;
import com.rightbrain.officeproject.ui.AllProductActivity;
import com.rightbrain.officeproject.ui.ProductDetailsActivity;

import java.util.ArrayList;

public class AllProductAdapter extends RecyclerView.Adapter<AllProductAdapter.MyViewHolder> {
    AllProductActivity context;
    ArrayList<ModelProducts> allProducts;



    public AllProductAdapter(AllProductActivity context, ArrayList<ModelProducts> allProducts) {
        this.context = context;
        this.allProducts = allProducts;


    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category_products, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        MysharedPreferanceSetup mysharedPreferanceSetup = MysharedPreferanceSetup.getPreferences(context);
        String uniqueCode = mysharedPreferanceSetup.getUniqueCode();
        String deliveryfee = mysharedPreferanceSetup.getShippingCharge();
        String currency = mysharedPreferanceSetup.getCurrency();

        holder.name.setText("" + allProducts.get(position).getName());
        holder.price.setText(currency+" " + allProducts.get(position).getPrice());
        String img = allProducts.get(position).getImagePath();
        Glide
                .with(context)
                .load("http://"+img)
                .into(holder.imageView);

        /* Glide.with(context).load(allcategory.get(1).getImagePath()).placeholder(R.drawable.healthyfood).dontAnimate().into(holder.imageView);
         */
        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = allProducts.get(position).getProductId();
                Intent intent = new Intent(context, ProductDetailsActivity.class);
                intent.putExtra("productid",""+id);
                context.startActivity(intent);
            }
        });

        holder.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final CartRepository repository = new CartRepository(context);

             /*   ModelCartRoom modelCartRoom=new ModelCartRoom();
                modelCartRoom.setP_name("" + allProducts.get(position).getName());
                modelCartRoom.setP_price("" + allProducts.get(position).getPrice());
                modelCartRoom.setUrl("http://"+img);
                modelCartRoom.setQuantity("1");

                repository.insertSingleData(new ModelCartRoom("" + allProducts.get(position).getName(),"" + allProducts.get(position).getPrice(),"1","http://"+img,"M"));
*/

                Context cont = null;

               /* repository.getSearchData(""+allProducts.get(position).getName()).observe(context, new Observer<ModelCartRoom>() {
                    @Override
                    public void onChanged(ModelCartRoom modelCartRoom) {
                        Toast.makeText(cont, ""+modelCartRoom.getP_name(), Toast.LENGTH_SHORT).show();
                    }
                });*/

                /*repository.FindAllData("shihab").observe((LifecycleOwner) context, new Observer<ModelCartRoom>() {
                    @Override
                    public void onChanged(ModelCartRoom modelCartRoom) {
                        Toast.makeText(context, ""+modelCartRoom.getP_name(), Toast.LENGTH_SHORT).show();
                    }
                });*/


            }
        });
    }

    @Override
    public int getItemCount() {
        return allProducts.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView name,price,previous_price;
        ImageView imageView;
        ConstraintLayout plus;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.textView7);
            imageView = itemView.findViewById(R.id.imageView7);
            price = itemView.findViewById(R.id.textView8);
            previous_price = itemView.findViewById(R.id.textView6);
            plus = itemView.findViewById(R.id.constraintLayout3);
        }
    }
}
