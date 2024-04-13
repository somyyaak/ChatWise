package com.example.chatwise

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chatwise.data.Product

class ViewProducts : AppCompatActivity() , ProductItemClciked {
    private lateinit var  viewModel: ProductsViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_products)
        val recyclerView : RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)

        val adapter = ProductListAdapter(arrayListOf() , this)
        recyclerView.adapter = adapter
        viewModel = ViewModelProvider(
            this,ViewModelProvider.AndroidViewModelFactory.getInstance(application)
        )[ProductsViewModel::class.java]
        viewModel.fetchData()
        viewModel.productsLive.observe(this,{ t ->
            if(t.isNotEmpty() && t != null){
                 adapter.update(t)
            }
        })
    }



    override fun onItemClicked(item: Product?) {
        Toast.makeText(this , "clicked item is $item", Toast.LENGTH_LONG).show()
    }
}