package com.example.hotelhelper.domain.repository

import com.example.hotelhelper.domain.HotelItem

interface OrderListRepository {
    fun getHotelOrder(hotelOrderId:Int):HotelItem
    fun getListOrder():List<HotelItem>
    fun addHotelOrder(hotelItem: HotelItem)
    fun deleteOrderItem(hotelItem: HotelItem)
    fun editHotelOrder(hotelItem: HotelItem)
}