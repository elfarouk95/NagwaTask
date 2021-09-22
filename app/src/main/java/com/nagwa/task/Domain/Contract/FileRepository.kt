package com.nagwa.task.Domain.Contract

import com.nagwa.task.Domain.Model.FileModel
import io.reactivex.Observable


interface FileRepository {

    fun downloadFile(url: String, type: String): Observable<Number>

    fun saveFile(url: String, absolutePath: String): Observable<Number>

    fun getFakeFileList() : List<FileModel>



}