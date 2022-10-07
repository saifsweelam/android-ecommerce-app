package com.saifsweelam.ecommercetask;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiService {
    @GET("products")
    Call<ProductsResponse> getProducts();

    @GET("products/{id}")
    Call<Product> getProduct(@Path("id") int productId);

    @GET("products/search")
    Call<ProductsResponse> searchProducts(@Query("q") String query);
}
