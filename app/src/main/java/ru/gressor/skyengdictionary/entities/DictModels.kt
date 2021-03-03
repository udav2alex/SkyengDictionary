package ru.gressor.skyengdictionary.entities

import android.os.Parcelable
import com.google.gson.annotations.Expose
import kotlinx.parcelize.Parcelize

@Parcelize
data class DictWord(
    @Expose val id: Int,
    @Expose val text: String,
    @Expose val meanings: List<Meaning2>
): Parcelable

@Parcelize
data class Meaning2(
    @Expose val id: Long = 0,
    @Expose val partOfSpeechCode: String? = null,
    @Expose val translation: Translation? = null,
    @Expose val previewUrl: String? = null,
    @Expose val imageUrl: String? = null,
    @Expose val transcription: String? = null,
    @Expose val soundUrl: String? = null
): Parcelable

@Parcelize
data class Translation(
    @Expose val text: String? = null,
    @Expose val note: String? = null
): Parcelable