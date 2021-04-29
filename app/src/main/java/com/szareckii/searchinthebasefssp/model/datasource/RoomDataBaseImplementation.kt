package com.szareckii.searchinthebasefssp.model.datasource

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.status.DataModelStatus
import io.reactivex.Observable

class RoomDataBaseImplementation : DataSource {

    override fun getDataPhysical(
            region: String,
            lastname: String,
            firstname: String,
            secondname: String?,
            birthdate: String?
    ): Observable<DataModelPhysical> {
        TODO("not implemented")
    }

    override fun getDataStatus(task: String): Observable<DataModelStatus> {
        TODO("not implemented")
    }
}