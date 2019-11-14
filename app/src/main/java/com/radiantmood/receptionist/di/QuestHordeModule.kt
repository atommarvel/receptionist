package com.radiantmood.receptionist.di

import com.radiantmood.receptionist.core.Differ
import com.radiantmood.receptionist.feature.QuestHordeRVAdapter
import dagger.Module
import dagger.Provides

@Module
class QuestHordeModule {

    @PerFragment
    @Provides
    fun adapter(): QuestHordeRVAdapter = QuestHordeRVAdapter(Differ())
}