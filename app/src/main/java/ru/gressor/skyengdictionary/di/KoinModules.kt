package ru.gressor.skyengdictionary.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.data.local.DataSourceLocal
import ru.gressor.skyengdictionary.data.local.HistoryDAO
import ru.gressor.skyengdictionary.data.local.HistoryRoomDB
import ru.gressor.skyengdictionary.data.remote.DataSourceRemote
import ru.gressor.skyengdictionary.data.remote.RetrofitImpl
import ru.gressor.skyengdictionary.interactors.MainInteractor
import ru.gressor.skyengdictionary.repos.MainRepository
import ru.gressor.skyengdictionary.repos.RepositoryLocal
import ru.gressor.skyengdictionary.viewmodels.MainViewModel

val applicationModule = module {
    single<HistoryRoomDB> {
        Room.databaseBuilder(get(), HistoryRoomDB::class.java, "dictionary.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single<HistoryDAO> {
        get<HistoryRoomDB>().getDAO()
    }

    single<MainContract.RepositoryLocal> {
        RepositoryLocal(DataSourceLocal(get()))
    }

    single<MainContract.Repository> {
        MainRepository(DataSourceRemote(RetrofitImpl()))
    }
}

val mainFragmentModule = module {
    factory<MainContract.Interactor> {
        MainInteractor(get(), get())
    }
    viewModel {
        MainViewModel(get())
    }
}