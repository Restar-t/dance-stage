package com.example.prismstage.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prismstage.ui.theme.MediumBlue // ✅ 确保导入了颜色

@Composable
fun HomeScreen() {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("欢迎回来, Dapper-YF!", fontSize = 24.sp, modifier = Modifier.padding(bottom = 8.dp))
        Text("今天的状态如何？准备好发光了吗？", fontSize = 16.sp, color = Color.Gray)
        Spacer(modifier = Modifier.height(32.dp))
        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
            colors = CardDefaults.cardColors(containerColor = MediumBlue)
        ) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("上次练习回顾", fontSize = 20.sp)
                Spacer(modifier = Modifier.height(8.dp))
                Text("Popping基础动作 | 得分: 85")
            }
        }
    }
}