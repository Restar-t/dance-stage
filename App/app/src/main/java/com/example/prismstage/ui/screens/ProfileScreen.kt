package com.dapperyf.prismstage.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp
import androidx.compose.ui.unit.dp

@Composable
fun ProfileScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("我的主页", fontSize = 24.sp)
        Spacer(modifier = Modifier.height(16.dp))
        Text("总练习时长: 42小时")
        Text("平均分: 81分")
    }
}

// 舞谱库的占位屏幕，你可以后续补充
@Composable
fun LibraryScreen() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("舞谱库", fontSize = 24.sp)
    }
}