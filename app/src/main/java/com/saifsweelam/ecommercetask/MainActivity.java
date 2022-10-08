package com.saifsweelam.ecommercetask;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.os.Bundle;
import android.widget.SearchView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements Callback<ProductsResponse>, SearchView.OnQueryTextListener {
    private RetrofitClient retrofitClient;
    private ApiService apiService;
    private ProductsResponse data;
    private SearchView searchField;
    private RecyclerView productsView;
    private ProductsAdapter productsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        searchField = findViewById(R.id.searchField);
        productsView = findViewById(R.id.productsView);

        retrofitClient = RetrofitClient.getInstance();
        apiService = retrofitClient.getApiService();

        Call<ProductsResponse> request = apiService.getProducts();
        request.enqueue(this);

        searchField.setOnQueryTextListener(this);
    }

    @Override
    public void onResponse(Call<ProductsResponse> call, Response<ProductsResponse> response) {
        if (response.isSuccessful()) {
            data = response.body();
            productsAdapter = new ProductsAdapter(data);
            productsView.setAdapter(productsAdapter);
            productsView.setLayoutManager(new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL));
        }
    }

    @Override
    public void onFailure(Call<ProductsResponse> call, Throwable t) {

    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        Call<ProductsResponse> request = apiService.searchProducts(newText);
        request.enqueue(this);
        return false;
    }
}