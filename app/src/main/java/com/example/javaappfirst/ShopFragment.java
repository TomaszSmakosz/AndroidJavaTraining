package com.example.javaappfirst;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.javaappfirst.adapters.ShopProductAdapter;
import com.example.javaappfirst.database.Product;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class ShopFragment extends Fragment implements View.OnClickListener {

    private ShopViewModel mViewModel;
    private NavController navController;
    private FloatingActionButton fabCart, fabSetDb;

    public static ShopFragment newInstance() {
        return new ShopFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.shop_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ShopViewModel.class);
        navController = Navigation.findNavController(view);
        fabCart = view.findViewById(R.id.fab_cart);
        fabCart.setOnClickListener(this);
        fabSetDb = view.findViewById(R.id.fab_set_db);
        fabSetDb.setOnClickListener(this);
        final ShopProductAdapter shopProductAdapter = new ShopProductAdapter(mViewModel);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(shopProductAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        mViewModel.getAllProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                shopProductAdapter.setItems(products);
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.fab_cart:
                navController.navigate(R.id.action_shopFragment_to_cartFragment);
                break;
            case R.id.fab_set_db:
                mViewModel.insert(new Product(1,"2","Mysza", 22.20f, "Kox mysz", 1));
                break;
        }
    }
}
