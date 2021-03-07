package ru.gressor.core.entities

sealed class HistoryData {
    object Empty : HistoryData()
    data class Success(val itemsList: List<HistoryItem>): HistoryData()
    data class Loading(val progress: Int?): HistoryData()
    data class Error(val error: Throwable): HistoryData()
}