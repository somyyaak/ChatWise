package com.example.chatwise

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.chatwise.data.Product
import com.example.chatwise.data.ProductInterface
import com.example.chatwise.data.RetrofitHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProductsViewModel(application : Application ) : AndroidViewModel(application) {
    private val products: MutableLiveData<List<Product?>> = MutableLiveData<List<Product?>>()
    val productsLive: LiveData<List<Product?>> = products
    fun fetchData() {
        val productApi = RetrofitHelper.getInstance().create(ProductInterface::class.java)
        GlobalScope.launch(Dispatchers.IO) {
            val productapicalling = productApi.getProductDetails()
            if (productapicalling.body()?.products != null) {
                withContext(Dispatchers.Main) {
                    products.value = productapicalling.body()?.products
                }
            }
        }
    }
}

