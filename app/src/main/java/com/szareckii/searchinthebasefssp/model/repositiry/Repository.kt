package com.szareckii.searchinthebasefssp.model.repositiry

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import io.reactivex.Observable

interface Repository {

    fun getData(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String? = null,
        birthdate: String? = null
    ): Observable<DataModelPhysical>

    fun getResult(
            task: String
    ): Observable<DataModelResult>
}
