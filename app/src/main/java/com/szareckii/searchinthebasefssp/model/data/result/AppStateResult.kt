package com.szareckii.searchinthebasefssp.model.data.result

sealed class AppStateResult {

    data class Success(val data: DataModelResult?) : AppStateResult()
    data class Error(val error: Throwable) : AppStateResult()
    data class Loading(val progress: Int?) : AppStateResult()
}
