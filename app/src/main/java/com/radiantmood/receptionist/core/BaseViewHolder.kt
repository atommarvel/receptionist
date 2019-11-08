package com.radiantmood.receptionist.core

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder(private val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root) {

    /**
     * The variable id used by [ViewDataBinding.setVariable].
     */
    abstract val modelId: Int

    fun bind(any: Any) {
        binding.apply {
            setVariable(modelId, any)
            executePendingBindings()
        }
    }
}