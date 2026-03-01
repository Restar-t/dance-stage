package com.example.prismstage.capture

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.prismstage.data.model.AnalysisResult // ✅ 确保导入了正确的模型

@Composable
fun CaptureScreen(
    captureViewModel: CaptureViewModel = viewModel()
) {
    val uiState by captureViewModel.uiState.collectAsState()

    val selectVideoLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent(),
        onResult = { uri: Uri? ->
            uri?.let {
                captureViewModel.startVideoAnalysis(it)
            }
        }
    )

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        when {
            uiState.isUploading -> {
                CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "正在分析您的动作，请稍候...",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MaterialTheme.colorScheme.onBackground
                )
            }
            uiState.analysisResult != null -> {
                AnalysisResultView(result = uiState.analysisResult!!)
            }
            else -> {
                InitialView(
                    onSelectVideoClick = { selectVideoLauncher.launch("video/*") },
                    onRecordVideoClick = { /* TODO: 跳转到实时录制界面 */ }
                )
                uiState.errorMessage?.let {
                    Spacer(modifier = Modifier.height(16.dp))
                    Text(text = it, color = MaterialTheme.colorScheme.error)
                }
            }
        }
    }
}

@Composable
private fun InitialView(onSelectVideoClick: () -> Unit, onRecordVideoClick: () -> Unit) {
    Text(
        text = "开始你的舞蹈分析",
        style = MaterialTheme.typography.headlineMedium,
        color = MaterialTheme.colorScheme.onBackground
    )
    Spacer(modifier = Modifier.height(32.dp))
    Button(onClick = onSelectVideoClick, modifier = Modifier.fillMaxWidth()) {
        Text("从相册上传视频")
    }
    Spacer(modifier = Modifier.height(16.dp))
    OutlinedButton(onClick = onRecordVideoClick, modifier = Modifier.fillMaxWidth()) {
        Text("实时录制动作")
    }
}

@Composable
private fun AnalysisResultView(result: AnalysisResult) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text("分析报告", style = MaterialTheme.typography.headlineSmall)
        Spacer(Modifier.height(16.dp))
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(modifier = Modifier.padding(16.dp)) {
                Text("综合得分: ${result.score}", style = MaterialTheme.typography.titleLarge)
                Spacer(Modifier.height(8.dp))
                Text("AI 建议: ${result.feedback}", style = MaterialTheme.typography.bodyMedium)
            }
        }
        Spacer(Modifier.height(16.dp))
        AsyncImage(
            model = result.skeletonImageUrl,
            contentDescription = "动作骨骼图",
            modifier = Modifier.fillMaxWidth().height(200.dp)
        )
    }
}