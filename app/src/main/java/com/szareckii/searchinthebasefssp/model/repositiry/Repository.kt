package com.szareckii.searchinthebasefssp.model.repositiry

import io.reactivex.Observable

interface Repository<T> {

    fun getData(
        region: String,
        lastname: String,
        firstname: String,
        secondname: String? = null,
        birthdate: String? = null
    ): Observable<T>
}
