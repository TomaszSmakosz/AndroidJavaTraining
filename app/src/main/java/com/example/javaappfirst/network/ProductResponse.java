package com.example.javaappfirst.network;

import com.example.javaappfirst.database.Product;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ProductResponse {
    @SerializedName("products")
    public ArrayList<Product> products;

    public ProductResponse(ArrayList<Product> products) {
        this.products = products;
    }
}
