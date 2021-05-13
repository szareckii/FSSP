package com.szareckii.searchinthebasefssp.presenter

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.AppState
import io.reactivex.Observable

interface Interactor<T> {

    suspend fun getDataPhysical(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String?,
        birthdate: String?,
        fromRemoteSource: Boolean
    ): DataModelPhysical

    suspend fun getDataResult(task: String): T
}