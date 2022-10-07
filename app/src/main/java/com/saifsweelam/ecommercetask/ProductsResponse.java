package com.saifsweelam.ecommercetask;

import com.google.gson.annotations.Expose;

import java.util.List;

public class ProductsResponse {
    @Expose
    private List<Product> products = null;
    @Expose
    private Integer total;
    @Expose
    private Integer skip;
    @Expose
    private Integer limit;

    public ProductsResponse() {}

    public ProductsResponse(List<Product> products, Integer total, Integer skip, Integer limit) {
        super();
        this.products = products;
        this.total = total;
        this.skip = skip;
        this.limit = limit;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getSkip() {
        return skip;
    }

    public void setSkip(Integer skip) {
        this.skip = skip;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }
}
