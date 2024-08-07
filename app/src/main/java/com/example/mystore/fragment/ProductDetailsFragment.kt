package com.example.mystore.fragment

import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.mystore.R
import com.example.mystore.databinding.FragmentProductDetailsBinding
import com.example.mystore.model.ProductX
import com.google.gson.Gson

class ProductDetailsFragment : Fragment() {
    private lateinit var binding: FragmentProductDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProductDetailsBinding.inflate(inflater,container,false)
        val jsonProduct = arguments?.getString("products")
        val products = Gson().fromJson(jsonProduct,ProductX::class.java)
        products?.let {
            binding.txtTitle.text = it.title
            binding.txtPrice.text = it.price.toString() + " Rs."
            binding.txtDescription.text = it.description
            Glide.with(requireContext()).load(Uri.parse(it.thumbnail)).into(binding.imgSetproduct)
        }
        return binding.root
    }
}