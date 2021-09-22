package com.nagwa.task.Domain.Contract


import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Url

interface ApiHelper
{
    fun downloadFile(url: String) : Observable<ResponseBody>
}