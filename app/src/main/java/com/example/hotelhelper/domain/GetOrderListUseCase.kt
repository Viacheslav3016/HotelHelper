package com.example.hotelhelper.domain

import androidx.lifecycle.LiveData
import com.example.hotelhelper.domain.repository.OrderListRepository

class GetOrderListUseCase(private val repository: OrderListRepository) {
    fun getListOrder():LiveData<List<ShopItem>>{
        return repository.getListOrder()
    }

}