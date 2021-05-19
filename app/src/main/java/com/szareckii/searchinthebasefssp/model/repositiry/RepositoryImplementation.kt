package com.szareckii.searchinthebasefssp.model.repositiry

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import com.szareckii.searchinthebasefssp.model.data.status.DataModelStatus
import com.szareckii.searchinthebasefssp.model.datasource.DataSource

class RepositoryImplementation(private val dataSource: DataSource) :
        Repository<DataModelResult> {

    override suspend fun getData(
            region: String,
            lastname: String,
            firstname: String,
            secondname: String?,
            birthdate: String?
    ): DataModelPhysical {

        return dataSource.getDataPhysical(
                region,
                lastname,
                firstname,
                secondname,
                birthdate
        )
    }

    override suspend fun getStatus(task: String): DataModelStatus {
        return dataSource.getDataStatus(task)
    }

    override suspend fun getResult(task: String): DataModelResult {
        return dataSource.getDataResult(task)
    }
}


