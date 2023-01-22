package com.example.hotelhelper.presentation

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelhelper.R

class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tvName = view.findViewById<TextView>(R.id.TextVIewName)
    val tvSize = view.findViewById<TextView>(R.id.TextViewSize)
    val tvCount = view.findViewById<TextView>(R.id.TextViewCount)
}