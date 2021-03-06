package ru.gressor.skyengdictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import ru.gressor.core.entities.HistoryItem

@Database(entities = [HistoryItem::class], version = 1, exportSchema = false)
abstract class HistoryRoomDB: RoomDatabase() {

    abstract fun getDAO(): HistoryDAO
}