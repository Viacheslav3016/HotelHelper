package com.example.hotelhelper.domain

import com.example.hotelhelper.domain.repository.OrderListRepository

class EditShopOrderUseCase(private val repository: OrderListRepository) {
    fun editHotelOrder(shopItem: ShopItem){
        repository.editHotelOrder(shopItem)
    }
}