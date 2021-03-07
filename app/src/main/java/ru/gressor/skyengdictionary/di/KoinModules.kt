package ru.gressor.skyengdictionary.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.data.local.*
import ru.gressor.skyengdictionary.data.remote.SearchDataSourceRemote
import ru.gressor.skyengdictionary.data.remote.RetrofitImpl
import ru.gressor.skyengdictionary.entities.SearchData
import ru.gressor.skyengdictionary.entities.DictWord
import ru.gressor.skyengdictionary.entities.HistoryData
import ru.gressor.skyengdictionary.interactors.SearchInteractor
import ru.gressor.skyengdictionary.repos.SearchRepository
import ru.gressor.skyengdictionary.repos.SearchRepositoryLocal
import ru.gressor.skyengdictionary.viewmodels.SearchViewModel
import ru.gressor.historyscreen.HistoryInteractor
import ru.gressor.historyscreen.HistoryRepository
import ru.gressor.historyscreen.HistoryViewModel

val applicationModule = module {
    single<HistoryRoomDB> {
        Room.databaseBuilder(get(), HistoryRoomDB::class.java, "dictionary.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single<HistoryDAO> {
        get<HistoryRoomDB>().getDAO()
    }
    single<MainContract.Repository<List<HistoryItem>>>(named(NAME_HISTORY)) {
        HistoryRepository(HistoryDataSource(get()))
    }
    single<MainContract.RepositoryLocal<List<DictWord>>> {
        SearchRepositoryLocal(SearchDataSourceLocal(get()))
    }
    single<MainContract.Repository<List<DictWord>>>(named(NAME_SEARCH)) {
        SearchRepository(SearchDataSourceRemote(RetrofitImpl()))
    }
}

val searchFragmentModule = module {
    factory<MainContract.Interactor<SearchData>>(named(NAME_SEARCH)) {
        SearchInteractor(get(named(NAME_SEARCH)), get())
    }
    viewModel(named(NAME_SEARCH)) {
        SearchViewModel(get(named(NAME_SEARCH)))
    }
}

val historyFragmentModule = module {
    factory<MainContract.Interactor<HistoryData>>(named(NAME_HISTORY)) {
        HistoryInteractor(get(named(NAME_HISTORY)))
    }
    viewModel(named(NAME_HISTORY)) {
        HistoryViewModel(get(named(NAME_HISTORY)))
    }
}

const val NAME_HISTORY = "history"
const val NAME_SEARCH = "search"
