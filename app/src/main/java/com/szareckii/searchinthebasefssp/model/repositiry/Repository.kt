package com.szareckii.searchinthebasefssp.model.repositiry

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical

interface Repository<T> {

    suspend fun getData(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String? = null,
        birthdate: String? = null
    ): DataModelPhysical

    suspend fun getResult(task: String): T
}
