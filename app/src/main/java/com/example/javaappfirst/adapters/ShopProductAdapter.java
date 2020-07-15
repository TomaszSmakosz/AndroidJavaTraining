package com.example.javaappfirst.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.javaappfirst.R;
import com.example.javaappfirst.ShopViewModel;
import com.example.javaappfirst.database.Product;

import java.util.List;

public class ShopProductAdapter extends RecyclerView.Adapter<ShopProductAdapter.ProductViewHolder> {

    private ShopViewModel shopViewModel;
    private List<Product> productList;

    public ShopProductAdapter(ShopViewModel shopViewModel) {
        this.shopViewModel = shopViewModel;
    }

    @NonNull
    @Override
    public ProductViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.shop_product_item,
                parent, false);
        return new ProductViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductViewHolder holder, int position) {
        final Product currentItem = productList.get(position);
        holder.tvProductName.setText(currentItem.getName());
        holder.tvProductAmount.setText(String.valueOf(currentItem.getInCartAmount()));
        holder.ibProductAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentItem.addProduct();
                shopViewModel.insert(currentItem);
            }
        });
        holder.ibProductRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(currentItem.removeProduct())
                    shopViewModel.insert(currentItem);
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
        ImageButton ibProductAdd;
        ImageButton ibProductRemove;

        public ProductViewHolder(@NonNull View itemView) {
            super(itemView);
            ivProduct = itemView.findViewById(R.id.iv_product);
            tvProductName = itemView.findViewById(R.id.tv_product_name);
            tvProductAmount = itemView.findViewById(R.id.tv_product_amount);
            ibProductAdd = itemView.findViewById(R.id.ib_product_add);
            ibProductRemove = itemView.findViewById(R.id.ib_product_remove);
        }
    }

    public void setItems(List<Product> productList){
        this.productList = productList;
        notifyDataSetChanged();
    }


}
