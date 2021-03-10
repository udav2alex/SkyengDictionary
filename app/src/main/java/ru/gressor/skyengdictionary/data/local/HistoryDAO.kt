package ru.gressor.skyengdictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface HistoryDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveHistoryItem(historyItem: HistoryItem)

    @Query("SELECT * FROM HistoryItem ORDER BY timestamp DESC;")
    suspend fun getHistory(): List<HistoryItem>
}