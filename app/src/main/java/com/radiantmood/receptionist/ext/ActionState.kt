package com.radiantmood.receptionist.ext

import androidx.recyclerview.widget.ItemTouchHelper

typealias ActionState = Int

fun ActionState.isIdle() = this == ItemTouchHelper.ACTION_STATE_IDLE
fun ActionState.isDragging() = this == ItemTouchHelper.ACTION_STATE_DRAG