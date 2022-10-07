package com.saifsweelam.ecommercetask;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder> {
    private final ProductsResponse data;

    public ProductsAdapter(ProductsResponse data) {
        this.data = data;
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProductsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ProductsViewHolder holder, int position) {
        holder.setProductViewHolderData(data.getProducts().get(position));
    }

    @Override
    public int getItemCount() {
        return data.getProducts().size();
    }

    static class ProductsViewHolder extends RecyclerView.ViewHolder {
        private View view;
        private ImageView thumbnailView;
        private TextView nameView;
        private TextView descriptionView;
        private TextView priceView;

        public ProductsViewHolder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            thumbnailView = (ImageView) itemView.findViewById(R.id.thumbnailView);
            nameView = (TextView) itemView.findViewById(R.id.nameView);
            descriptionView = (TextView) itemView.findViewById(R.id.descriptionView);
            priceView = (TextView) itemView.findViewById(R.id.priceView);
        }

        public void setProductViewHolderData(Product product) {
            nameView.setText(product.getTitle());
            descriptionView.setText(product.getDescription());
            priceView.setText("$"+product.getPrice());
            Glide.with(view.getContext())
                    .load(product.getThumbnail())
                    .centerInside()
                    .placeholder(R.drawable.thumbnail)
                    .into(thumbnailView);
        }
    }
}
