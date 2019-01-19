package com.example.ayele.shopifyandroid;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DataAdapter extends RecyclerView.Adapter<DataAdapter.ViewHolder>{

    private Context mContext;
    private ArrayList<ShopifyCollections> customShopifyCollections;
    private ArrayList<ShopifyCollectsIds> collectsIds;
    private ArrayList<String> productIds;

    public DataAdapter(ArrayList<ShopifyCollections> customShopifyCollections, Context mContext) {
        this.mContext = mContext;
        this.customShopifyCollections = customShopifyCollections;
    }

    @Override
    public DataAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DataAdapter.ViewHolder holder, final int position){
        holder.collectionName.setText(customShopifyCollections.get(position).getTitle());
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                navigateToNext(position);
            }
        });
    }

    private void navigateToNext(int position){
        loadCollectIdJSON(customShopifyCollections.get(position).getId(), position);
    }

    @Override
    public int getItemCount(){

        return customShopifyCollections.size();
    }

    private void loadCollectIdJSON(final String collectionId, final int position) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(ShopifyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ShopifyApi request = retrofit.create(ShopifyApi.class);

        Call<CollectsIds> call = request.getCollectsIds(collectionId);

        call.enqueue((new Callback<CollectsIds>() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onResponse(Call<CollectsIds> call, Response<CollectsIds> response) {
                CollectsIds collectsResponse = response.body();
                if (collectsResponse != null && collectsResponse.getCollects() != null) {
                    ShopifyCollectsIds[] sCollects = collectsResponse.getCollects();
                    collectsIds = new ArrayList<>(Arrays.asList(sCollects));
                    productIds = new ArrayList<>();
                    for (int i = 0; i < collectsIds.size(); i++) {
                        productIds.add(collectsIds.get(i).getProduct_id());
                    }
                    Intent intent = new Intent(mContext, ProductsActivity.class);
                    intent.putExtra("collection_id", collectionId);
                    intent.putExtra("collection_name", customShopifyCollections.get(position).getTitle());
                    intent.putExtra("image_src", customShopifyCollections.get(position).getImage().getSrc());
                    intent.putExtra("product_ids", String.join(",", productIds));

                    mContext.startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<CollectsIds> call, Throwable t) {
                Log.d("Error ", t.getMessage());
            }
        }));
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView collectionName;

        public ViewHolder(View itemView){
            super(itemView);
            collectionName = (TextView)itemView.findViewById(R.id.collectionName);
        }
    }
}
