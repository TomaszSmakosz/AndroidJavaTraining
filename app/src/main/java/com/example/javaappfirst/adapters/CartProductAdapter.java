package com.example.javaappfirst.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaappfirst.CartViewModel;
import com.example.javaappfirst.R;
import com.example.javaappfirst.ShopViewModel;
import com.example.javaappfirst.database.Product;

import java.util.List;

public class CartProductAdapter extends RecyclerView.Adapter<CartProductAdapter.ProductViewHolder> {

    private CartViewModel cartViewModel;
    private List<Product> productList;

    public CartProductAdapter(CartViewModel cartViewModel) {
        this.cartViewModel = cartViewModel;
    }

    @NonNull
    @Override
    public CartProductAdapter.ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_product_item,
                parent, false);
        return new CartProductAdapter.ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CartProductAdapter.ProductViewHolder holder, int position) {
        final Product currentItem = productList.get(position);
        holder.tvProductName.setText(currentItem.getName());
        holder.tvProductAmount.setText(String.valueOf(currentItem.getInCartAmount()));
        holder.tvProductPrice.setText(String.valueOf(currentItem.getInCartAmount() * currentItem.getPrice()));
        holder.ibProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentItem.addProduct();
                cartViewModel.insert(currentItem);
            }
        });
        holder.ibProductRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentItem.removeProduct())
                    cartViewModel.insert(currentItem);
            }
        });
    }

    @Override
    public int getItemCount() {
        return productList == null ? 0 : productList.size();
    }

    public class ProductViewHolder extends RecyclerView.ViewHolder {
        ImageView ivProduct;
        TextView tvProductName;
        TextView tvProductAmount;
        TextView tvProductPrice;
        ImageButton ibProductAdd;
        ImageButton ibProductRemove;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.iv_product);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvProductAmount = itemView.findViewById(R.id.tv_product_amount);
            tvProductPrice = itemView.findViewById(R.id.tv_product_price);
            ibProductAdd = itemView.findViewById(R.id.ib_product_add);
            ibProductRemove = itemView.findViewById(R.id.ib_product_remove);
        }
    }

    public void setItems(List<Product> productList){
        this.productList = productList;
        cartViewModel.calculateTotalPrice();
        notifyDataSetChanged();
    }

}
