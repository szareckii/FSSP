package com.szareckii.searchinthebasefssp.model.datasource

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import com.szareckii.searchinthebasefssp.model.data.status.DataModelStatus

class RoomDataBaseImplementation : DataSource {

    override suspend fun getDataPhysical(
            region: String,
            lastname: String,
            firstname: String,
            secondname: String?,
            birthdate: String?
    ): DataModelPhysical {
        TODO("not implemented")
    }

    override suspend fun getDataStatus(task: String): DataModelStatus {
        TODO("Not yet implemented")
    }

    override suspend fun getDataResult(task: String): DataModelResult {
        TODO("not implemented")
    }
}