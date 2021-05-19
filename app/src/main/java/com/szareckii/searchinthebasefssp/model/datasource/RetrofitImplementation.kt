package com.szareckii.searchinthebasefssp.model.datasource

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.szareckii.searchinthebasefssp.BuildConfig
import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.result.DataModelResult
import com.szareckii.searchinthebasefssp.model.data.status.DataModelStatus
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImplementation : DataSource {

    override suspend fun getDataPhysical(
            region: String,
            lastname: String,
            firstname: String,
            secondname: String?,
            birthdate: String?
    ): DataModelPhysical {
        return getService().searchPhysical(BuildConfig.FSSP_API_KEY, region, lastname, firstname, secondname, birthdate).await()
    }

    override suspend fun getDataStatus(
        task: String
    ): DataModelStatus {
        return getService().getStatus(BuildConfig.FSSP_API_KEY, task).await()
    }

    override suspend fun getDataResult(
             task: String
     ): DataModelResult {
        return getService().getResult(BuildConfig.FSSP_API_KEY, task).await()
    }

    private fun getService(): ApiService {
        return createRetrofit().create(ApiService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .client(createOkHttpClient())
            .build()
    }

    private fun createOkHttpClient(): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
        httpClient.addInterceptor(
                HttpLoggingInterceptor()
                        .setLevel(HttpLoggingInterceptor.Level.HEADERS))
        return httpClient.build()
    }

    companion object {
        private const val BASE_URL_LOCATIONS = "https://api-ip.fssp.gov.ru/api/v1.0/"
    }
}
