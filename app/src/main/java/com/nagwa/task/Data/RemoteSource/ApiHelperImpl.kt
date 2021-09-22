package com.nagwa.task.Data.RemoteSource


import android.webkit.URLUtil
import com.nagwa.task.Domain.Contract.ApiHelper
import io.reactivex.Observable
import okhttp3.ResponseBody
import retrofit2.http.Url
import java.lang.IllegalArgumentException
import javax.inject.Inject

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override fun downloadFile(url: String): Observable<ResponseBody> {
        if (URLUtil.isValidUrl(url))
            return apiService.downloadFile(url)

        throw IllegalArgumentException("Not Valid Url");
    }
}