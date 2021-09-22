package com.nagwa.task.Core.di

import com.nagwa.task.Core.view.viewModel.MainActivityVM
import com.nagwa.task.Data.RemoteSource.ApiHelperImpl
import com.nagwa.task.Data.RemoteSource.RetrofitBuilder
import com.nagwa.task.Data.Repository.FileRepositoryImpl
import com.nagwa.task.Domain.Contract.ApiHelper
import com.nagwa.task.Domain.Contract.FileRepository
import org.koin.dsl.module

val ApiHelperModule = module {
    single {
        ApiHelperImpl(RetrofitBuilder.apiService) as ApiHelper
    }
}

val FileRepositoryImplModule = module {
    single {
        FileRepositoryImpl(get()) as FileRepository
    }
}

val MainActivityVMModule = module {
    single {
        MainActivityVM(get())
    }
}
