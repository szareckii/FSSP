package com.szareckii.searchinthebasefssp.view.main

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.AppState
import com.szareckii.searchinthebasefssp.model.repositiry.Repository
import com.szareckii.searchinthebasefssp.presenter.Interactor
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: Repository,
    private val localRepository: Repository
    ) : Interactor<AppState>{

    override fun getDataPhysical(
            region: String,
            lastname: String,
            firstname: String,
            secondname: String?,
            birthdate: String?,
            fromRemoteSource: Boolean
    ): Observable<DataModelPhysical> {
        return if (fromRemoteSource) {
            remoteRepository.getData(
                region,
                lastname,
                firstname,
                secondname,
                birthdate
            )
        } else {
            localRepository.getData(
                region,
                lastname,
                firstname,
                secondname,
                birthdate
            )
        }
    }

    override fun getDataResult(task: String): Observable<AppState> {
        return remoteRepository.getResult(task).map {
            AppState.Success(it)
        }
    }
}
