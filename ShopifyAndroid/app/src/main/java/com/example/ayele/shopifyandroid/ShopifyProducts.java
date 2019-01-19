package com.example.ayele.shopifyandroid;

public class ShopifyProducts {

    private String title;
    private Variants variants[];

    public ShopifyProducts(String title, Variants[] variants) {
        this.title = title;
        this.variants = variants;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Variants[] getVariants() {
        return variants;
    }

    public void setVariants(Variants[] variants) {
        this.variants = variants;
    }
}