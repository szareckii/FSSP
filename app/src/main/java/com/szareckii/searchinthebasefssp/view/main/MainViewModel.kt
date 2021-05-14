package com.szareckii.searchinthebasefssp.view.main

import androidx.lifecycle.LiveData
import com.szareckii.searchinthebasefssp.model.data.result.AppState
import com.szareckii.searchinthebasefssp.viewmodel.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(private val interactor: MainInteractor) :
    BaseViewModel<AppState>() {

    private val liveDataForViewToObserve: LiveData<AppState> = _mutableLiveData

    fun subscribe(): LiveData<AppState> {
        return liveDataForViewToObserve
    }

    override fun getData(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String?,
        birthdate: String?,
        isOnline: Boolean
    ) {
        _mutableLiveData.value = AppState.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch {
            startInteractor(
                region,
                lastname,
                firstname,
                secondname,
                birthdate,
                isOnline
            )
        }
    }

    private suspend fun startInteractor(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String?,
        birthdate: String?,
        isOnline: Boolean
        ) = withContext(Dispatchers.IO) {

        val task = interactor.getDataPhysical(region, lastname, firstname, secondname, birthdate, isOnline).responsePhysical?.task

        delay(3000)

        _mutableLiveData.postValue(
//            parseSearchResults(
            task?.let {
                interactor
                    .getDataResult(it)
            }
        )
//            )
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(AppState.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = AppState.Success(null)
        super.onCleared()
    }
}