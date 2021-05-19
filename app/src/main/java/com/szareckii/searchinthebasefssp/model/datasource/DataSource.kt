package com.szareckii.searchinthebasefssp.model.datasource

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import com.szareckii.searchinthebasefssp.model.data.status.DataModelStatus

interface DataSource {

    suspend fun getDataPhysical(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String? = null,
        birthdate: String? = null
    ): DataModelPhysical

    suspend fun getDataStatus(
        task: String
    ): DataModelStatus

    suspend fun getDataResult(
        task: String
    ): DataModelResult
}
