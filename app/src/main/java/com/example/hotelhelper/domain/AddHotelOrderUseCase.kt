package com.example.hotelhelper.domain

import com.example.hotelhelper.domain.repository.OrderListRepository

class AddHotelOrderUseCase(private val repository: OrderListRepository) {
    fun addHotelOrder(hotelItem: HotelItem){
        repository.addHotelOrder(hotelItem)
    }
}