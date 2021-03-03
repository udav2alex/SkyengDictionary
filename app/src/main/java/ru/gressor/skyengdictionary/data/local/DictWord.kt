package ru.gressor.skyengdictionary.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(indices = [Index(value = arrayOf("id"), unique = true)])
data class DictWord(
    @field:PrimaryKey
    val id: Int,
    val text: String,
    val meanings: List<Meaning2>? = null
)