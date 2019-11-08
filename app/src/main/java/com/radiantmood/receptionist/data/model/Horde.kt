package com.radiantmood.receptionist.data.model

import java.util.*

data class Horde(val quests: List<Quest>, val id: String = UUID.randomUUID().toString())