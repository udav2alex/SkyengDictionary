package ru.gressor.skyengdictionary.data.local

import androidx.room.Entity
import androidx.room.Index
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    indices = [
        Index(value = arrayOf("timestamp"), unique = false)
    ]
)
data class HistoryItem(
    @field:PrimaryKey
    val word: String,
    val timestamp: Long = Date().time
)