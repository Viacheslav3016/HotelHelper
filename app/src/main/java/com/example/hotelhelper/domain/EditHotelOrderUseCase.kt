package com.example.hotelhelper.domain

import com.example.hotelhelper.domain.repository.OrderListRepository

class EditHotelOrderUseCase(private val repository: OrderListRepository) {
    fun editHotelOrder(hotelItem: HotelItem){
        repository.editHotelOrder(hotelItem)
    }
}