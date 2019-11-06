package com.radiantmood.receptionist.core

import android.app.Application
import com.radiantmood.receptionist.di.AppComponent
import com.radiantmood.receptionist.di.ContextModule
import com.radiantmood.receptionist.di.DaggerAppComponent

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        initDagger()
    }

    private fun initDagger() {
        appComponent = DaggerAppComponent.builder()
            .contextModule(ContextModule(this))
            .build()
    }

    companion object {
        lateinit var appComponent: AppComponent
    }
}