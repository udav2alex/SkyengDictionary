package ru.gressor.skyengdictionary

import android.app.Application
import ru.gressor.skyengdictionary.di.AppComponent
import ru.gressor.skyengdictionary.di.models.AppModule
import ru.gressor.skyengdictionary.di.DaggerAppComponent

class App: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        appComponent = DaggerAppComponent.builder()
            .appModule(AppModule(this))
            .build()
    }

    companion object {
        private lateinit var instance: App
        fun getInstance() = instance
    }
}