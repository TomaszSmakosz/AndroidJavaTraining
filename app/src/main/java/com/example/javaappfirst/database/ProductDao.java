package com.example.javaappfirst.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ProductDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(Product product);

    @Update
    void update(Product product);

    @Query("SELECT * from product_table")
    LiveData<List<Product>> getProduct();

    @Query("SELECT * from product_table")
    List<Product> getProducts();

    @Query("SELECT * from product_table where externalId = :ext")
    List<Product> getProductByExternalId(String ext);

    @Query("SELECT * from product_table where in_cart_amount > 0")
    LiveData<List<Product>> getCartProducts();
}
