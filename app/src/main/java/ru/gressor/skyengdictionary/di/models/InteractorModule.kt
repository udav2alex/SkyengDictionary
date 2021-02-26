package ru.gressor.skyengdictionary.di.models

import dagger.Module
import dagger.Provides
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.di.NAME_LOCAL
import ru.gressor.skyengdictionary.di.NAME_REMOTE
import ru.gressor.skyengdictionary.interactors.MainInteractor
import javax.inject.Named
import javax.inject.Singleton

@Module
class InteractorModule {

    @Provides
    @Singleton
    fun provideInteractor(
        @Named(NAME_REMOTE) repositoryRemote: MainContract.Repository,
        @Named(NAME_LOCAL) repositoryLocal: MainContract.Repository
    ): MainContract.Interactor = MainInteractor(repositoryRemote, repositoryLocal)
}