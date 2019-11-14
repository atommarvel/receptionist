package com.radiantmood.receptionist.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.ListAdapter
import com.radiantmood.receptionist.core.BaseViewHolder
import com.radiantmood.receptionist.core.Differ
import com.radiantmood.receptionist.data.model.Quest
import com.radiantmood.receptionist.databinding.QuestItemBinding

class QuestHordeRVAdapter(questDiffer: Differ<Quest>) :
    ListAdapter<Quest, QuestHordeRVAdapter.ViewHolder>(questDiffer) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = QuestItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(getItem(position))

    class ViewHolder(binding: QuestItemBinding) : BaseViewHolder<Quest>(binding, BR.quest)
}