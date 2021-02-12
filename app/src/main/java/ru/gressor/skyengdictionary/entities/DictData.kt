package ru.gressor.skyengdictionary.entities

sealed class DictData {
    data class DictItems(val wordList: List<DictWord>): DictData()
    data class Loading(val progress: Int): DictData()
    data class Error(val message: String): DictData()
}