package com.radiantmood.receptionist.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.ListAdapter
import com.radiantmood.receptionist.core.BaseViewHolder
import com.radiantmood.receptionist.core.Differ
import com.radiantmood.receptionist.data.model.Quest
import com.radiantmood.receptionist.databinding.QuestItemBinding
import com.radiantmood.receptionist.ext.togglePresence

class QuestHordeRVAdapter(questDiffer: Differ<Quest>) :
    ListAdapter<Quest, QuestHordeRVAdapter.ViewHolder>(questDiffer) {

    val itemClickObserver = MutableLiveData<ClickEvent>()
    // TODO: Should probably be owned by the ViewModel. If so, then make ViewModel the ActionModeCallback again.
    val selectionTracker = mutableSetOf<Quest>()

    data class ClickEvent(val quest: Quest?, val position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = QuestItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quest = getItem(position)
        val isSelected = selectionTracker.contains(quest)
        holder.bind(quest, isSelected)
    }

    fun toggleSelected(position: Int) {
        val quest = getItem(position)
        selectionTracker.togglePresence(quest)
    }

    inner class ViewHolder(binding: QuestItemBinding) : BaseViewHolder<Quest>(binding, BR.quest) {
        init {
            binding.root.setOnClickListener {
                itemClickObserver.value = ClickEvent(model, adapterPosition)
            }
        }
    }
}