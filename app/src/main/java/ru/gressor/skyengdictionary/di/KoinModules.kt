package ru.gressor.skyengdictionary.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gressor.skyengdictionary.MainContract
import ru.gressor.skyengdictionary.data.local.DataSourceLocal
import ru.gressor.skyengdictionary.data.remote.DataSourceRemote
import ru.gressor.skyengdictionary.data.remote.RetrofitImpl
import ru.gressor.skyengdictionary.interactors.MainInteractor
import ru.gressor.skyengdictionary.repos.MainRepository
import ru.gressor.skyengdictionary.viewmodels.MainViewModel

val applicationModule = module {
    single<MainContract.Repository>(named(NAME_LOCAL)) {
        MainRepository(DataSourceLocal())
    }

    single<MainContract.Repository>(named(NAME_REMOTE)) {
        MainRepository(DataSourceRemote(RetrofitImpl()))
    }
}

val mainFragmentModule = module {
    factory<MainContract.Interactor> {
        MainInteractor(get(named(NAME_REMOTE)), get(named(NAME_LOCAL)))
    }
    viewModel {
        MainViewModel(get())
    }
}