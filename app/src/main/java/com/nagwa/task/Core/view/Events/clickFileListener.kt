package com.nagwa.task.Core.view.Events

import android.content.Context
import com.nagwa.task.Domain.Model.FileModel
import com.nagwa.task.databinding.FilesLytBinding

interface IClickFileListener {

    fun select(viewItem: FilesLytBinding, item: FileModel)
}