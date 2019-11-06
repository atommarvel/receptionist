package com.radiantmood.receptionist.di

import com.radiantmood.receptionist.feature.QuestHordeFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules =
    [
        ViewModelModule::class,
        ViewModelFactoryModule::class,
        ContextModule::class
    ]
)
interface AppComponent {
    fun inject(questHordeFragment: QuestHordeFragment)
}