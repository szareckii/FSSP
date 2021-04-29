package com.szareckii.searchinthebasefssp.model.data.physical

sealed class AppStatePhysical {

    data class Success(val data: DataModelPhysical?) : AppStatePhysical()
    data class Error(val error: Throwable) : AppStatePhysical()
    data class Loading(val progress: Int?) : AppStatePhysical()
}
