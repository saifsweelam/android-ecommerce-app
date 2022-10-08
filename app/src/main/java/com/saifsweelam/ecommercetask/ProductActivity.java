package com.saifsweelam.ecommercetask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.TextView;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProductActivity extends AppCompatActivity implements Callback<Product> {
    private TextView titleView;
    private TextView priceView;
    private TextView ratingView;
    private TextView descriptionView;

    private ViewPager2 imagesViewPager;
    private DotsIndicator imagesViewPagerIndicator;

    private ProductImagesAdapter imagesAdapter;

    private RetrofitClient retrofitClient;
    private ApiService apiService;

    private Product product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product);

        int productId = getIntent().getIntExtra("productId", 0);

        titleView = findViewById(R.id.titleView);
        priceView = findViewById(R.id.priceView);
        ratingView = findViewById(R.id.ratingView);
        descriptionView = findViewById(R.id.descriptionView);

        imagesViewPager = findViewById(R.id.imagesViewPager);
        imagesViewPagerIndicator = findViewById(R.id.imagesViewPagerIndicator);

        retrofitClient = RetrofitClient.getInstance();
        apiService = retrofitClient.getApiService();

        Call<Product> request = apiService.getProduct(productId);
        request.enqueue(this);
    }

    @Override
    public void onResponse(Call<Product> call, Response<Product> response) {
        if (response.isSuccessful()) {
            product = response.body();

            assert product != null;
            titleView.setText(product.getTitle());
            priceView.setText("$"+product.getPrice());
            ratingView.setText(String.valueOf(product.getRating()));
            descriptionView.setText(product.getDescription());

            imagesAdapter = new ProductImagesAdapter(product.getImages());
            imagesViewPager.setAdapter(imagesAdapter);
            imagesViewPagerIndicator.setViewPager2(imagesViewPager);
        }
    }

    @Override
    public void onFailure(Call<Product> call, Throwable t) {

    }
}