package ru.gressor.skyengdictionary

import android.app.Application
import org.koin.core.context.startKoin
import ru.gressor.skyengdictionary.di.applicationModule
import ru.gressor.skyengdictionary.di.mainFragmentModule

class App: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin{
            modules(listOf(applicationModule, mainFragmentModule))
        }
    }
}