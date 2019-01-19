package com.example.ayele.shopifyandroid;

public class ShopifyCollectsIds {

    private String collection_id;
    private String product_id;

    public ShopifyCollectsIds(String collection_id, String product_id) {
        this.collection_id = collection_id;
        this.product_id = product_id;
    }

    public String getCollection_id() {
        return collection_id;
    }

    public void setCollection_id(String collection_id) {
        this.collection_id = collection_id;
    }

    public String getProduct_id() {
        return product_id;
    }

    public void setProduct_id(String product_id) {
        this.product_id = product_id;
    }
}
