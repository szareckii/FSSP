package com.szareckii.searchinthebasefssp.model.data.status

sealed class AppStateStatus {

    data class Success(val data: DataModelStatus?) : AppStateStatus()
    data class Error(val error: Throwable) : AppStateStatus()
    data class Loading(val progress: Int?) : AppStateStatus()
}
