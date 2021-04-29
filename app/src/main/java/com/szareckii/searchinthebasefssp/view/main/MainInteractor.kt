package com.szareckii.searchinthebasefssp.view.main

import com.szareckii.searchinthebasefssp.model.data.physical.AppStatePhysical
import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.status.AppStateStatus
import com.szareckii.searchinthebasefssp.model.repositiry.Repository
import com.szareckii.searchinthebasefssp.presenter.Interactor
import io.reactivex.Observable

class MainInteractor(
    private val remoteRepository: Repository<DataModelPhysical>,
    private val localRepository: Repository<DataModelPhysical>
) : Interactor{

    override fun getDataPhysical(
            region: String,
            lastname: String,
            firstname: String,
            secondname: String?,
            birthdate: String?,
            fromRemoteSource: Boolean
    ): Observable<AppStatePhysical> {
        return if (fromRemoteSource) {
            remoteRepository.getData(
                region,
                lastname,
                firstname,
                secondname,
                birthdate
            ).map {
                AppStatePhysical.Success(it)
            }
        } else {
            localRepository.getData(
                region,
                lastname,
                firstname,
                secondname,
                birthdate
            ).map {
                AppStatePhysical.Success(it)
            }
        }
    }
}
