package com.radiantmood.receptionist.di

import com.radiantmood.receptionist.feature.QuestHordeFragment
import dagger.Component
import javax.inject.Scope

@Scope
annotation class PerFragment

@PerFragment
@Component(
    dependencies = [AppComponent::class],
    modules =
    [
        ViewModelModule::class,
        ViewModelFactoryModule::class
    ]
)
interface FragmentComponent {
    fun inject(fragment: QuestHordeFragment)
}