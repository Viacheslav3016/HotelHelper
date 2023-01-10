package com.example.hotelhelper.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hotelhelper.domain.HotelItem
import com.example.hotelhelper.domain.repository.OrderListRepository

object OrderListRepIml : OrderListRepository {
    private val listOrderLD = MutableLiveData<List<HotelItem>>()
    private val listOrder = sortedSetOf<HotelItem>({o1,o2 -> o1.id.compareTo(o2.id)})
    private var autoIncrement = 0

    init {
        for (i in 0 until 10) {
            val item = HotelItem("name", i, "S", true)
            addHotelOrder(item)
        }
    }
    override fun getHotelOrder(hotelOrderId: Int): HotelItem {
        return listOrder.find { it.id == hotelOrderId}?:throw RuntimeException("Element with $hotelOrderId not finding")
    }

    override fun getListOrder(): LiveData<List<HotelItem>> {
        return listOrderLD;
    }

    override fun addHotelOrder(hotelItem: HotelItem) {
        if (hotelItem.id == HotelItem.UNDEFINED_ID){
            hotelItem.id = autoIncrement++
        }
        listOrder.add(hotelItem)
        updateList()
    }

    override fun deleteOrderItem(hotelItem: HotelItem) {
        listOrder.remove(hotelItem)
        updateList()
    }

    override fun editHotelOrder(hotelItem: HotelItem) {
        val oldElement = getHotelOrder(hotelItem.id)
        listOrder.remove(oldElement)
        addHotelOrder(hotelItem)
    }

    private fun updateList(){
        listOrderLD.value = listOrder.toList()
    }
}