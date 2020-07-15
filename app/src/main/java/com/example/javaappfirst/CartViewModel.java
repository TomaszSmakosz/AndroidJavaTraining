package com.example.javaappfirst;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.javaappfirst.database.AppDatabase;
import com.example.javaappfirst.database.Product;
import com.example.javaappfirst.database.ProductDao;

import java.util.List;

public class CartViewModel extends AndroidViewModel {
    private AppDatabase appDatabase;
    private ProductDao productDao;
    private LiveData<List<Product>> allCartProducts;
    private MutableLiveData<String> _totalPrice = new MutableLiveData<String>();
    LiveData<String> totalPrice;

    public CartViewModel(@NonNull Application application) {
        super(application);
        appDatabase = AppDatabase.getInstance(application);
        productDao = appDatabase.productDao();
        allCartProducts = productDao.getCartProducts();
    }

    public void insert(Product product){
        new CartViewModel.InsertAsyncTask(productDao).execute(product);
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

    public LiveData<List<Product>> getAllCartProducts() {
        return allCartProducts;
    }

    public LiveData<String> getTotalPrice() {
        return _totalPrice;
    }

    public void calculateTotalPrice(){
        if (allCartProducts!=null) {
            float total = 0f;
            for (Product product : allCartProducts.getValue()) {
                total += product.getPrice() * product.getInCartAmount();
            }
            _totalPrice.postValue((String.format("%.2f", total)).replace(".", ","));
        }
    }
}
