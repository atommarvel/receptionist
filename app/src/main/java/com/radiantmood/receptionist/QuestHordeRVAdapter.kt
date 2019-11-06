package com.radiantmood.receptionist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class QuestHordeRVAdapter(val quests: MutableList<Quest>) :
    RecyclerView.Adapter<QuestHordeRVAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        LayoutInflater
            .from(parent.context)
            .inflate(R.layout.quest_item, parent, false)
            .wrap()

    private fun View.wrap(): ViewHolder = ViewHolder(this)

    override fun getItemCount(): Int = quests.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.onBind(quests[position])

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun onBind(quest: Quest) {

        }

    }
}