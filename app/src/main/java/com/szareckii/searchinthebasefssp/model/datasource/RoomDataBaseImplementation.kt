package com.szareckii.searchinthebasefssp.model.datasource

import com.szareckii.searchinthebasefssp.room.HistoryDao
import com.szareckii.searchinthebasefssp.room.HistoryEntity

class RoomDataBaseImplementation(private val historyDao: HistoryDao) : DataSourceLocal {

    override suspend fun getHistoryEntityData(): List<HistoryEntity>? {
        return historyDao.all()
    }

    override suspend fun saveToDB(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String?,
        birthdate: String?
    ) {
        historyDao.insert(
            HistoryEntity(0, region, lastname, firstname, secondname, birthdate)
        )
    }
}