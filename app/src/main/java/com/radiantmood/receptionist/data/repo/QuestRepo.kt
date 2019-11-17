package com.radiantmood.receptionist.data.repo

import com.radiantmood.receptionist.data.model.Quest
import com.radiantmood.receptionist.di.PerFragment
import javax.inject.Inject

@PerFragment
class QuestRepo @Inject constructor() {
    fun getQuests() = (0..50).map { Quest("Title $it") }
}