package com.szareckii.searchinthebasefssp.model.datasource

import com.szareckii.searchinthebasefssp.room.HistoryEntity
import com.szareckii.searchinthebasefssp.room.StateHistoryEntity

interface DataSourceLocal {

    suspend fun getHistoryEntityData(): List<HistoryEntity>?

    suspend fun saveToDB(
         region: String,
         lastname: String,
         firstname: String,
         secondname: String?,
         birthdate: String?
    )
}