package com.example.javaappfirst;

import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.javaappfirst.adapters.CartProductAdapter;
import com.example.javaappfirst.adapters.ShopProductAdapter;
import com.example.javaappfirst.database.Product;
import com.example.javaappfirst.databinding.CartFragmentBinding;

import java.util.List;

public class CartFragment extends Fragment {

    private CartViewModel mViewModel;

    public static CartFragment newInstance() {
        return new CartFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        mViewModel = new ViewModelProvider(this).get(CartViewModel.class);
        CartFragmentBinding binding = DataBindingUtil.inflate(getLayoutInflater() ,R.layout.cart_fragment, container, false);
        binding.setViewmodel(mViewModel);
        binding.setLifecycleOwner(this);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        final CartProductAdapter cartProductAdapter = new CartProductAdapter(mViewModel);
        RecyclerView recyclerView = view.findViewById(R.id.recyclerview);
        recyclerView.setAdapter(cartProductAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getContext(),
                DividerItemDecoration.VERTICAL));
        mViewModel.getAllCartProducts().observe(getViewLifecycleOwner(), new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                cartProductAdapter.setItems(products);
            }
        });
    }
}
