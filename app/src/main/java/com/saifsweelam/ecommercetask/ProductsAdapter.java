package com.saifsweelam.ecommercetask;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

public class ProductsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private final ProductsResponse data;

    public ProductsAdapter(ProductsResponse data) {
        this.data = data;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        switch (viewType) {
            case 0: return new HeadingViewHolder(layoutInflater.inflate(R.layout.heading_item, parent, false));
            case 1: return new ProductsViewHolder(layoutInflater.inflate(R.layout.product_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ((HeadingViewHolder) holder).setHeading("Found " + (getItemCount()-1) + " Results");
        } else {
            ((ProductsViewHolder) holder).setProductViewHolderData(data.getProducts().get(position - 1));
        }
    }

    @Override
    public int getItemCount() {
        return data.getProducts().size() + 1;
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) return 0;
        return 1;
    }

    static class HeadingViewHolder extends RecyclerView.ViewHolder {
        private final TextView headingView;

        public HeadingViewHolder(@NonNull View itemView) {
            super(itemView);
            headingView = (TextView) itemView.findViewById(R.id.headingView);
        }

        public void setHeading(String text) {
            headingView.setText(text);
        }
    }

    class ProductsViewHolder extends RecyclerView.ViewHolder {
        private final View view;
        private final ImageView thumbnailView;
        private final TextView nameView;
        private final TextView descriptionView;
        private final TextView priceView;

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
                    .centerCrop()
                    .placeholder(R.drawable.thumbnail)
                    .into(thumbnailView);

            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(view.getContext(), ProductActivity.class);
                    intent.putExtra("productId", data.getProducts().get(getAdapterPosition()-1).getId());
                    view.getContext().startActivity(intent);
                }
            });
        }
    }
}
