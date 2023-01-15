package com.example.hotelhelper.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotelhelper.domain.ShopItem
import com.example.hotelhelper.domain.repository.OrderListRepository

object OrderListRepIml : OrderListRepository {
    private val listOrderLD = MutableLiveData<List<ShopItem>>()
    private val listOrder = sortedSetOf<ShopItem>({ o1, o2 -> o1.id.compareTo(o2.id)})
    private var autoIncrement = 0

    init {
        for (i in 0 until 10) {
            val item = ShopItem("name", i, "S", true)
            addHotelOrder(item)
        }
    }
    override fun getHotelOrder(hotelOrderId: Int): ShopItem {
        return listOrder.find { it.id == hotelOrderId}?:throw RuntimeException("Element with $hotelOrderId not finding")
    }

    override fun getListOrder(): LiveData<List<ShopItem>> {
        return listOrderLD;
    }

    override fun addHotelOrder(shopItem: ShopItem) {
        if (shopItem.id == ShopItem.UNDEFINED_ID){
            shopItem.id = autoIncrement++
        }
        listOrder.add(shopItem)
        updateList()
    }

    override fun deleteOrderItem(shopItem: ShopItem) {
        listOrder.remove(shopItem)
        updateList()
    }

    override fun editHotelOrder(shopItem: ShopItem) {
        val oldElement = getHotelOrder(shopItem.id)
        listOrder.remove(oldElement)
        addHotelOrder(shopItem)
    }

    private fun updateList(){
        listOrderLD.value = listOrder.toList()
    }
}