package com.example.mystore.api

import com.example.mystore.model.Product
import retrofit2.Response
import retrofit2.http.GET

interface ProductService {
    @GET("products")
    suspend fun getProduct():Response<Product>

}