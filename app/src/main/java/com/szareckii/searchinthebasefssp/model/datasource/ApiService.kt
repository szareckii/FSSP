package com.szareckii.searchinthebasefssp.model.datasource

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.status.DataModelStatus
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("search/physical")
    fun searchPhysical(
        @Query("token") token: String,
        @Query("region") region: String,
        @Query("lastname") lastname: String,
        @Query("firstname") firstname: String,
        @Query("secondname") secondname: String? = null,
        @Query("birthdate") birthdate: String? = null
    ): Observable<DataModelPhysical>

    @GET("status")
    fun status(
            @Query("token") token: String,
            @Query("task") task: String
    ): Observable<DataModelStatus>

}

