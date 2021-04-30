package com.szareckii.searchinthebasefssp.model.datasource

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import io.reactivex.Observable

interface DataSource {

    fun getDataPhysical(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String? = null,
        birthdate: String? = null
    ): Observable<DataModelPhysical>

    fun getDataResult(
        task: String
    ): Observable<DataModelResult>
}
