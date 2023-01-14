package com.example.hotelhelper.presentation.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.hotelhelper.R
import com.example.hotelhelper.domain.ShopItem
import com.example.hotelhelper.presentation.OrderViewHolder
import com.example.hotelhelper.presentation.Utils.OrderItemDiff

class OrderAdapter :
    androidx.recyclerview.widget.ListAdapter<ShopItem, OrderViewHolder>(OrderItemDiff()) {

    var onHotelItemLongClickListener: OnHotelItemLongClickListener? = null
    var onHotelItemClickListener: ((ShopItem) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderViewHolder {
        val layout = when (viewType) {
            STATUS_ENABLED -> R.layout.item_order_enabled
            STATUS_DISABLED -> R.layout.item_order_disable
            else -> {
                throw RuntimeException("unknown viewType $viewType")
            }
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
        val orderItem = getItem(position)
        holder.itemView.setOnLongClickListener {
            onHotelItemLongClickListener?.onHotelItemLongClick(orderItem)
            true
        }
        holder.itemView.setOnClickListener {
            onHotelItemClickListener?.invoke(orderItem)
        }
        holder.tvName.text = orderItem.name
        holder.tvSize.text = orderItem.size
        holder.tvCount.text = orderItem.count.toString()
    }


    override fun getItemViewType(position: Int): Int {
        val orderItem = getItem(position)
        return if (orderItem.status) {
            STATUS_ENABLED
        } else {
            STATUS_DISABLED
        }
    }

    interface OnHotelItemLongClickListener {
        fun onHotelItemLongClick(shopItem: ShopItem)
    }

    interface OnHotelItemClickListener {
        fun onHotelItemClick(shopItem: ShopItem)
    }

    companion object {
        const val STATUS_ENABLED = 0
        const val STATUS_DISABLED = 1
        const val MAX_POOL_SIZE = 5
    }
}