package ru.gressor.skyengdictionary.di.models

import dagger.Module
import dagger.Provides
import ru.gressor.skyengdictionary.App
import javax.inject.Singleton

@Module
class AppModule(private val app: App) {

    @Provides
    @Singleton
    fun provideApp(): App = app
}