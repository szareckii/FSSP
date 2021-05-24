package com.szareckii.searchinthebasefssp.model.repositiry

import com.szareckii.searchinthebasefssp.room.HistoryEntity


interface RepositoryLocal {

    suspend fun getHistoryEntityData(): List<HistoryEntity>?

    suspend fun saveToDB(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String?,
        birthdate: String?
    )
}