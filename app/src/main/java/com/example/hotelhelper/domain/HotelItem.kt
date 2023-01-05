package com.example.hotelhelper.domain

data class HotelItem(
    val name:String,
    val count:Int,
    val status:Boolean,
    var id:Int = UNDEFIND_ID
){
    companion object{
        const val UNDEFIND_ID = -1
    }
}
