package com.example.koinintro

import retrofit2.http.GET

interface MyApi {
    @GET("my/endpoint")
    fun callApi()
}