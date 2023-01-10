package com.example.hotelhelper.presentation.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.hotelhelper.R
import com.example.hotelhelper.domain.HotelItem

class OrderAdapter : RecyclerView.Adapter<OrderAdapter.OrderViewHolder>() {
    var orderList = listOf<HotelItem>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class OrderViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName = view.findViewById<TextView>(R.id.TextVIewName)
        val tvSize = view.findViewById<TextView>(R.id.TextViewSize)
        val tvCount = view.findViewById<TextView>(R.id.TextViewCount)

    }
    var onHotelItemLongClickListener:OnHotelItemLongClickListener?=null
    var onHotelItemClickListener:OnHotelItemClickListener?=null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layout =  when(viewType){
            STATUS_ENABLED -> R.layout.item_order_enabled
            STATUS_DISABLED -> R.layout.item_order_disable
            else -> {throw RuntimeException("unknown viewType $viewType")}
        }
                val view =
                 LayoutInflater.from(parent.context).inflate(
                     layout,
                     parent,
                     false
                 )
             return OrderViewHolder(view)
         }

    override fun onBindViewHolder(holder: OrderViewHolder, position: Int) {
        val orderItem = orderList[position]
        holder.itemView.setOnLongClickListener {
            onHotelItemLongClickListener?.onHotelItemLongClick(orderItem)
            true
        }
        holder.itemView.setOnClickListener{
            onHotelItemClickListener?.onHotelItemClick(orderItem)
        }
        holder.tvName.text = orderItem.name
        holder.tvSize.text = orderItem.size
        holder.tvCount.text = orderItem.count.toString()
    }

    override fun getItemCount(): Int {
        return orderList.size
    }

    override fun getItemViewType(position: Int): Int {
        val orderItem = orderList[position]
        return if (orderItem.status) {
            STATUS_ENABLED
        } else {
            STATUS_DISABLED
        }
    }

    interface OnHotelItemLongClickListener{
        fun onHotelItemLongClick(hotelItem: HotelItem)
    }

    interface OnHotelItemClickListener{
        fun onHotelItemClick(hotelItem: HotelItem)
    }
        companion object {
            const val STATUS_ENABLED = 0
            const val STATUS_DISABLED = 1
            const val MAX_POOL_SIZE = 5
        }
}