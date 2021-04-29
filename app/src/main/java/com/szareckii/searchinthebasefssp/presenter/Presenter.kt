package com.szareckii.searchinthebasefssp.presenter

import com.szareckii.searchinthebasefssp.model.data.physical.AppStatePhysical
import com.szareckii.searchinthebasefssp.view.base.View

interface Presenter<T : AppStatePhysical, V : View> {

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
