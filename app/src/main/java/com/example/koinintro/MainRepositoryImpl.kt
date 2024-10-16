package com.example.koinintro

class MainRepositoryImpl(private val myApi: MyApi): MainRepository {
    override fun doNetworkCall() {
        myApi.callApi()
    }
}