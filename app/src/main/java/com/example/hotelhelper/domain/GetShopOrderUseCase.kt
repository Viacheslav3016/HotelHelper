package com.example.hotelhelper.domain

import com.example.hotelhelper.domain.repository.OrderListRepository

class GetShopOrderUseCase(private val repository: OrderListRepository) {
    fun getHotelOrder(hotelOrderId:Int):ShopItem{
    return repository.getHotelOrder(hotelOrderId)
    }
}