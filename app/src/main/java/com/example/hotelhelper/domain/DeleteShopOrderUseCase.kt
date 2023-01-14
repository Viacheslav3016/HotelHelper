package com.example.hotelhelper.domain

import com.example.hotelhelper.domain.repository.OrderListRepository

class DeleteShopOrderUseCase(private val repository: OrderListRepository) {
    fun deleteOrderItem(shopItem: ShopItem){
        repository.deleteOrderItem(shopItem)
    }
}