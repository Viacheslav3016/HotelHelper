package com.example.hotelhelper.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.hotelhelper.data.OrderListRepIml
import com.example.hotelhelper.domain.AddShopOrderUseCase
import com.example.hotelhelper.domain.EditShopOrderUseCase
import com.example.hotelhelper.domain.GetShopOrderUseCase
import com.example.hotelhelper.domain.ShopItem

class ShopItemViewModel : ViewModel() {
    private val repository = OrderListRepIml
    private val addShopOrderUseCase = AddShopOrderUseCase(repository)
    private val getShopOrderUseCase = GetShopOrderUseCase(repository)
    private val editShopOrderUseCase = EditShopOrderUseCase(repository)

    private val _errorInputName = MutableLiveData<Boolean>()
    val errotInputname: LiveData<Boolean>
        get() = _errorInputName

    private val _errorInputSize = MutableLiveData<Boolean>()
    val errorInputsize: LiveData<Boolean>
        get() = _errorInputSize

    private val _errorInputCount = MutableLiveData<Boolean>()
    val errorInputCount: LiveData<Boolean>
        get() = _errorInputCount

    private val _closeScreen = MutableLiveData<Unit>()
        val closeScreen: LiveData<Unit>
        get() = _closeScreen


    private val _shopItem = MutableLiveData<ShopItem>()
    val shopItem: LiveData<ShopItem>
    get() = _shopItem

    fun addOrder(inputName: String?, inputSize: String?, inputCount: String?) {
        val name = parseName(inputName)
        val size = parseSize(inputSize)
        val count = parseCount(inputCount)
        val validField = validate(name, size, count)
        if (validField) {
            val shopItem = ShopItem(name, count, size, true)
            addShopOrderUseCase.addHotelOrder(shopItem)
            closeScreenN()
        }
    }

    fun editShopOrder(inputName: String?, inputSize: String?, inputCount: String?) {
        val name = parseName(inputName)
        val size = parseSize(inputSize)
        val count = parseCount(inputCount)
        val validField = validate(name, size, count)
        if (validField) {
            _shopItem.value?.let {
                val item = it.copy(name = name, count = count, size = size)
                editShopOrderUseCase.editHotelOrder(item)
                closeScreenN()
            }

        }
    }

    fun getShopOrder(shopItemId: Int) {
        val item = getShopOrderUseCase.getHotelOrder(shopItemId)
        _shopItem.value = item
    }

    private fun parseName(inputName: String?): String {
        return inputName?.trim() ?: ""
    }

    private fun parseSize(inputSize: String?): String {
        return inputSize?.trim() ?: ""
    }

    private fun validate(name: String, size: String, count: Int): Boolean {
        var result = true
        if (name.isBlank()) {
            _errorInputName.value = true
            result = false
        }
        if (size.isBlank()) {
            _errorInputSize.value = true
            result = false
        }
        if (count <= 0) {
            _errorInputCount.value = true
            result = false
        }
        return result
    }

    private fun parseCount(inputCount: String?): Int {
        return try {
            inputCount?.trim()?.toInt() ?: 0
        } catch (e: Exception) {
            0
        }
    }

    fun closeScreenN(){
        _closeScreen.value = Unit
    }

    fun resetErrorName() {
        _errorInputName.value = false
    }

    fun resetErrorSize() {
        _errorInputSize.value = false
    }

    fun resetErrorCount(){
        _errorInputCount.value = false
    }

}