package com.example.retrofit2_sandbox.data

import retrofit2.Call
import retrofit2.http.GET

interface MarsApiService {
    @GET("realestate")
    fun getProperties(): Call<List<MarsProperty>>
}