package com.nagwa.task.Core.view.MainUi

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.nagwa.task.Core.view.Events.IClickFileListener
import com.nagwa.task.Core.view.adapters.ListFileAdapter
import com.nagwa.task.Core.view.viewModel.MainActivityVM
import com.nagwa.task.Domain.Model.FileModel
import com.nagwa.task.databinding.ActivityMainBinding
import com.nagwa.task.databinding.FilesLytBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity(), IClickFileListener {


    private val permissions = arrayOf(
        Manifest.permission.WRITE_EXTERNAL_STORAGE,
        Manifest.permission.READ_EXTERNAL_STORAGE,
    )

    var adapter: ListFileAdapter = ListFileAdapter()
    lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityVM by viewModel()


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)

        val requestCode = 200
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions, requestCode)
        }

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        adapter.list = viewModel.getFileList()

        binding.list.adapter = adapter

        adapter.click = this

        binding.list.layoutManager = LinearLayoutManager(this)


    }

    override fun select(viewItem: FilesLytBinding, item: FileModel) {
        viewModel.downloadFile(viewItem, item, this)
    }
}