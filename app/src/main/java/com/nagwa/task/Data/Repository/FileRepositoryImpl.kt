package com.nagwa.task.Data.Repository

import android.os.Environment
import com.nagwa.task.Data.FakeData.getFakeList
import com.nagwa.task.Domain.Contract.ApiHelper
import com.nagwa.task.Domain.Contract.FileRepository
import com.nagwa.task.Domain.Model.FileModel
import com.nagwa.task.Util.randomName
import io.reactivex.Observable
import java.io.File
import java.io.FileOutputStream
import java.io.InputStream
import javax.inject.Inject

class FileRepositoryImpl(private val apiHelper: ApiHelper) : FileRepository {

    override fun downloadFile(url: String, type: String): Observable<Number> {
        val path = Environment.getExternalStorageDirectory()

        var fileName = randomName()

        if (type == "PDF") {
            fileName += ".pdf"
        } else {
            fileName += ".mp4"
        }
        val file = File(path, fileName)

        return saveFile(url, file.absolutePath);

    }

    override fun saveFile(url: String, absolutePath: String): Observable<Number> {

        return Observable.create {
            apiHelper.downloadFile(url).subscribe(
                { responseBody ->

                    if (responseBody == null) {
                        it.onNext(-1)
                        it.onComplete()
                    }

                    var input: InputStream? = null

                    try {
                        var fileSizeDownloaded = 0
                        input = responseBody.byteStream()

                        val fos = FileOutputStream(absolutePath)
                        fos.use { output ->
                            val buffer = ByteArray(4 * 1024) // or other buffer size

                            while (true) {
                                val read = input.read(buffer)
                                if (read == -1)
                                    break
                                output.write(buffer, 0, read)
                                fileSizeDownloaded += read;
                                it.onNext(fileSizeDownloaded.toFloat() / responseBody.contentLength())
                            }
                            output.flush()
                        }

                        it.onComplete()
                    } catch (e: Exception) {
                        it.onError(e)

                    } finally {
                        input?.close()
                    }
                },
                { error ->
                    it.onError(error)
                }
            )

        }
    }

    override fun getFakeFileList(): List<FileModel> {
        return getFakeList()
    }
}