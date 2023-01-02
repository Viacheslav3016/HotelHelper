package com.example.hotelhelper.domain

import com.example.hotelhelper.domain.repository.OrderListRepository

class GetOrderListUseCase(private val repository: OrderListRepository) {
    fun getListOrder():List<HotelItem>{
        return repository.getListOrder()
    }

}