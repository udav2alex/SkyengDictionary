package ru.gressor.skyengdictionary.entities

import com.google.gson.annotations.Expose

data class DictWord(
    @Expose val id: Int,
    @Expose val text: String,
    @Expose val meanings: List<Meaning2>? = null
)

data class Meaning2(
    @Expose var id: Long = 0,
    @Expose var partOfSpeechCode: String? = null,
    @Expose var translation: Translation? = null,
    @Expose var previewUrl: String? = null,
    @Expose var imageUrl: String? = null,
    @Expose var transcription: String? = null,
    @Expose var soundUrl: String? = null
)

data class Translation(
    @Expose var text: String? = null,
    @Expose var note: String? = null
)