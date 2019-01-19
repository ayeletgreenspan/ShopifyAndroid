package com.example.ayele.shopifyandroid;

public class Variants {
    private int inventory_quantity;

    public Variants(int inventory_quantity) {
        this.inventory_quantity = inventory_quantity;
    }

    public int getInventory_quantity() {
        return inventory_quantity;
    }

    public void setInventory_quantity(int inventory_quantity) {
        this.inventory_quantity = inventory_quantity;
    }
}
