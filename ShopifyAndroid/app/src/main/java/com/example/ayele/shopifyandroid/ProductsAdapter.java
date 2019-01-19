package com.example.ayele.shopifyandroid;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;


public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder>{
    private ArrayList<ShopifyProducts> products;
    private String collectionId;
    private String collectionTitle;
    private String collectionImageUrl;


    public ProductsAdapter(ArrayList<ShopifyProducts> products, String collectionId, String collectionTitle, String collectionImageUrl){
        this.products = products;
        this.collectionId = collectionId;
        this.collectionTitle = collectionTitle;
        this.collectionImageUrl = collectionImageUrl;
    }

    @Override
    public ProductsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_row, parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ProductsAdapter.ViewHolder holder, final int position){

        holder.productName.setText(products.get(position).getTitle());
        holder.collectionName.setText(collectionTitle);


        Picasso.get().load(collectionImageUrl).into(holder.collectionImage);

        int inventory = 0;
        for(int i = 0; i < products.get(position).getVariants().length; i++){
            inventory += products.get(position).getVariants()[i].getInventory_quantity();
        }
        holder.inventory.setText("Total Inventory : "+inventory);

    }

    @Override
    public int getItemCount(){
        return products.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView collectionImage;
        private TextView productName;
        private TextView collectionName;
        private TextView inventory;


        public ViewHolder(View itemView){
            super(itemView);

            collectionImage = (ImageView)itemView.findViewById(R.id.collectionImage);
            productName = (TextView)itemView.findViewById(R.id.productName);
            collectionName = (TextView)itemView.findViewById(R.id.collectionName);
            inventory = (TextView)itemView.findViewById(R.id.inventory);

        }
    }
}
