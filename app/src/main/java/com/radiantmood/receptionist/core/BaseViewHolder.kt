package com.radiantmood.receptionist.core

import androidx.annotation.CallSuper
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * @param modelId The variable id used by [ViewDataBinding.setVariable].
 */
abstract class BaseViewHolder<T>(private val binding: ViewDataBinding, private val modelId: Int) :
    RecyclerView.ViewHolder(binding.root) {

    var model: T? = null

    @CallSuper
    open fun bind(newModel: T, isSelected: Boolean) {
        model = newModel
        binding.apply {
            setVariable(modelId, model)
            executePendingBindings()
        }
        binding.root.isActivated = isSelected
    }
}