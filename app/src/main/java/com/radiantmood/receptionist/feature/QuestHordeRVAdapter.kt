package com.radiantmood.receptionist.feature

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView
import com.radiantmood.receptionist.core.BaseViewHolder
import com.radiantmood.receptionist.data.model.Quest
import com.radiantmood.receptionist.databinding.QuestItemBinding

class QuestHordeRVAdapter(val quests: MutableList<Quest>) : RecyclerView.Adapter<QuestHordeRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = QuestItemBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = quests.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(quests[position])

    inner class ViewHolder(binding: QuestItemBinding) : BaseViewHolder(binding) {
        override val modelId = BR.quest
    }
}