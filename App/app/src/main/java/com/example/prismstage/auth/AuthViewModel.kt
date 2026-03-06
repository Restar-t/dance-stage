package com.example.prismstage.auth

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
// 保留这些import，即使暂时不用
import com.example.prismstage.data.AuthRepository
import com.example.prismstage.data.dto.AuthRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

// UI状态定义 (保持不变)
data class AuthUiState(
    val isLoading: Boolean = false,
    val errorMessage: String? = null,
    val loginSuccess: Boolean = false
)

class AuthViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState = _uiState.asStateFlow()

    // =================== 关键改动在这里 ===================
    // 我们保留了 repository，但暂时不创建它的实例
    // private val repository = AuthRepository()
    // ======================================================

    // --- 模拟的用户数据库 (用于本地演示) ---
    private val fakeUserDatabase = mutableMapOf(
        "test" to "123",
        "dapper" to "456"
    )

    // 使用纯本地模拟逻辑，不再调用repository
    fun login(username: String, password: String) {
        if (_uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            delay(1500) // 模拟1.5秒的网络延迟，增加真实感

            val storedPassword = fakeUserDatabase[username]
            if (storedPassword != null && storedPassword == password) {
                // 登录成功
                _uiState.update { it.copy(isLoading = false, loginSuccess = true) }
            } else {
                // 登录失败
                _uiState.update { it.copy(isLoading = false, errorMessage = "用户名或密码错误") }
            }
        }
    }

    // 注册功能也使用纯本地模拟逻辑
    fun register(username: String, password: String) {
        if (_uiState.value.isLoading) return

        viewModelScope.launch {
            _uiState.update { it.copy(isLoading = true, errorMessage = null) }
            delay(1000)

            if (username.length < 3) {
                _uiState.update { it.copy(isLoading = false, errorMessage = "用户名不能少于3个字符") }
                return@launch
            }
            if (password.length < 6) {
                _uiState.update { it.copy(isLoading = false, errorMessage = "密码不能少于6个字符") }
                return@launch
            }
            if (fakeUserDatabase.containsKey(username)) {
                _uiState.update { it.copy(isLoading = false, errorMessage = "该用户名已被注册") }
                return@launch
            }

            // 注册成功
            fakeUserDatabase[username] = password
            _uiState.update { it.copy(isLoading = false, errorMessage = "注册成功！现在可以用新账户登录了。") }
        }
    }

    fun resetLoginSuccess() {
        _uiState.update { it.copy(loginSuccess = false) }
    }
}