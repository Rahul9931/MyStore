package com.example.mystore.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mystore.model.ProductX
import com.example.mystore.repository.ProductRepository
import kotlinx.coroutines.launch

class ProductViewModel(private val repository: ProductRepository):ViewModel() {
    var data:List<ProductX> = listOf()
    suspend fun getProduct(): List<ProductX> {
        val job = viewModelScope.launch{
            data = repository.getproduct()
        }
        job.join()
        return data

    }
}