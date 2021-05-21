package com.szareckii.searchinthebasefssp.model.datasource

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.AppState
import com.szareckii.searchinthebasefssp.room.HistoryEntity

interface DataSourceLocal {

//    suspend fun getHistoryEntityData(dataModelPhysical : DataModelPhysical): HistoryEntity?

    suspend fun saveToDB(
         region: String,
         lastname: String,
         firstname: String,
         secondname: String?,
         birthdate: String?
    )
}