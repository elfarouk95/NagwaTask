package com.nagwa.task.Core.base

import android.app.Application
import com.nagwa.task.Core.di.*

import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(applicationContext)
            modules(
                listOf(
                    ApiHelperModule,
                    FileRepositoryImplModule,
                    MainActivityVMModule
                )
            )
        }

    }

}