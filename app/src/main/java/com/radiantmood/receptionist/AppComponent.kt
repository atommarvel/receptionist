package com.radiantmood.receptionist

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