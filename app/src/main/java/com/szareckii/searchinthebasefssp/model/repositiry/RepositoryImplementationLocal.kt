package com.szareckii.searchinthebasefssp.model.repositiry

import com.szareckii.searchinthebasefssp.model.datasource.DataSourceLocal
import com.szareckii.searchinthebasefssp.room.HistoryEntity
import com.szareckii.searchinthebasefssp.room.StateHistoryEntity

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal) :
    RepositoryLocal{

    override suspend fun saveToDB(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String?,
        birthdate: String?
    ) {
        dataSource.saveToDB(
            region,
            lastname,
            firstname,
            secondname,
            birthdate
        )
    }

    override suspend fun getHistoryEntityData(): List<HistoryEntity>? {
        return dataSource.getHistoryEntityData()
    }


}