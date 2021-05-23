package com.szareckii.searchinthebasefssp.view.main

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.AppStateResult
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import com.szareckii.searchinthebasefssp.model.data.status.DataModelStatus
import com.szareckii.searchinthebasefssp.model.repositiry.Repository
import com.szareckii.searchinthebasefssp.model.repositiry.RepositoryLocal
import com.szareckii.searchinthebasefssp.viewmodel.Interactor

class MainInteractor(
    private val remoteRepository: Repository<DataModelResult>,
    private val localRepository: RepositoryLocal
) : Interactor<AppStateResult> {

    override suspend fun getDataPhysical(
            region: String,
            lastname: String,
            firstname: String,
            secondname: String?,
            birthdate: String?,
            fromRemoteSource: Boolean
    ): DataModelPhysical {

//Записали в БД информацию о запросе
        localRepository.saveToDB(
            region,
            lastname,
            firstname,
            secondname,
            birthdate
        )

//Сделали АПИ-запрос
        return remoteRepository.getDataPhysical(
            region,
            lastname,
            firstname,
            secondname,
            birthdate
        )
    }

    override suspend fun getDataStatus(task: String): DataModelStatus {
           return remoteRepository.getStatus(task)
    }

    override suspend fun getDataResult(task: String): AppStateResult {
        return AppStateResult.Success(remoteRepository.getResult(task))
    }
}
