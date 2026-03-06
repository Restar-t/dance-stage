package com.example.prismstage.data

import com.example.prismstage.data.dto.AuthRequest
import com.example.prismstage.data.dto.AuthResponse
import retrofit2.Response // 注意：这里要用Retrofit的Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthApiService {

    @POST("api/login")
    suspend fun login(@Body request: AuthRequest): Response<AuthResponse>

    @POST("api/register")
    suspend fun register(@Body request: AuthRequest): Response<AuthResponse>
}