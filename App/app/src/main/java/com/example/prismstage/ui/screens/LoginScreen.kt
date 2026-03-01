package com.example.prismstage.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp

@Composable
fun LoginScreen(
    onLoginSuccess: () -> Unit // 登录成功后的回调函数
) {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    // TODO: 后续我们会添加ViewModel来处理真正的登录逻辑

    Column(
        modifier = Modifier.fillMaxSize().padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("欢迎来到棱镜舞台", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(32.dp))

        OutlinedTextField(
            value = username,
            onValueChange = { username = it },
            label = { Text("用户名") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = password,
            onValueChange = { password = it },
            label = { Text("密码") },
            modifier = Modifier.fillMaxWidth(),
            visualTransformation = PasswordVisualTransformation() // 让密码显示为星号
        )
        Spacer(modifier = Modifier.height(32.dp))

        Button(
            onClick = {
                // TODO: 在这里调用ViewModel的登录方法
                // 现在，我们先做一个简单的模拟：只要点击按钮就认为登录成功
                onLoginSuccess()
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("登录")
        }

        TextButton(onClick = { /* TODO: 跳转到注册页 */ }) {
            Text("还没有账户？点此注册")
        }
    }
}