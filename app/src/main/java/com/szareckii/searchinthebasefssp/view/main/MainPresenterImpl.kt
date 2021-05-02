package com.szareckii.searchinthebasefssp.view.main

import com.szareckii.searchinthebasefssp.model.data.result.AppState
import com.szareckii.searchinthebasefssp.model.datasource.DataSourceLocal
import com.szareckii.searchinthebasefssp.model.datasource.DataSourceRemote
import com.szareckii.searchinthebasefssp.model.repositiry.RepositoryImplementation
import com.szareckii.searchinthebasefssp.presenter.Presenter
import com.szareckii.searchinthebasefssp.rx.SchedulerProvider
import com.szareckii.searchinthebasefssp.view.base.View
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableObserver
import java.util.concurrent.TimeUnit

class MainPresenterImpl<T : AppState, V : View>(
    private val interactor: MainInteractor,
    private val compositeDisposable: CompositeDisposable = CompositeDisposable(),
    private val schedulerProvider: SchedulerProvider = SchedulerProvider()
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
        currentView?.renderData(AppState.Loading(null))

        compositeDisposable.add(
                interactor.getDataPhysical(
                        region,
                        lastname,
                        firstname,
                        secondname,
                        birthdate,
                        isOnline
                ).delay(3000, TimeUnit.MILLISECONDS)
                .flatMap { dataPhysical ->
                    dataPhysical .responsePhysical?.task?.let {
                        interactor.getDataResult(it)
                    }
                }
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .subscribeWith(getObserver())
        )
    }

    private fun getObserver(): DisposableObserver<AppState> {
        return object : DisposableObserver<AppState>() {

            override fun onNext(appState: AppState) {
                currentView?.renderData(appState)
            }

            override fun onError(e: Throwable) {
                currentView?.renderData(AppState.Error(e))
            }

            override fun onComplete() {
            }
        }
    }

}

