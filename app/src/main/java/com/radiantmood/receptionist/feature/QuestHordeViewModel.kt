package com.radiantmood.receptionist.feature

import androidx.appcompat.view.ActionMode
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.radiantmood.receptionist.core.SingleLiveEvent
import com.radiantmood.receptionist.data.model.Quest
import com.radiantmood.receptionist.data.repo.QuestRepo
import com.radiantmood.receptionist.ext.togglePresence
import java.util.*
import javax.inject.Inject


class QuestHordeViewModel @Inject constructor(
    private val questRepo: QuestRepo
) : ViewModel(), ItemTouchHelperEventListener, ActionModeCallback {

    val actionModeLiveData = SingleLiveEvent<ActionModeInfo>()
    val selectionTracker = mutableSetOf<Quest>()

    data class ActionModeInfo(val enable: Boolean, val position: Int)

    private val quests: MutableList<Quest> = mutableListOf()
    val questsLiveData: MutableLiveData<List<Quest>> = MutableLiveData()

    fun fetchQuests() {
        quests.addAll(questRepo.getQuests())
        questsLiveData.value = quests
    }

    override fun onItemMove(fromPosition: Int, toPosition: Int) {
        val range =
            if (fromPosition < toPosition) fromPosition.until(toPosition) else fromPosition.downTo(toPosition + 1)
        val swapDirection = if (fromPosition < toPosition) 1 else -1
        for (i in range) {
            Collections.swap(quests, i, i + swapDirection)
        }
        questsLiveData.value = quests
    }

    override fun onItemDismiss(position: Int) {
        quests.removeAt(position)
        questsLiveData.value = quests
    }

    override fun onLongPressSelect(position: Int) {
        actionModeLiveData.value = ActionModeInfo(true, position)
    }

    override fun completeSelectedQuests() {
        quests.removeAll(selectionTracker)
        questsLiveData.value = quests
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        selectionTracker.clear()
        actionModeLiveData.value = ActionModeInfo(false, -1)
    }

    fun toggleSelected(position: Int) {
        val quest = quests[position]
        selectionTracker.togglePresence(quest)
    }

    fun isSelected(quest: Quest) = selectionTracker.contains(quest)

    fun selectedCount() = selectionTracker.size
}

