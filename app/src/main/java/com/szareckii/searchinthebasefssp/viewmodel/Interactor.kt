package com.szareckii.searchinthebasefssp.viewmodel

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.status.DataModelStatus


interface Interactor<T> {

    suspend fun getDataPhysical(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String?,
        birthdate: String?,
        fromRemoteSource: Boolean
    ): DataModelPhysical

    suspend fun getDataStatus(task: String): DataModelStatus

    suspend fun getDataResult(task: String): T
}