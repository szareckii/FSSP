package com.szareckii.searchinthebasefssp.model.repositiry

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import com.szareckii.searchinthebasefssp.model.datasource.DataSource
import io.reactivex.Observable

class RepositoryImplementation(private val dataSource: DataSource) :
        Repository {

    override fun getData(
            region: String,
            lastname: String,
            firstname: String,
            secondname: String?,
            birthdate: String?
    ): Observable<DataModelPhysical> {

        return dataSource.getDataPhysical(
                region,
                lastname,
                firstname,
                secondname,
                birthdate
        )
    }

    override fun getResult(task: String): Observable<DataModelResult> {
        return dataSource.getDataResult(task)
    }
}


