package com.radiantmood.receptionist

import javax.inject.Inject

class QuestRepo @Inject constructor() {
    fun getQuests() = listOf<Quest>(
        Quest("title1"),
        Quest("title2"),
        Quest("title3"),
        Quest("title4"),
        Quest("title5"),
        Quest("title6"),
        Quest("title7"),
        Quest("title8"),
        Quest("title9"),
        Quest("title10"),
        Quest("title11"),
        Quest("title12"),
        Quest("title13"),
        Quest("title14"),
        Quest("title15")
    )
}