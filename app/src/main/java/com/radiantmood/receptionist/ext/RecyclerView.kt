package com.radiantmood.receptionist.ext

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.toggleSelected(position: Int) {
    val viewAtPosition = (layoutManager as? LinearLayoutManager)?.findViewByPosition(position)
    viewAtPosition?.isActivated = viewAtPosition?.isActivated != true
}