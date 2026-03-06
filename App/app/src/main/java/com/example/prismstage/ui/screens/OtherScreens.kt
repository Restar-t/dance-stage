package com.example.prismstage.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.filled.History
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.prismstage.R // 确保你的项目中有名为 ic_launcher_foreground 的资源
import androidx.compose.ui.graphics.Color

// =====================================================================
//                            舞谱库屏幕
// =====================================================================

// 舞谱库的数据模型 (用于模拟)
data class DanceRoutine(
    val id: Int,
    val title: String,
    val style: String,
    val difficulty: String,
    val imageUrl: String // 在实际项目中，这会是一个网络URL
)

// 模拟的舞谱数据
val mockRoutines = listOf(
    DanceRoutine(1, "Popping基础律动", "Popping", "入门", "https://example.com/popping.jpg"),
    DanceRoutine(2, "Wave & Flow 组合", "Waving", "进阶", "https://example.com/wave.jpg"),
    DanceRoutine(3, "Locking经典连招", "Locking", "中级", "https://example.com/locking.jpg"),
    DanceRoutine(4, "Hiphop律动入门", "Hiphop", "入门", "https://example.com/hiphop.jpg")
)

@Composable
fun LibraryScreen() {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        item {
            Text(
                "舞谱库",
                style = MaterialTheme.typography.headlineLarge,
                modifier = Modifier.padding(bottom = 8.dp)
            )
        }
        items(mockRoutines) { routine ->
            DanceRoutineCard(routine = routine)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DanceRoutineCard(routine: DanceRoutine) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        onClick = { /* TODO: 跳转到舞谱详情页 */ }
    ) {
        Row(
            modifier = Modifier.height(IntrinsicSize.Min), // 让Row内的元素可以撑满高度
            verticalAlignment = Alignment.CenterVertically
        ) {
            // 左侧图片 (用占位符代替)
            Box(
                modifier = Modifier
                    .width(100.dp)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp)),
                contentAlignment = Alignment.Center
            ) {
                // 在真实App中，这里会用Coil从routine.imageUrl加载网络图片
                Image(
                    painter = painterResource(id = R.drawable.ic_launcher_foreground), // 使用一个本地资源作为占位符
                    contentDescription = routine.title,
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.fillMaxSize()
                )
            }

            // 右侧信息
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 12.dp)
                    .weight(1f) // 占据剩余空间
            ) {
                Text(routine.title, style = MaterialTheme.typography.titleLarge, fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.height(4.dp))
                Text("风格: ${routine.style}", style = MaterialTheme.typography.bodyMedium)
                Text("难度: ${routine.difficulty}", style = MaterialTheme.typography.bodyMedium)
            }
            Icon(
                imageVector = Icons.Default.ChevronRight,
                contentDescription = "进入",
                modifier = Modifier.padding(end = 16.dp)
            )
        }
    }
}


// =====================================================================
//                            “我的”主页屏幕
// =====================================================================

@Composable
fun ProfileScreen(
    onLogout: () -> Unit // 退出登录的回调函数
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        // 顶部个人信息区域
        ProfileHeader()
        Spacer(modifier = Modifier.height(24.dp))

        // 统计数据
        StatsCard()
        Spacer(modifier = Modifier.height(24.dp))

        // 功能列表
        ActionList()
        Spacer(modifier = Modifier.weight(1f)) // 占位符，将按钮推到底部

        // 退出登录按钮
        Button(
            onClick = onLogout,
            modifier = Modifier.fillMaxWidth(),
            colors = ButtonDefaults.buttonColors(containerColor = MaterialTheme.colorScheme.errorContainer)
        ) {
            Text("退出登录", color = MaterialTheme.colorScheme.onErrorContainer)
        }
    }
}

@Composable
fun ProfileHeader() {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_launcher_foreground), // 使用一个占位图作为头像
            contentDescription = "用户头像",
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(
                "Dapper-YF", // 模拟的用户名
                style = MaterialTheme.typography.headlineMedium,
                fontWeight = FontWeight.Bold
            )
            Text(
                "今天也要坚持练习哦！",
                style = MaterialTheme.typography.bodyMedium,
                color = Color.Gray
            )
        }
    }
}

@Composable
fun StatsCard() {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            StatItem(value = "42", label = "总练习(小时)")
            StatItem(value = "81", label = "平均得分")
            StatItem(value = "15", label = "已学舞谱")
        }
    }
}

@Composable
fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, style = MaterialTheme.typography.headlineSmall, fontWeight = FontWeight.Bold)
        Text(label, style = MaterialTheme.typography.bodySmall, color = Color.Gray)
    }
}

@Composable
fun ActionList() {
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        ActionListItem(icon = Icons.Default.History, text = "练习历史")
        ActionListItem(icon = Icons.Default.FavoriteBorder, text = "我的收藏")
        ActionListItem(icon = Icons.Default.Settings, text = "设置")
    }
}

@Composable
fun ActionListItem(icon: ImageVector, text: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { /* TODO: 跳转到对应页面 */ }
            .padding(vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = text, modifier = Modifier.size(24.dp))
        Spacer(modifier = Modifier.width(16.dp))
        Text(text, style = MaterialTheme.typography.bodyLarge, modifier = Modifier.weight(1f))
        Icon(imageVector = Icons.Default.ChevronRight, contentDescription = "进入")
    }
}