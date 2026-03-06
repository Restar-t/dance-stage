package com.example.prismstage.data

import com.example.prismstage.data.dto.AuthRequest
import com.example.prismstage.data.dto.AuthResponse
import retrofit2.Response

class AuthRepository {

    private val authApiService = RetrofitInstance.authApi

    suspend fun login(request: AuthRequest): Response<AuthResponse> {
        return authApiService.login(request)
    }

    suspend fun register(request: AuthRequest): Response<AuthResponse> {
        return authApiService.register(request)
    }
}