package com.dapperyf.prismstage.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp

/**
 * 个人中心页面（保留原有样式，新增退出登录功能）
 * @param onLogout 退出登录的回调函数，点击退出按钮时触发
 */
@Composable
fun ProfileScreen(
    onLogout: () -> Unit // 新增：接收退出登录回调参数
) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("我的主页", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("总练习时长: 42小时")
        Text("平均分: 81分")

        // 新增：退出登录按钮，与原有内容保持视觉间距
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = onLogout, // 绑定退出登录回调
            modifier = Modifier.padding(top = 16.dp)
        ) {
            Text("退出登录", fontSize = 16.sp)
        }
    }
}

// 舞谱库的占位屏幕，你可以后续补充
@Composable
fun LibraryScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("舞谱库", fontSize = 24.sp)
    }
}