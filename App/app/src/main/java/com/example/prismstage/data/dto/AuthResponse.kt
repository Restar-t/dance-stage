package com.example.prismstage.data.dto

// 这个类用于接收登录或注册成功后，后端服务器返回的JSON
data class AuthResponse(
    val success: Boolean,
    val message: String,
    val token: String? // token可能为空，所以是可空类型
)