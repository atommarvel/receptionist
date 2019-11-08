package com.radiantmood.receptionist.core

interface ItemTouchHelperAdapter {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
    fun onLongPressSelect(position: Int)
}