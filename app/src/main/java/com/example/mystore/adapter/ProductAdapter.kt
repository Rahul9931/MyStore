package com.example.mystore.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mystore.databinding.ProductCardBinding
import com.example.mystore.model.ProductX

class ProductAdapter(
    private val context: Context,
    private var productsList: List<ProductX>,
    private val onProductClick:(ProductX)->Unit
): RecyclerView.Adapter<ProductAdapter.ProductAdapterViewHolder>() {
    class ProductAdapterViewHolder(val binding:ProductCardBinding):RecyclerView.ViewHolder(binding.root) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductAdapterViewHolder {
        val binding = ProductCardBinding.inflate(LayoutInflater.from(context),parent,false)
        return ProductAdapterViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return productsList.size
    }

    override fun onBindViewHolder(holder: ProductAdapterViewHolder, position: Int) {
        holder.binding.txtSettitle.text = productsList[position].title
        holder.binding.txtSetdescription.text = productsList[position].description
        Glide.with(context).load(Uri.parse(productsList[position].thumbnail)).into(holder.binding.imgProduct)
        holder.binding.cardcontainer.setOnClickListener {
            onProductClick(productsList[position])
        }
    }
}