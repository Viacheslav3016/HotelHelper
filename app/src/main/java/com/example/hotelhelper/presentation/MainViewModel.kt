package com.example.hotelhelper.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotelhelper.data.OrderListRepIml
import com.example.hotelhelper.domain.DeleteHotelOrderUseCase
import com.example.hotelhelper.domain.EditHotelOrderUseCase
import com.example.hotelhelper.domain.GetOrderListUseCase
import com.example.hotelhelper.domain.HotelItem
import com.example.hotelhelper.domain.repository.OrderListRepository

class MainViewModel:ViewModel() {
    private val repository = OrderListRepIml
    private val editHotelOrderUseCase = EditHotelOrderUseCase(repository)
    private val deleteHotelOrderUseCase = DeleteHotelOrderUseCase(repository)
    private val getOrderListUseCase = GetOrderListUseCase(repository)
     val orderList = getOrderListUseCase.getListOrder()

    fun deleteOrder(hotelItem: HotelItem){
        deleteHotelOrderUseCase.deleteOrderItem(hotelItem)
    }

    fun editStatusOrder(hotelItem: HotelItem){
        val newitem = hotelItem.copy(status = !hotelItem.status)
        editHotelOrderUseCase.editHotelOrder(newitem)
    }
}