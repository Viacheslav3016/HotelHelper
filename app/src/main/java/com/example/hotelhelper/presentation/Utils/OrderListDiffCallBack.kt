package com.example.hotelhelper.presentation.Utils

import androidx.recyclerview.widget.DiffUtil
import com.example.hotelhelper.domain.ShopItem

class OrderListDiffCallBack(
    val oldList:List<ShopItem>,
    val newList:List<ShopItem>):DiffUtil.Callback() {
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