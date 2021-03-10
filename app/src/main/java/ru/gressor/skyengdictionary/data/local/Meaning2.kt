package ru.gressor.skyengdictionary.data.local

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Meaning2(
    @field:PrimaryKey
    val id: Long = 0,
    val partOfSpeechCode: String? = null,
    @Embedded val translation: Translation? = null,
    val previewUrl: String? = null,
    val imageUrl: String? = null,
    val transcription: String? = null,
    val soundUrl: String? = null
)

data class Translation(
    val text: String? = null,
    val note: String? = null
)