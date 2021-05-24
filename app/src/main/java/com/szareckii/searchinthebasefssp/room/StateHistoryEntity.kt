package com.szareckii.searchinthebasefssp.room

sealed class StateHistoryEntity {

    data class Success(val data: List<HistoryEntity>?) : StateHistoryEntity()
    data class Error(val error: Throwable) : StateHistoryEntity()
    data class Loading(val progress: Int?) : StateHistoryEntity()
}
