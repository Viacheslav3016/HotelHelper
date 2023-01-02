package com.example.hotelhelper.domain

import com.example.hotelhelper.domain.repository.OrderListRepository

class GetHotelOrderUseCase(private val repository: OrderListRepository) {
    fun getHotelOrder(hotelOrderId:Int):HotelItem{
    return repository.getHotelOrder(hotelOrderId)
    }
}