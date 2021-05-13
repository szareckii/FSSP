package com.szareckii.searchinthebasefssp.presenter

import com.szareckii.searchinthebasefssp.model.data.result.AppState

interface Presenter<T : AppState, V : View> {

    fun attachView(view: V)

    fun detachView(view: V)

    fun getDataPhysical(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String?,
        birthdate: String?,
        isOnline: Boolean
    )
}
