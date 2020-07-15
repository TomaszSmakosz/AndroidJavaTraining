package com.example.javaappfirst.database;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "product_table")
public class Product {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "userid")
    private int id;

    @ColumnInfo(name = "externalId")
    @SerializedName("externalId")
    private String externalId;

    @ColumnInfo(name = "name")
    @SerializedName("name")
    private String name;

    @ColumnInfo(name = "price")
    @SerializedName("price")
    private float price;

    @ColumnInfo(name = "description")
    @SerializedName("description")
    private String description;

    @ColumnInfo(name = "in_cart_amount")
    @SerializedName("in_cart_amount")
    private int inCartAmount;

    public Product(int id, String externalId, String name, float price, String description, int inCartAmount) {
        this.id = id;
        this.externalId = externalId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.inCartAmount = inCartAmount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getExternalId() {
        return externalId;
    }

    public void setExternalId(String externalId) {
        this.externalId = externalId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getInCartAmount() {
        return inCartAmount;
    }

    public void setInCartAmount(int inCartAmount) {
        this.inCartAmount = inCartAmount;
    }

    public void addProduct(){
        inCartAmount+=1;
    }

    public boolean removeProduct(){
        if(inCartAmount > 0){
            inCartAmount-=1;
            return true;
        }
        return false;
    }
}