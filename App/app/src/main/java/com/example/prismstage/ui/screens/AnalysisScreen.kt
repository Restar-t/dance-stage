package com.dapperyf.prismstage.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp

@Composable
fun AnalysisScreen() {
    // 这个屏幕在我们的设计里是通过一个浮动按钮触发的，而不是一个常驻页面
    // 所以这里只放一个占位符。实际逻辑会更复杂
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(
            "这里是分析功能\n通过点击中间的浮动按钮触发",
            fontSize = 20.sp,
            textAlign = TextAlign.Center
        )
    }
}