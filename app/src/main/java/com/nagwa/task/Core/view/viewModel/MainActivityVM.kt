package com.nagwa.task.Core.view.viewModel

import android.content.Context

import android.view.View
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModel
import com.nagwa.task.Domain.Contract.FileRepository
import com.nagwa.task.Domain.Model.FileModel
import com.nagwa.task.R
import com.nagwa.task.databinding.FilesLytBinding

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.UnknownHostException
import javax.inject.Inject


class MainActivityVM (private var fileRepository: FileRepository) : ViewModel() {

    fun getFileList(): List<FileModel> {
        return fileRepository.getFakeFileList()
    }

    fun downloadFile(viewItem: FilesLytBinding, item: FileModel, context: Context, count: Int = 0) {
        if (count < 3) {
            CoroutineScope(Dispatchers.IO).launch {
                val v = fileRepository.downloadFile(item.url, item.type)

                v.subscribeWith(object : Observable<Number>(), Observer<Number> {
                    override fun subscribeActual(observer: Observer<in Number>?) {

                    }

                    override fun onSubscribe(d: Disposable) {
                        try {
                            CoroutineScope(Dispatchers.Main).launch {
                                viewItem.download.visibility = View.INVISIBLE
                                viewItem.progress.visibility = View.VISIBLE
                            }
                        } catch (e: Throwable) {

                        }

                    }

                    override fun onNext(t: Number) {
                        try {
                            CoroutineScope(Dispatchers.Main).launch {
                                viewItem.progress.progress = (t.toFloat() * 100.0).toInt()
                            }
                        } catch (e: Throwable) {

                        }
                    }

                    override fun onError(e: Throwable) {
                        try {
                            CoroutineScope(Dispatchers.Main).launch {
                                viewItem.download.visibility = View.VISIBLE
                                viewItem.progress.visibility = View.INVISIBLE
                                if (e is UnknownHostException) {
                                    Toast.makeText(
                                        context,
                                        "check your Internet connection ",
                                        Toast.LENGTH_LONG
                                    ).show()
                                } else {
                                    Toast.makeText(
                                        context,
                                        "file ${item.name}  has ${e.message.toString()}  as Error",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }

                                downloadFile(viewItem, item, context, count + 1)
                            }
                        } catch (e: Throwable) {

                        }
                    }

                    override fun onComplete() {
                        try {
                            CoroutineScope(Dispatchers.Main).launch {
                                viewItem.download.setBackgroundDrawable(
                                    ContextCompat.getDrawable(
                                        context,
                                        R.drawable.checked
                                    )
                                )
                                item.downloaded = true
                                viewItem.download.visibility = View.VISIBLE
                                viewItem.progress.visibility = View.INVISIBLE
                            }
                        } catch (e: Throwable) {

                        }
                    }

                })
            }
        } else {
            viewItem.download.visibility = View.VISIBLE
            viewItem.progress.visibility = View.INVISIBLE
            viewItem.progress.progress = 0
        }

    }


}