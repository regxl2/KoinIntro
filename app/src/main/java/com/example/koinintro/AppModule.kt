package com.example.koinintro

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

val appModule = module{
    single{
        Retrofit.Builder()
            .baseUrl("https://www.google.com")
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(MyApi::class.java)
    }
    // <MainRepository> it written specifies that it is provided as MainRepository not MainRepositoryImpl
    single<MainRepository>{
        MainRepositoryImpl(get())
        // get() is use to access the dependency which is already defined by the appModule
    }
//     if we want each class to have its own instance then use factory block
//    factory {
//        MainRepositoryImpl(get())
//    }

    // To create viewModel we use viewModel scope
    viewModel{
        MainViewModel(get())
    }
}