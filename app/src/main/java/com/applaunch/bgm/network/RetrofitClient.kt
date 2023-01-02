package com.applaunch.bgm.network

import com.applaunch.appbase.listner_base.BaseRepoListener
import com.applaunch.appbase.network_base.BaseRetrofitClient
import com.applaunch.appbase.network_base.NetworkUtils

class RetrofitClient(repoListener: BaseRepoListener) : BaseRetrofitClient(repoListener) {
    fun getApiService(): ApiService =
        provideRetrofit(provideOkHttpClient(), NetworkUtils.BASE_URL).create(ApiService::class.java)
}