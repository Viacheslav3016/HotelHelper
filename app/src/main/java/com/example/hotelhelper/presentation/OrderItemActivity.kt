package com.example.hotelhelper.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.hotelhelper.R

class OrderItemActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_item)
        val mode = intent.getStringExtra("extra_mood")
    }
}