package com.radiantmood.receptionist.data.model

import java.util.*

data class Quest(val title: String, val id: String = UUID.randomUUID().toString())