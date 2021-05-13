package com.szareckii.searchinthebasefssp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.szareckii.searchinthebasefssp.model.data.result.AppState
import com.szareckii.searchinthebasefssp.rx.SchedulerProvider
import io.reactivex.disposables.CompositeDisposable
import kotlinx.coroutines.*

abstract class BaseViewModel<T : AppState>(
    protected open val _mutableLiveData: MutableLiveData<T> = MutableLiveData()
) : ViewModel() {

    abstract fun getDataPhysical(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String?,
        birthdate: String?,
        isOnline: Boolean)

    protected val viewModelCoroutineScope = CoroutineScope(
        Dispatchers.Main
                + SupervisorJob()
                + CoroutineExceptionHandler { _, throwable ->
            handleError(throwable)
        })

    override fun onCleared() {
        super.onCleared()
        cancelJob()
    }

    protected fun cancelJob() {
        viewModelCoroutineScope.coroutineContext.cancelChildren()
    }

    abstract fun handleError(error: Throwable)
}