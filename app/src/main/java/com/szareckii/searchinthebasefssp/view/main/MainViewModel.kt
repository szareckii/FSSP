package com.szareckii.searchinthebasefssp.view.main

import androidx.lifecycle.LiveData
import com.szareckii.searchinthebasefssp.model.data.result.AppStateResult
import com.szareckii.searchinthebasefssp.viewmodel.BaseViewModel
import kotlinx.coroutines.*

class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<AppStateResult>() {

    companion object {
        //через сколько проверять результат запроса
        private const val TIME_REQUEST_REPEAT: Long = 5000
    }

    private val liveDataForViewToObserve: LiveData<AppStateResult> = _mutableLiveData

    private var task: String? = null

    // Статус запорса
    // 2 - запрос в обработке. 0 - выполнено.
    private var statusRequest: Int? = 2

    fun subscribe(): LiveData<AppStateResult> {
        return liveDataForViewToObserve
    }

    fun getData(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String?,
        birthdate: String?,
        isOnline: Boolean
    ) {
        _mutableLiveData.value = AppStateResult.Loading(null)
        cancelJob()
        val taskCoroutine =  viewModelCoroutineScope.async {
            startInteractorPhysical(
                region,
                lastname,
                firstname,
                secondname,
                birthdate,
                isOnline
            )
        }

        val statusCoroutine =  viewModelCoroutineScope.async {
            task = taskCoroutine.await()
            task?.let { startInteractorStatus(it) }
        }

        viewModelCoroutineScope.launch {
            statusRequest = statusCoroutine.await()
            while (statusRequest != 0 ) {
                //проверить результат выполнения запроса каждый TIME_REQUEST_REPEAT секунд
                delay(TIME_REQUEST_REPEAT)
                task?.let { statusRequest = startInteractorStatus(it) }
            }
            task?.let { startInteractorResult(it) }
        }
    }

    private suspend fun startInteractorPhysical(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String?,
        birthdate: String?,
        isOnline: Boolean
    ) = withContext(Dispatchers.IO) {
        interactor.getDataPhysical(region, lastname, firstname, secondname, birthdate, isOnline).responsePhysical?.task
    }

    private suspend fun startInteractorStatus(task: String) = withContext(Dispatchers.IO) {
        interactor.getDataStatus(task).responseStatus?.status
    }

    private suspend fun startInteractorResult(task: String) = withContext(Dispatchers.IO) {
        _mutableLiveData.postValue(interactor.getDataResult(task))
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppStateResult.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppStateResult.Success(null)
        super.onCleared()
    }
}
