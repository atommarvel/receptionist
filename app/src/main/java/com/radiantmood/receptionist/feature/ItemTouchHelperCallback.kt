package com.radiantmood.receptionist.feature

import android.util.Log
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.radiantmood.receptionist.ext.ActionState
import com.radiantmood.receptionist.ext.isDragging
import com.radiantmood.receptionist.ext.isIdle
import com.radiantmood.receptionist.ext.lTag

class ItemTouchHelperCallback(
    private val eventListener: ItemTouchHelperEventListener,
    private val isActionModeEnabled: () -> Boolean
) : ItemTouchHelper.Callback() {

    /**
     * Tracks last [ActionState] provided by the [onSelectedChanged]'s action param.
     * Used to detect an item being long-pressed, but then not dragged (which starts multi-select).
     */
    private var previousActionState: ActionState = ItemTouchHelper.ACTION_STATE_IDLE

    /**
     * Tracks if an item moves during an [ItemTouchHelper.ACTION_STATE_DRAG] session.
     * Used to detect an item being long-pressed, but then not dragged (which starts multi-select).
     */
    private var didItemMove: Boolean = false

    /**
     * The viewholder is null when [onSelectedChanged] is triggered with an [ItemTouchHelper.ACTION_STATE_IDLE].
     * Because of this we have to take note of where the viewholder started, so we know which item to start off as
     * selected in multi-select mode if the item did *not* move ([didItemMove]).
     * Used to detect an item being long-pressed, but then not dragged (which starts multi-select).
     */
    private var viewHolderStartingPosition: Int = -1

    override fun getMovementFlags(recyclerView: RecyclerView, viewHolder: RecyclerView.ViewHolder): Int {
        val dragFlags = ItemTouchHelper.UP or ItemTouchHelper.DOWN
        val swipeFlags = ItemTouchHelper.START or ItemTouchHelper.END
        return makeMovementFlags(dragFlags, swipeFlags)
    }

    override fun isLongPressDragEnabled(): Boolean = !isActionModeEnabled()

    override fun isItemViewSwipeEnabled(): Boolean = !isActionModeEnabled()

    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        eventListener.onItemMove(viewHolder.adapterPosition, target.adapterPosition)
        didItemMove = true
        return true
    }

    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        eventListener.onItemDismiss(viewHolder.adapterPosition)
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
            eventListener.onLongPressSelect(position)
        }
        // reset multi-select tracking
        didItemMove = false
        viewHolderStartingPosition = -1
    }
}