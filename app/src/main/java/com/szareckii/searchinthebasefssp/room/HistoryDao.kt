package com.szareckii.searchinthebasefssp.room

import androidx.room.*

@Dao
interface HistoryDao {
    @Query("SELECT * FROM HistoryEntity order by id DESC LIMIT 10")
    suspend fun all(): List<HistoryEntity>
    @Query("SELECT * FROM HistoryEntity WHERE id  =  :id LIMIT 1")
    suspend fun getDataById(id: Int): HistoryEntity
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(entity: HistoryEntity)
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertAll(entities: List<HistoryEntity>)
    @Update
    suspend fun update(entity: HistoryEntity)
    @Delete
    suspend fun delete(entity: HistoryEntity)
}
