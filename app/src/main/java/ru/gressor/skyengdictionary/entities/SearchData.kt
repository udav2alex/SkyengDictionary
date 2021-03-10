package ru.gressor.skyengdictionary.entities

sealed class SearchData {
    object Empty : SearchData()
    data class Success(val wordList: List<DictWord>): SearchData()
    data class Loading(val progress: Int?): SearchData()
    data class Error(val error: Throwable): SearchData()
}