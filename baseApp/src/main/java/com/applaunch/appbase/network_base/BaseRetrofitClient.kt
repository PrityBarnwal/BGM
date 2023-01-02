package com.applaunch.appbase.network_base

import BaseMapping
import com.applaunch.appbase.BuildConfig
import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.utils_base.BaseConstants
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

open class BaseRetrofitClient(var iRepositoryListener: BaseRepoListener?) {

    protected fun provideRetrofit(
        okHttpClient: OkHttpClient,
        networkUtils: NetworkUtils
    ): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BaseMapping.getBaseUrl(networkUtils))
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient)
            .build()
    }

    //Create OkHttpClient
    protected fun provideOkHttpClient(): OkHttpClient {
        val loggingInterceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val okHttpClientBuilder = OkHttpClient.Builder()

        okHttpClientBuilder.apply {
            connectTimeout(40, TimeUnit.SECONDS)
            readTimeout(40, TimeUnit.SECONDS)

            if (BuildConfig.DEBUG)
                addInterceptor(loggingInterceptor)
        }
        okHttpClientBuilder.addInterceptor { chain ->
            val original = chain.request()
            val request = original.newBuilder()
                .header(BaseConstants.Header.API_KEY,"cwcLz4ywd85KXeRT3menxVJrWCTJjn37HrU2Z5tbN6yYzhtBSA4EEapmXqMDLvgnQah7uyd2feUfRFqQUkSkRQLsPBVp6cQQ4PggN9Ft7fEcaS28656TnGNkwCQTe7y")
                .header(BaseConstants.Header.APP_VERSION,"1.0.0")
                .header(BaseConstants.Header.AUTHORIZATION, "Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiI2MzRlN2E5ODMzMGI1MjZmNGE0YTMyZmMiLCJpYXQiOjE2Njc3OTk1NzAsImV4cCI6MTY3MDM5MTU3MH0.HTMT5zw03214wbjLqsyzSIfK3RsdvU67d6rt0H6erFQ")
                .method(original.method, original.body)
                .build()
            chain.proceed(request)
        }
        return okHttpClientBuilder.build()
    }
}