package com.saifsweelam.ecommercetask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class ProductImagesAdapter extends RecyclerView.Adapter<ProductImagesAdapter.ProductImageViewHolder> {
    List<String> imageUrls;

    public ProductImagesAdapter(List<String> imageUrls) {
        this.imageUrls = imageUrls;
    }

    @NonNull
    @Override
    public ProductImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductImageViewHolder(LayoutInflater.from(parent.getContext()).inflate(
                R.layout.product_image,
                parent,
                false
        ));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductImageViewHolder holder, int position) {
        holder.setImageUrl(imageUrls.get(position));
    }

    @Override
    public int getItemCount() {
        return imageUrls.size();
    }

    public static class ProductImageViewHolder extends RecyclerView.ViewHolder {
        private ImageView imageView;

        public ProductImageViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.productImageView);
        }

        public void setImageUrl(String url) {
            Glide.with(imageView.getContext())
                    .load(url)
                    .centerCrop()
                    .into(imageView);
        }
    }
}
