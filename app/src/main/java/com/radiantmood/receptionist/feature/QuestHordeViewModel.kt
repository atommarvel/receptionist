package com.radiantmood.receptionist.feature

import androidx.lifecycle.ViewModel
import com.radiantmood.receptionist.data.repo.QuestRepo
import javax.inject.Inject

class QuestHordeViewModel @Inject constructor(private val questRepo: QuestRepo) : ViewModel() {

    val adapter = QuestHordeRVAdapter(mutableListOf())

    fun fetchQuests() {
        adapter.quests.addAll(questRepo.getQuests())
        adapter.notifyDataSetChanged()
    }
}
