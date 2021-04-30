package com.szareckii.searchinthebasefssp.model.datasource

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import io.reactivex.Observable

class DataSourceLocal(
    private val remoteProvider: RoomDataBaseImplementation = RoomDataBaseImplementation()
) : DataSource{

    override fun getDataPhysical(
            region: String,
            lastname: String,
            firstname: String,
            secondname: String?,
            birthdate: String?
    ): Observable<DataModelPhysical> =
            remoteProvider.getDataPhysical(
                region,
                lastname,
                firstname,
                secondname,
                birthdate
            )

    override fun getDataResult(task: String): Observable<DataModelResult> =
            remoteProvider.getDataResult(task)
}