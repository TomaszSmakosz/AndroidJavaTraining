package com.example.javaappfirst.network;

import com.example.javaappfirst.database.Product;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("/test.json")
    public Call<ProductResponse> getCurrentProductsData();
}
