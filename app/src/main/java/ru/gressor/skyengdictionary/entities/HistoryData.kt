package ru.gressor.skyengdictionary.entities

import ru.gressor.skyengdictionary.data.local.HistoryItem

sealed class HistoryData {
    object Empty : HistoryData()
    data class Success(val wordList: List<HistoryItem>): HistoryData()
    data class Loading(val progress: Int?): HistoryData()
    data class Error(val error: Throwable): HistoryData()
}