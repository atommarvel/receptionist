package com.radiantmood.receptionist

import androidx.lifecycle.ViewModel
import javax.inject.Inject

class QuestHordeViewModel @Inject constructor(private val questRepo: QuestRepo) : ViewModel() {

    val adapter = QuestHordeRVAdapter(mutableListOf())

    fun fetchQuests() {
        adapter.quests.addAll(questRepo.getQuests())
        adapter.notifyDataSetChanged()
    }
}
