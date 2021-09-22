package com.nagwa.task.Data.RemoteSource

import io.reactivex.Observable
import okhttp3.ResponseBody

import retrofit2.http.GET
import retrofit2.http.Streaming
import retrofit2.http.Url

interface ApiService {
    @Streaming
    @GET
    fun downloadFile(@Url fileUrl: String): Observable<ResponseBody>
}