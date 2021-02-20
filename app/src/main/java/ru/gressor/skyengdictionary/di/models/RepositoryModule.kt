package ru.gressor.skyengdictionary.di.models

import dagger.Module
import dagger.Provides
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.data.DataSourceLocal
import ru.gressor.skyengdictionary.data.DataSourceRemote
import ru.gressor.skyengdictionary.di.NAME_LOCAL
import ru.gressor.skyengdictionary.di.NAME_REMOTE
import ru.gressor.skyengdictionary.repos.MainRepository
import javax.inject.Named
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    fun provideRepositoryLocal(
        @Named(NAME_LOCAL) dataSourceLocal: MainContract.DataSource
    ): MainContract.Repository = MainRepository(dataSourceLocal)

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    fun provideRepositoryRemote(
        @Named(NAME_REMOTE) dataSourceRemote: MainContract.DataSource
    ): MainContract.Repository = MainRepository(dataSourceRemote)

    @Provides
    @Singleton
    @Named(NAME_LOCAL)
    fun provideDataSourceLocal(): MainContract.DataSource = DataSourceLocal()

    @Provides
    @Singleton
    @Named(NAME_REMOTE)
    fun provideDataSourceRemote(): MainContract.DataSource = DataSourceRemote()
}