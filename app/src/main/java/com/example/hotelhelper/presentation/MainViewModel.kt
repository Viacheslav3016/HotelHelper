package com.example.hotelhelper.presentation

import androidx.lifecycle.ViewModel
import com.example.hotelhelper.data.OrderListRepIml
import com.example.hotelhelper.domain.DeleteShopOrderUseCase
import com.example.hotelhelper.domain.EditShopOrderUseCase
import com.example.hotelhelper.domain.GetOrderListUseCase
import com.example.hotelhelper.domain.ShopItem

class MainViewModel:ViewModel() {
    private val repository = OrderListRepIml
    private val editShopOrderUseCase = EditShopOrderUseCase(repository)
    private val deleteShopOrderUseCase = DeleteShopOrderUseCase(repository)
    private val getOrderListUseCase = GetOrderListUseCase(repository)
     val orderList = getOrderListUseCase.getListOrder()

    fun deleteOrder(shopItem: ShopItem){
        deleteShopOrderUseCase.deleteOrderItem(shopItem)
    }

    fun editStatusOrder(shopItem: ShopItem){
        val newitem = shopItem.copy(status = !shopItem.status)
        editShopOrderUseCase.editHotelOrder(newitem)
    }
}