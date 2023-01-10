package com.example.hotelhelper.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelhelper.R
import com.example.hotelhelper.presentation.Adapter.OrderAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var orderAdapter: OrderAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        settingRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.orderList.observe(this) {
            orderAdapter.orderList = it
        }
    }

    private fun settingRecyclerView(){
        val recyclerViewOrder = findViewById<RecyclerView>(R.id.recyclerViewOrder)
        with(recyclerViewOrder){
            orderAdapter = OrderAdapter()
            adapter = orderAdapter
            recycledViewPool.setMaxRecycledViews(OrderAdapter.STATUS_ENABLED, OrderAdapter.MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(OrderAdapter.STATUS_DISABLED, OrderAdapter.MAX_POOL_SIZE)
        }

    }


}