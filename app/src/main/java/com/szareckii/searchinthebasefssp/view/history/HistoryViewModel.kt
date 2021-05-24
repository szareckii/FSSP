package com.szareckii.searchinthebasefssp.view.history

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.szareckii.searchinthebasefssp.model.data.result.AppStateResult
import com.szareckii.searchinthebasefssp.room.StateHistoryEntity
import com.szareckii.searchinthebasefssp.viewmodel.BaseViewModel
import kotlinx.coroutines.*

class HistoryViewModel(
    private val interactor: HistoryInteractor,
) : BaseViewModel<StateHistoryEntity>() {

    private val liveDataForViewToObserve: LiveData<StateHistoryEntity> = _mutableLiveData

    fun subscribe(): LiveData<StateHistoryEntity> {
        return liveDataForViewToObserve
    }

    fun getData() {
        _mutableLiveData.value = StateHistoryEntity.Loading(null)
        cancelJob()
        viewModelCoroutineScope.launch { startInteractor() }
    }

    private suspend fun startInteractor() {
        _mutableLiveData.postValue(interactor.getHistoryEntityData())
    }

    override fun handleError(error: Throwable) {
        _mutableLiveData.postValue(StateHistoryEntity.Error(error))
    }

    override fun onCleared() {
        _mutableLiveData.value = StateHistoryEntity.Success(null)
        super.onCleared()
    }
}