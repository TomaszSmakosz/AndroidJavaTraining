package com.example.javaappfirst;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.javaappfirst.database.AppDatabase;
import com.example.javaappfirst.database.Product;
import com.example.javaappfirst.database.ProductDao;

import java.util.List;

public class ShopViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;
    private ProductDao productDao;
    private LiveData<List<Product>> allProducts;

    public ShopViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(application);
        productDao = appDatabase.productDao();
        allProducts = productDao.getProduct();
    }

    public void insert(Product product){
        new InsertAsyncTask(productDao).execute(product);
    }

    private class InsertAsyncTask extends AsyncTask<Product, Void, Void> {
        ProductDao mProductDao;

        public InsertAsyncTask(ProductDao mProductDao) {
            this.mProductDao = mProductDao;
        }

        @Override
        protected Void doInBackground(Product... products) {
            mProductDao.insert(products[0]);
            return null;
        }
    }

    public LiveData<List<Product>> getAllProducts() {
        return allProducts;
    }
}
