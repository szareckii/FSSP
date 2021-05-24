package com.szareckii.searchinthebasefssp.model.repositiry

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.status.DataModelStatus

interface Repository<T> {

    suspend fun getDataPhysical(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String? = null,
        birthdate: String? = null
    ): DataModelPhysical

    suspend fun getStatus(task: String): DataModelStatus

    suspend fun getResult(task: String): T
}
