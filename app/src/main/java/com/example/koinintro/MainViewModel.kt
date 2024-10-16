package com.example.koinintro

import androidx.lifecycle.ViewModel

class MainViewModel(private val mainRepository: MainRepository): ViewModel() {
    fun doNetworkCall(){
        println("Simulation network call")
    }
}