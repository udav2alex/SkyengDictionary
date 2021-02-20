package ru.gressor.skyengdictionary.di

import dagger.Component
import ru.gressor.skyengdictionary.di.models.AppModule
import ru.gressor.skyengdictionary.di.models.InteractorModule
import ru.gressor.skyengdictionary.di.models.RepositoryModule
import ru.gressor.skyengdictionary.di.models.ViewModelModule
import ru.gressor.skyengdictionary.views.MainFragment
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AppModule::class,
        InteractorModule::class,
        RepositoryModule::class,
        ViewModelModule::class
    ]
)
interface AppComponent {
    fun inject(mainFragment: MainFragment)
}