package ru.gressor.skyengdictionary.di

import androidx.room.Room
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gressor.core.di.NAME_SEARCH
import ru.gressor.core.BaseContract
import ru.gressor.core.entities.SearchData
import ru.gressor.core.entities.DictWord
import ru.gressor.skyengdictionary.data.local.*
import ru.gressor.skyengdictionary.data.remote.SearchDataSourceRemote
import ru.gressor.skyengdictionary.data.remote.RetrofitImpl
import ru.gressor.skyengdictionary.interactors.SearchInteractor
import ru.gressor.skyengdictionary.repos.SearchRepository
import ru.gressor.skyengdictionary.repos.SearchRepositoryLocal
import ru.gressor.skyengdictionary.viewmodels.SearchViewModel
import ru.gressor.skyengdictionary.views.SearchFragment

fun injectDependencies() = loadModules

private val loadModules by lazy {
    loadKoinModules(listOf(applicationModule, searchFragmentModule))
}

val applicationModule = module {
    single<HistoryRoomDB> {
        Room.databaseBuilder(get(), HistoryRoomDB::class.java, "dictionary.db")
            .fallbackToDestructiveMigration()
            .build()
    }
    single<HistoryDAO> {
        get<HistoryRoomDB>().getDAO()
    }
    single<BaseContract.RepositoryLocal<List<DictWord>>> {
        SearchRepositoryLocal(SearchDataSourceLocal(get()))
    }
    single<BaseContract.Repository<List<DictWord>>>(named(NAME_SEARCH)) {
        SearchRepository(SearchDataSourceRemote(RetrofitImpl()))
    }
}

val searchFragmentModule = module {
    scope<SearchFragment> {
        factory<BaseContract.Interactor<SearchData>>(named(NAME_SEARCH)) {
            SearchInteractor(get(named(NAME_SEARCH)), get())
        }
        viewModel {
            SearchViewModel(get(named(NAME_SEARCH)))
        }
    }
}
