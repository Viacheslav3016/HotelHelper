package com.example.hotelhelper.presentation.Utils

import androidx.recyclerview.widget.DiffUtil
import com.example.hotelhelper.domain.HotelItem

class OrderItemDiff:DiffUtil.ItemCallback<HotelItem>() {
    override fun areItemsTheSame(oldItem: HotelItem, newItem: HotelItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: HotelItem, newItem: HotelItem): Boolean {
        return oldItem==newItem
    }
}