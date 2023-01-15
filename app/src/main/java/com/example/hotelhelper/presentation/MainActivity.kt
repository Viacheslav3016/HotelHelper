package com.example.hotelhelper.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelhelper.R
import com.example.hotelhelper.domain.ShopItem
import com.example.hotelhelper.presentation.Adapter.OrderAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: MainViewModel
    private lateinit var orderAdapter: OrderAdapter
    private var TEG_MAIN_ACTIVITY: String = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        settingRecyclerView()
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.orderList.observe(this) {
            orderAdapter.submitList(it)
        }
        val button = findViewById<FloatingActionButton>(R.id.floatingActionButton)
        button.setOnClickListener {
            val intent = OrderItemActivity.Companion.newIntentAdd(this)
            startActivity(intent)
        }
    }

    private fun settingRecyclerView() {
        val recyclerViewOrder = findViewById<RecyclerView>(R.id.recyclerViewOrder)
        with(recyclerViewOrder) {
            orderAdapter = OrderAdapter()
            adapter = orderAdapter
            recycledViewPool.setMaxRecycledViews(
                OrderAdapter.STATUS_ENABLED,
                OrderAdapter.MAX_POOL_SIZE
            )
            recycledViewPool.setMaxRecycledViews(
                OrderAdapter.STATUS_DISABLED,
                OrderAdapter.MAX_POOL_SIZE
            )
        }

        setupOnLongClickListener()
        setupOnItemClickListener()
        setupOnSwipe(recyclerViewOrder)
    }

    private fun setupOnSwipe(recyclerViewOrder: RecyclerView?) {
        val myCallBack = object :
            ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.LEFT
                        or ItemTouchHelper.RIGHT
            ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = orderAdapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteOrder(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(myCallBack)
        itemTouchHelper.attachToRecyclerView(recyclerViewOrder)
    }

    private fun setupOnItemClickListener() {
        orderAdapter.onHotelItemClickListener = {
            Log.d(TEG_MAIN_ACTIVITY, it.toString())
            val intent = OrderItemActivity.newIntentEdit(this, it.id)
            startActivity(intent)
        }
    }

    private fun setupOnLongClickListener() {
        orderAdapter.onHotelItemLongClickListener =
            object : OrderAdapter.OnHotelItemLongClickListener {
                override fun onHotelItemLongClick(shopItem: ShopItem) {
                    viewModel.editStatusOrder(shopItem)
                }
            }
    }
}
