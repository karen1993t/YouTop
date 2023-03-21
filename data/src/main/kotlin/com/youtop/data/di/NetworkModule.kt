package com.youtop.data.di

import com.youtop.data.BuildConfig
import com.youtop.data.api.BaseInterceptor
import com.youtop.data.api.NetworkLogInterceptor
import com.youtop.data.api.YouTopAppRestServiceApi
import okhttp3.OkHttpClient
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal val networkModule = module {
    singleOf(::getOkHttpClient)
    singleOf(::getRetrofit)
    singleOf(::getYouTopAppRestService)
    singleOf(::BaseInterceptor)
    singleOf(::NetworkLogInterceptor)

}

private fun getRetrofit(okHttpClient: OkHttpClient): Retrofit = Retrofit
    .Builder()
    .baseUrl(BuildConfig.BASE_URL)
    .client(okHttpClient)
    .addConverterFactory(GsonConverterFactory.create())
    .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
    .build()

private fun getYouTopAppRestService(retrofit: Retrofit): YouTopAppRestServiceApi =
    retrofit.create(YouTopAppRestServiceApi::class.java)

private fun getOkHttpClient(
    baseInterceptor: BaseInterceptor,
    networkLogInterceptor: NetworkLogInterceptor,
): OkHttpClient = OkHttpClient.Builder()
    .addInterceptor(baseInterceptor)
    .addInterceptor(networkLogInterceptor)
    .build()

