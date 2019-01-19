package com.example.ayele.shopifyandroid;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<ShopifyCollections> collections;
    private DataAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
    }

    private void initViews() {
        recyclerView = (RecyclerView)findViewById(R.id.card_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        loadCustomCollectionsJSON();
    }

    private void loadCustomCollectionsJSON(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ShopifyApi.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ShopifyApi request = retrofit.create(ShopifyApi.class);

        Call<CustomCollection> call = request.getCollections();

        call.enqueue(new Callback<CustomCollection>() {
            @Override
            public void onResponse(Call<CustomCollection> call, Response<CustomCollection> response) {

                CustomCollection shopifyCollection = response.body();
                if (shopifyCollection != null && shopifyCollection.getCustomShopifyCollections() != null) {
                    ShopifyCollections[] sCollections = shopifyCollection.getCustomShopifyCollections();
                    collections = new ArrayList<>(Arrays.asList(sCollections));
                    adapter = new DataAdapter(collections, MainActivity.this);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<CustomCollection> call, Throwable t) {
                Log.d("Error ", t.getMessage());
            }
        });
    }
}
