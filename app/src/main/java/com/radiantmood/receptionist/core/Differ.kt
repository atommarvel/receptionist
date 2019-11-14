package com.radiantmood.receptionist.core

import androidx.recyclerview.widget.DiffUtil
import javax.inject.Inject

class Differ<T : Identified>
@Inject constructor() : DiffUtil.ItemCallback<T>() {

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean = oldItem.equals(newItem)
}