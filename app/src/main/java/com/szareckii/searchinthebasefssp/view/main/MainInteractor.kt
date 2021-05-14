package com.szareckii.searchinthebasefssp.view.main

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.AppState
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import com.szareckii.searchinthebasefssp.model.repositiry.Repository
import com.szareckii.searchinthebasefssp.viewmodel.Interactor

class MainInteractor(
    private val remoteRepository: Repository<DataModelResult>,
    private val localRepository: Repository<DataModelResult>
    ) : Interactor<AppState> {

    override suspend fun getDataPhysical(
            region: String,
            lastname: String,
            firstname: String,
            secondname: String?,
            birthdate: String?,
            fromRemoteSource: Boolean
    ): DataModelPhysical {
        return if (fromRemoteSource) {
            remoteRepository
        } else {
            localRepository
        }.getData(
            region,
            lastname,
            firstname,
            secondname,
            birthdate
        )
    }

    override suspend fun getDataResult(task: String): AppState {
        return AppState.Success(remoteRepository.getResult(task))
    }
}
