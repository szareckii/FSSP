package com.szareckii.searchinthebasefssp.model.datasource

import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import com.szareckii.searchinthebasefssp.model.data.status.DataModelStatus
import kotlinx.coroutines.Deferred
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
    ): Deferred<DataModelPhysical>

    @GET("status")
    fun getStatus(
        @Query("token") token: String,
        @Query("task") task: String
    ): Deferred<DataModelStatus>


    @GET("result")
    fun getResult(
            @Query("token") token: String,
            @Query("task") task: String
    ): Deferred<DataModelResult>

}

