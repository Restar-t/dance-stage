package com.example.prismstage.data.dto

// 这个类用于发送登录或注册请求，它的结构要和后端服务器接收的JSON一致
data class AuthRequest(
    val username: String,
    val password: String
)