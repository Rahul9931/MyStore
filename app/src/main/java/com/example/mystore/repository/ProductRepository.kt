package com.example.mystore.repository

import android.util.Log
import com.example.mystore.api.ProductService
import com.example.mystore.model.ProductX

class ProductRepository(private val productService: ProductService) {
    suspend fun getproduct():List<ProductX>{
        val result = productService.getProduct()
        Log.d("check1","${result}")
        return result.body()!!.products
    }
}