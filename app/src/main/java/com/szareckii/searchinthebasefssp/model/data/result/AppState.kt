package com.szareckii.searchinthebasefssp.model.data.result

sealed class AppState {

    data class Success(val data: DataModelResult?) : AppState()
    data class Error(val error: Throwable) : AppState()
    data class Loading(val progress: Int?) : AppState()
}
