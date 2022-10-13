package com.example.retrofit2_sandbox.data

sealed class MainViewState {
    data class Success(val list: List<MarsProperty>) : MainViewState()
    data class Error(val message: String): MainViewState()
}