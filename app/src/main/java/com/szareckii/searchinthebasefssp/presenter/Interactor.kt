package com.szareckii.searchinthebasefssp.presenter

import com.szareckii.searchinthebasefssp.model.data.physical.AppStatePhysical
import com.szareckii.searchinthebasefssp.model.data.status.AppStateStatus
import io.reactivex.Observable

interface Interactor {

    fun getDataPhysical(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String?,
        birthdate: String?,
        fromRemoteSource: Boolean
    ): Observable<AppStatePhysical>

//    fun getDataStatus(
//        task: String,
//    ): Observable<AppStateStatus>
}