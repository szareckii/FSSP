package com.szareckii.searchinthebasefssp.model.repositiry

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.AppState
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import com.szareckii.searchinthebasefssp.model.data.status.DataModelStatus
import com.szareckii.searchinthebasefssp.model.datasource.DataSourceLocal
import com.szareckii.searchinthebasefssp.room.HistoryEntity

class RepositoryImplementationLocal(private val dataSource: DataSourceLocal) :
    RepositoryLocal<DataModelResult> {

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

//    override suspend fun getHistoryEntityData(appState: AppState): HistoryEntity? {
//        return dataSource.getHistoryEntityData(appState)
//    }
//
    override suspend fun getDataPhysical(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String?,
        birthdate: String?
    ): DataModelPhysical {
        TODO("Not yet implemented")
    }

    override suspend fun getStatus(task: String): DataModelStatus {
        TODO("Not yet implemented")
    }

    override suspend fun getResult(task: String): DataModelResult {
        TODO("Not yet implemented")
    }

}