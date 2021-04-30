package com.szareckii.searchinthebasefssp.model.datasource

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
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

    override fun getDataResult(task: String): Observable<DataModelResult> {
        TODO("not implemented")
    }
}