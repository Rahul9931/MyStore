package com.example.mystore.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mystore.adapter.ProductAdapter
import com.example.mystore.R
import com.example.mystore.api.ProductService
import com.example.mystore.api.RetrofitHelper
import com.example.mystore.databinding.FragmentProductListBinding
import com.example.mystore.model.ProductX
import com.example.mystore.repository.ProductRepository
import com.example.mystore.viewmodel.ProductViewModel
import com.example.mystore.viewmodel.ProductViewModelFactory
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ProductListFragment : Fragment() {
    private lateinit var binding: FragmentProductListBinding
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
        binding = FragmentProductListBinding.inflate(inflater,container,false)
        binding.progressbar.visibility = View.VISIBLE
        val productService = RetrofitHelper.getInstance().create(ProductService::class.java)
        val repository = ProductRepository(productService)
        val productViewModel = ViewModelProvider(this,ProductViewModelFactory(repository)).get(ProductViewModel::class.java)
        CoroutineScope(Dispatchers.Main).launch {
            Log.d("check2","${productViewModel.getProduct()}")
            Log.d("check3","${productViewModel.getProduct().get(0)}")
            setAdapter(productViewModel.getProduct())
        }
        return binding.root
    }

    private fun setAdapter(productsList: List<ProductX>) {
        binding.progressbar.visibility = View.GONE
        binding.rvProductsList.layoutManager = LinearLayoutManager(requireContext())
        val productAdapter = ProductAdapter(requireContext(),productsList,::onProductClick)
        binding.rvProductsList.adapter = productAdapter
    }

    private fun onProductClick(products: ProductX){
        Log.d("check4","$products")
        val bundle = Bundle()
        bundle.putString("products",Gson().toJson((products)))
        findNavController().navigate(R.id.action_productListFragment_to_productDetailsFragment,bundle)
    }
}