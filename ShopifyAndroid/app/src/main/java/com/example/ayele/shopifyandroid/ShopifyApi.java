package com.example.ayele.shopifyandroid;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ShopifyApi {

    String BASE_URL = "https://shopicruit.myshopify.com/admin/";

    @GET("custom_collections.json?page=1&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    Call<CustomCollection> getCollections();


    @GET("collects.json?&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    Call<CollectsIds> getCollectsIds(@Query("collection_id") String collectsId);

    @GET("products.json?&access_token=c32313df0d0ef512ca64d5b336a0d7c6")
    Call<Products> getProductsDetails(@Query("ids") String productIds);
}
