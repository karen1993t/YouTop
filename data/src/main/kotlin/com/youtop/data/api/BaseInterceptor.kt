package com.youtop.data.api

import com.youtop.data.api.AppRestServiceConstants.CLIENT_DEVICE
import com.youtop.data.api.AppRestServiceConstants.CONTENT_TYPE
import com.youtop.data.api.AppRestServiceConstants.CONTENT_TYPE_HEADER
import com.youtop.data.api.AppRestServiceConstants.DEVICE
import okhttp3.Interceptor

internal class BaseInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader(CONTENT_TYPE_HEADER, CONTENT_TYPE)
            .addHeader(CLIENT_DEVICE, DEVICE)
            .build()
        return chain.proceed(request)
    }
}