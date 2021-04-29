package com.szareckii.searchinthebasefssp.model.datasource

import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import com.szareckii.searchinthebasefssp.BuildConfig
import com.szareckii.searchinthebasefssp.model.data.physical.DataModelPhysical
import com.szareckii.searchinthebasefssp.model.data.status.DataModelStatus
import io.reactivex.Observable
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitImplementation : DataSource {

    override fun getDataPhysical(
            region: String,
            lastname: String,
            firstname: String,
            secondname: String?,
            birthdate: String?
    ): Observable<DataModelPhysical> {
        return getService().searchPhysical(BuildConfig.FSSP_API_KEY, region, lastname, firstname, secondname, birthdate)
    }

     override fun getDataStatus(
             task: String
     ): Observable<DataModelStatus> {
        return getService().status(BuildConfig.FSSP_API_KEY, task)
    }

    private fun getService(): ApiService {
        return createRetrofit().create(ApiService::class.java)
    }

    private fun createRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL_LOCATIONS)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
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
