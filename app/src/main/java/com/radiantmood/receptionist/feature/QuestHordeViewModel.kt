package com.radiantmood.receptionist.feature

import android.util.Log
import androidx.lifecycle.ViewModel
import com.radiantmood.receptionist.core.ItemTouchHelperAdapter
import com.radiantmood.receptionist.data.model.Quest
import com.radiantmood.receptionist.data.repo.QuestRepo
import com.radiantmood.receptionist.ext.lTag
import java.util.*
import javax.inject.Inject


class QuestHordeViewModel @Inject constructor(private val questRepo: QuestRepo) : ViewModel(), ItemTouchHelperAdapter {

    private val quests = mutableListOf<Quest>()
    val adapter = QuestHordeRVAdapter(quests)

    fun fetchQuests() {
        adapter.quests.addAll(questRepo.getQuests())
        adapter.notifyDataSetChanged()
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        val swapDirection = if (fromPosition < toPosition) 1 else -1
        for (i in fromPosition until toPosition) {
            Collections.swap(quests, i, i + swapDirection)
        }
        adapter.notifyItemMoved(fromPosition, toPosition)
    }

    override fun onItemDismiss(position: Int) {
        quests.removeAt(position)
        adapter.notifyItemRemoved(position)
    }

    override fun onLongPressSelect(position: Int) {
        // TODO: go into a multi-select long press mode
        Log.d(lTag, "TODO: go into multi-select mode")
    }
}

