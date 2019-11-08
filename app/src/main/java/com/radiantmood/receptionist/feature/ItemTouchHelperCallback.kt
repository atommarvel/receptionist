package com.radiantmood.receptionist.feature

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.radiantmood.receptionist.core.ItemTouchHelperAdapter
import com.radiantmood.receptionist.ext.ActionState
import com.radiantmood.receptionist.ext.isDragging
import com.radiantmood.receptionist.ext.isIdle
import com.radiantmood.receptionist.ext.lTag

class ItemTouchHelperCallback(private val adapterCallback: ItemTouchHelperAdapter) : ItemTouchHelper.Callback() {

    private var previousActionState: ActionState = ItemTouchHelper.ACTION_STATE_IDLE
    private var didItemMove: Boolean = false
    private var viewHolderStartingPosition: Int = -1

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun isLongPressDragEnabled(): Boolean = true

    override fun isItemViewSwipeEnabled(): Boolean = true

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        adapterCallback.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        didItemMove = true
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        adapterCallback.onItemDismiss(viewHolder.adapterPosition)
    }

    override fun onSelectedChanged(viewHolder: RecyclerView.ViewHolder?, actionState: Int) {
        super.onSelectedChanged(viewHolder, actionState)
        if (previousActionState.isIdle() && actionState.isDragging()) {
            viewHolder?.let {
                viewHolderStartingPosition = it.adapterPosition
                Log.d(lTag, "dragging started at $viewHolderStartingPosition")
            }
        }
        if (previousActionState.isDragging() && actionState.isIdle()) {
            onDraggingFinished(viewHolderStartingPosition)
        }
        previousActionState = actionState
    }

    private fun onDraggingFinished(position: Int) {
        if (!didItemMove) {
            adapterCallback.onLongPressSelect(position)
        }
        didItemMove = false
        viewHolderStartingPosition = -1
    }

}