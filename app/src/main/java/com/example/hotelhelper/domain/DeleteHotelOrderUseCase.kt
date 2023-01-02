package com.example.hotelhelper.domain

import com.example.hotelhelper.domain.repository.OrderListRepository

class DeleteHotelOrderUseCase(private val repository: OrderListRepository) {
    fun deleteOrderItem(hotelItem: HotelItem){
        repository.deleteOrderItem(hotelItem)
    }
}