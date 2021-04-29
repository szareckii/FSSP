package com.szareckii.searchinthebasefssp.view.main

import com.szareckii.searchinthebasefssp.model.data.physical.AppStatePhysical
import com.szareckii.searchinthebasefssp.model.data.status.AppStateStatus
import com.szareckii.searchinthebasefssp.model.datasource.DataSourceLocal
import com.szareckii.searchinthebasefssp.model.datasource.DataSourceRemote
import com.szareckii.searchinthebasefssp.model.repositiry.RepositoryImplementation
import com.szareckii.searchinthebasefssp.presenter.Presenter
import com.szareckii.searchinthebasefssp.rx.SchedulerProvider
import com.szareckii.searchinthebasefssp.view.base.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver

class MainPresenterImpl<T : AppStatePhysical, V : View>(
    private val interactor: MainInteractor = MainInteractor(
        RepositoryImplementation(DataSourceRemote()),
        RepositoryImplementation(DataSourceLocal())
    ),
    protected val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    protected val schedulerProvider: SchedulerProvider = SchedulerProvider()
) : Presenter<T, V> {
    private var currentView: V? = null

    override fun attachView(view: V) {
        if (view != currentView) {
            currentView = view
        }
    }

    override fun detachView(view: V) {
        compositeDisposable.clear()
        if (view == currentView) {
            currentView = null
        }
    }

    override fun getDataPhysical(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String?,
        birthdate: String?,
        isOnline: Boolean
    ) {
        currentView?.renderData(AppStatePhysical.Loading(null))

        compositeDisposable.add(
            interactor.getDataPhysical(
                region,
                lastname,
                firstname,
                secondname,
                birthdate,
                isOnline
            ).subscribeOn(schedulerProvider.io())
             .observeOn(schedulerProvider.ui())
            .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppStatePhysical> {
        return object : DisposableObserver<AppStatePhysical>() {

            override fun onNext(appStatePhysical: AppStatePhysical) {
                currentView?.renderData(appStatePhysical)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppStatePhysical.Error(e))
            }

            override fun onComplete() {
            }
        }
    }

}

