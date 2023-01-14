package com.example.hotelhelper.domain

import com.example.hotelhelper.domain.repository.OrderListRepository

class AddShopOrderUseCase(private val repository: OrderListRepository) {
    fun addHotelOrder(shopItem: ShopItem){
        repository.addHotelOrder(shopItem)
    }
}