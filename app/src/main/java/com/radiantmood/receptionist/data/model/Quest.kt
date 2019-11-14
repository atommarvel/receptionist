package com.radiantmood.receptionist.data.model

import com.radiantmood.receptionist.core.Identified
import java.util.*

data class Quest(val title: String, override val id: String = UUID.randomUUID().toString()) : Identified