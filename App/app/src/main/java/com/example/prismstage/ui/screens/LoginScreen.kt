package com.example.prismstage.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.prismstage.R
import com.example.prismstage.auth.AuthViewModel

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit // 登录成功后的回调函数，由MainActivity传入
) {
    // 获取AuthViewModel的实例
    val authViewModel: AuthViewModel = viewModel()
    // 从ViewModel中订阅UI状态
    val uiState by authViewModel.uiState.collectAsState()

    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }

    // 当登录成功状态变为true时，执行回调并重置状态
    LaunchedEffect(uiState.loginSuccess) {
        if (uiState.loginSuccess) {
            onLoginSuccess()
            authViewModel.resetLoginSuccess() // 防止重复触发
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier.fillMaxSize().padding(32.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // 添加App图标
            Icon(
                painter = painterResource(id = R.drawable.ic_prism_logo), // App Logo资源
                contentDescription = "App Logo", // 无障碍描述
                modifier = Modifier.size(120.dp),
                tint = MaterialTheme.colorScheme.primary // 使用主题主色调
            )
            Spacer(modifier = Modifier.height(16.dp))

            Text("欢迎来到棱镜舞台", style = MaterialTheme.typography.headlineLarge)
            Text(
                "记录你的每一次闪光",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Gray
            )
            Spacer(modifier = Modifier.height(32.dp))

            OutlinedTextField(
                value = username,
                onValueChange = { username = it },
                label = { Text("用户名") },
                modifier = Modifier.fillMaxWidth(),
                isError = uiState.errorMessage != null // 如果有错误信息，输入框变红
            )
            Spacer(modifier = Modifier.height(16.dp))

            OutlinedTextField(
                value = password,
                onValueChange = { password = it },
                label = { Text("密码") },
                modifier = Modifier.fillMaxWidth(),
                visualTransformation = PasswordVisualTransformation(),
                isError = uiState.errorMessage != null
            )
            Spacer(modifier = Modifier.height(8.dp))

            // 显示错误信息
            uiState.errorMessage?.let { message ->
                Text(
                    text = message,
                    color = MaterialTheme.colorScheme.error,
                    modifier = Modifier.fillMaxWidth()
                )
            }
            Spacer(modifier = Modifier.height(24.dp))

            Button(
                // 点击时调用ViewModel的login方法
                onClick = { authViewModel.login(username, password) },
                modifier = Modifier.fillMaxWidth(),
                // 正在加载时，按钮不可点击
                enabled = !uiState.isLoading
            ) {
                Text("登录")
            }

            TextButton(
                // 点击时调用ViewModel的register方法
                onClick = { authViewModel.register(username, password) },
                modifier = Modifier.fillMaxWidth(),
                enabled = !uiState.isLoading
            ) {
                Text("还没有账户？点此注册")
            }
        }

        // 如果正在加载，显示一个半透明的加载覆盖层
        if (uiState.isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
    }
}