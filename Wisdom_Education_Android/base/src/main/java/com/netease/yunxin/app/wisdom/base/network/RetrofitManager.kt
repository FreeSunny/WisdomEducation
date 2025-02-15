/*
 * Copyright (c) 2021 NetEase, Inc.  All rights reserved.
 * Use of this source code is governed by a MIT license that can be found in the LICENSE file.
 */

package com.netease.yunxin.app.wisdom.base.network

import com.netease.yunxin.kit.alog.ALog
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.internal.platform.Platform
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by hzsunyj on 4/20/21.
 */
class RetrofitManager private constructor() {

    private val client: OkHttpClient

    private val headers: MutableMap<String, String> = HashMap()

    private val retrofitCache: MutableMap<String, Retrofit> = HashMap()

    private val connectTimeout = 30L

    private val readTimeout = 30L

    fun getHeader(): Map<String, String> {
        return headers
    }

    init {
        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.connectTimeout(connectTimeout, TimeUnit.SECONDS)
        clientBuilder.readTimeout(readTimeout, TimeUnit.SECONDS)
        clientBuilder.addInterceptor(Interceptor { chain: Interceptor.Chain ->
            val request = chain.request()
            val requestBuilder = request.newBuilder().method(request.method, request.body)
            headers.takeIf { it.isNotEmpty() }.let {
                for ((key, value) in headers) {
                    requestBuilder.addHeader(key, value)
                }
            }
            chain.proceed(requestBuilder.build())
        })
        clientBuilder.addInterceptor(HttpLoggingInterceptor(object : HttpLoggingInterceptor.Logger {
            override fun log(message: String) {
                ALog.i(message)
            }
        }).setLevel(HttpLoggingInterceptor.Level.BODY))
        client = clientBuilder.build()
    }

    companion object {
        private var instance: RetrofitManager? = null
        fun instance(): RetrofitManager {
            if (instance == null) {
                synchronized(RetrofitManager::class.java) {
                    if (instance == null) {
                        instance = RetrofitManager()
                    }
                }
            }
            return instance!!
        }
    }

    fun addHeader(key: String, value: String): RetrofitManager {
        headers[key] = value
        return this
    }

    // get service
    fun <T> getService(baseUrl: String, zClass: Class<T>): T {
        var retrofit = retrofitCache[baseUrl]
        if (retrofit == null) {
            retrofit = Retrofit.Builder().client(client).baseUrl(baseUrl)
                .addCallAdapterFactory(LiveDataCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            retrofitCache[baseUrl] = retrofit;
        }
        return retrofit!!.create(zClass)
    }
}