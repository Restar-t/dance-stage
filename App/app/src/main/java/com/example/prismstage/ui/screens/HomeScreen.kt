package com.example.prismstage.ui.screens

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Analytics
import androidx.compose.material.icons.filled.WorkspacePremium
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.graphics.Color

@Composable
fun HomeScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Column {
                Text("欢迎回来, Dapper-YF!", fontSize = 24.sp, color = MaterialTheme.colorScheme.onBackground)
                Text("今天的状态如何？准备好发光了吗？", fontSize = 16.sp, color = Color.Gray)
            }
        }
        item {
            InfoCard(
                icon = Icons.Default.Analytics,
                title = "上次练习回顾",
                content = "Popping基础动作 | 综合得分: 85分"
            )
        }
        item {
            InfoCard(
                icon = Icons.Default.WorkspacePremium,
                title = "本周成就",
                content = "已完成3次高强度训练，解锁“汗水舞者”徽章！"
            )
        }
        item {
            Button(onClick = { /* TODO */ }, modifier = Modifier.fillMaxWidth()) {
                Text("开始新的练习")
            }
        }
    }
}

@Composable
fun InfoCard(icon: ImageVector, title: String, content: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(icon, contentDescription = title, modifier = Modifier.size(40.dp))
            Spacer(modifier = Modifier.width(16.dp))
            Column {
                Text(title, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Text(content, style = MaterialTheme.typography.bodyMedium, color = Color.Gray)
            }
        }
    }
}