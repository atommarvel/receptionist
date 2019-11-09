package com.radiantmood.receptionist.feature

import android.util.Log
import androidx.lifecycle.ViewModel
import com.radiantmood.receptionist.core.SingleLiveEvent
import com.radiantmood.receptionist.data.model.Quest
import com.radiantmood.receptionist.data.repo.QuestRepo
import com.radiantmood.receptionist.ext.lTag
import java.util.*
import javax.inject.Inject


class QuestHordeViewModel @Inject constructor(
    private val questRepo: QuestRepo
) : ViewModel(),
    ItemTouchHelperEventListener, ActionModeCallback {

    private val quests = mutableListOf<Quest>()
    val adapter = QuestHordeRVAdapter(quests)

    val actionModeLiveData = SingleLiveEvent<ActionModeInfo>()

    data class ActionModeInfo(val enable: Boolean, val position: Int)

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
        actionModeLiveData.value = ActionModeInfo(true, position)
    }

    override fun completeSelectedQuests() {
        Log.d(lTag, "time to complete quests!")
    }

    override fun onDestroyActionMode(mode: androidx.appcompat.view.ActionMode?) {
        actionModeLiveData.value = ActionModeInfo(false, -1)
    }
}

