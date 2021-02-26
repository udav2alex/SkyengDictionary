package ru.gressor.skyengdictionary.entities

sealed class DictData {
    object Empty : DictData()
    data class Success(val wordList: List<DictWord>): DictData()
    data class Loading(val progress: Int?): DictData()
    data class Error(val error: Throwable): DictData()
}