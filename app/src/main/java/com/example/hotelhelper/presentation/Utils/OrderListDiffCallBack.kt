package com.example.hotelhelper.presentation.Utils

import android.telecom.Call
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.DiffUtil.Callback
import com.example.hotelhelper.domain.HotelItem

class OrderListDiffCallBack(
    val oldList:List<HotelItem>,
    val newList:List<HotelItem>):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldListItem = oldList[oldItemPosition]
        val newListItem = newList[newItemPosition]
        return oldListItem.id == newListItem.id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldListItem = oldList[oldItemPosition]
        val newListItem = newList[newItemPosition]
        return oldListItem == newListItem
    }
}