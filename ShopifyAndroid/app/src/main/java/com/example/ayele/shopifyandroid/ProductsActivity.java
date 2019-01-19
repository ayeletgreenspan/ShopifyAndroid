package com.example.ayele.shopifyandroid;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.Arrays;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProductsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ShopifyProducts> products;
    private ProductsAdapter adapter;
    private String productIdString;
    private String collectionId;
    private String collectionTitle;
    private String collectionImageUrl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_products);
        initViews();
    }

    private void initViews(){
        recyclerView = (RecyclerView)findViewById(R.id.product_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        getIncomingIntent();
        loadProductsJSON();
    }

    private void getIncomingIntent(){

        if(getIntent().hasExtra("collection_id") && getIntent().hasExtra("collection_name") && getIntent().hasExtra("image_src") && getIntent().hasExtra("product_ids")){
            collectionId = getIntent().getStringExtra("collection_id");
            collectionTitle = getIntent().getStringExtra("collection_name");
            collectionImageUrl = getIntent().getStringExtra("image_src");
            productIdString = getIntent().getStringExtra("product_ids");
        }
    }

    private void loadProductsJSON(){

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ShopifyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ShopifyApi request = retrofit.create(ShopifyApi.class);

        Call<Products> call = request.getProductsDetails(productIdString);

        call.enqueue(new Callback<Products>() {
            @Override
            public void onResponse(Call<Products> call, Response<Products> response) {
                Products productsResponse = response.body();
                if (productsResponse != null && productsResponse.getProducts() != null) {
                    ShopifyProducts[] sProducts = productsResponse.getProducts();
                    products = new ArrayList<>(Arrays.asList(sProducts));
                    adapter = new ProductsAdapter(products, collectionId, collectionTitle, collectionImageUrl);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<Products> call, Throwable t) {
                Log.d("Error ", t.getMessage());
            }
        });
    }
}
