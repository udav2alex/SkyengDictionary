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
import ru.gressor.skyengdictionary.entities.SearchData
import ru.gressor.skyengdictionary.entities.DictWord
import ru.gressor.skyengdictionary.interactors.SearchInteractor
import ru.gressor.skyengdictionary.repos.SearchRepository
import ru.gressor.skyengdictionary.repos.SearchRepositoryLocal
import ru.gressor.skyengdictionary.viewmodels.SearchViewModel

val applicationModule = module {
    single<HistoryRoomDB> {
        Room.databaseBuilder(get(), HistoryRoomDB::class.java, "dictionary.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    single<HistoryDAO> {
        get<HistoryRoomDB>().getDAO()
    }

    single<MainContract.RepositoryLocal<List<DictWord>>> {
        SearchRepositoryLocal(DataSourceLocal(get()))
    }

    single<MainContract.Repository<List<DictWord>>> {
        SearchRepository(DataSourceRemote(RetrofitImpl()))
    }
}

val mainFragmentModule = module {
    factory<MainContract.Interactor<SearchData>> {
        SearchInteractor(get(), get())
    }
    viewModel {
        SearchViewModel(get())
    }
}