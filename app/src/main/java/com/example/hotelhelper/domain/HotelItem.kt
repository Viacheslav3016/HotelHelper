package com.example.hotelhelper.domain

data class HotelItem(
    val name:String,
    val count:Int,
    val size:String,
    val status:Boolean,
    var id:Int = UNDEFINED_ID
){
    companion object{
        const val UNDEFINED_ID = -1
    }
}
