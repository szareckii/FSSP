package com.szareckii.searchinthebasefssp.model.repositiry

import com.szareckii.searchinthebasefssp.model.data.result.AppState
import com.szareckii.searchinthebasefssp.room.HistoryEntity

interface RepositoryLocal<T> : Repository<T> {

//    suspend fun getHistoryEntityData(appState: AppState): HistoryEntity?

    suspend fun saveToDB(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String?,
        birthdate: String?
    )
}