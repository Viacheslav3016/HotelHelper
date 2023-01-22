package com.example.hotelhelper.domain.repository

import androidx.lifecycle.LiveData
import com.example.hotelhelper.domain.ShopItem

interface OrderListRepository {
    fun getHotelOrder(hotelOrderId:Int):ShopItem
    fun getListOrder():LiveData<List<ShopItem>>
    fun addHotelOrder(shopItem: ShopItem)
    fun deleteOrderItem(shopItem: ShopItem)
    fun editHotelOrder(shopItem: ShopItem)
}