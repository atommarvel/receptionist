package com.radiantmood.receptionist.feature

import androidx.recyclerview.widget.RecyclerView

/**
 * Used to react to events caught by [ItemTouchHelperCallback] in order to update the [RecyclerView]'s data.
 */
interface ItemTouchHelperEventListener {
    fun onItemMove(fromPosition: Int, toPosition: Int)
    fun onItemDismiss(position: Int)
    fun onLongPressSelect(position: Int)
}