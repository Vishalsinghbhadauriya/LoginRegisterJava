package com.example.loginregisterjava.network;

import com.example.loginregisterjava.modal.Example;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("categories.php")
    Call<Example> getCategories();
}
