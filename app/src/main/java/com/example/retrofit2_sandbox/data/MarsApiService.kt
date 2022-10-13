package com.example.retrofit2_sandbox.data

import retrofit2.http.GET

interface MarsApiService {
    @GET("realestate")
    suspend fun getProperties(): List<MarsProperty>
}