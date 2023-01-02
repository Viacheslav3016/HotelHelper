package com.example.hotelhelper.domain

data class HotelItem(
    var id:Int = UNDEFIND_ID,
    val name:String,
    val count:Int,
    val status:Boolean
){
    companion object{
        const val UNDEFIND_ID = -1
    }
}
