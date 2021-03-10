package ru.gressor.historyscreen

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.loadKoinModules
import org.koin.core.qualifier.named
import org.koin.dsl.module
import ru.gressor.core.BaseContract
import ru.gressor.core.di.NAME_HISTORY
import ru.gressor.core.entities.HistoryData
import ru.gressor.core.entities.HistoryItem
import ru.gressor.skyengdictionary.data.local.HistoryDataSource

fun injectDependencies() = loadModules

private val loadModules by lazy {
    loadKoinModules(listOf(historyFragmentModule))
}

val historyFragmentModule = module {
    single<BaseContract.Repository<List<HistoryItem>>>(named(NAME_HISTORY)) {
        HistoryRepository(HistoryDataSource(get()))
    }
    factory<BaseContract.Interactor<HistoryData>>(named(NAME_HISTORY)) {
        HistoryInteractor(get(named(NAME_HISTORY)))
    }
    viewModel(named(NAME_HISTORY)) {
        HistoryViewModel(get(named(NAME_HISTORY)))
    }
}