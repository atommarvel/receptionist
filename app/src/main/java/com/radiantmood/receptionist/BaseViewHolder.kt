package com.radiantmood.receptionist

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {
    abstract val modelId: Int

    fun bind(any: Any) {
        binding.apply {
            setVariable(modelId, any)
            executePendingBindings()
        }
    }
}