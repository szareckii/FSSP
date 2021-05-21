package com.szareckii.searchinthebasefssp.model.datasource

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.AppState
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import com.szareckii.searchinthebasefssp.model.data.status.DataModelStatus
import com.szareckii.searchinthebasefssp.room.HistoryDao
import com.szareckii.searchinthebasefssp.room.HistoryEntity

class RoomDataBaseImplementation(private val historyDao: HistoryDao) : DataSourceLocal {

//    override suspend fun getHistoryEntityData(dataModelPhysical : DataModelPhysical): HistoryEntity? {
////        historyDao.getDataById(dataModelPhysical)
//    }

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