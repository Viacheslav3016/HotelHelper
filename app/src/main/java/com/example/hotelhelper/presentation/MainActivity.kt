package com.example.hotelhelper.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.ViewModelProvider
import com.example.hotelhelper.R
import kotlin.math.log

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
         var count = 0
        viewModel.orderList.observe(this){
            Log.d("MainActivity", it.toString())
            if (count==0) {
                count++
                val item = it[0]
                viewModel.editStatusOrder(item)
                Log.d("MainActivity", it.toString())
            }
        }
    }
}