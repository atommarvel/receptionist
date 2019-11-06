package com.radiantmood.receptionist.di

import androidx.lifecycle.ViewModel
import com.radiantmood.receptionist.feature.QuestHordeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(QuestHordeViewModel::class)
    abstract fun bindMyViewModel(myViewModel: QuestHordeViewModel): ViewModel
}