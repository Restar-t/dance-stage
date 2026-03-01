package com.example.prismstage.capture

import android.net.Uri
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.prismstage.data.model.AnalysisResult // ✅ 确保导入了正确的模型
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CaptureUiState(
    val isUploading: Boolean = false,
    val analysisResult: AnalysisResult? = null,
    val errorMessage: String? = null
)

class CaptureViewModel : ViewModel() {
    private val _uiState = MutableStateFlow(CaptureUiState())
    val uiState = _uiState.asStateFlow()

    fun startVideoAnalysis(videoUri: Uri) {
        viewModelScope.launch {
            _uiState.update { it.copy(isUploading = true, errorMessage = null, analysisResult = null) }
            try {
                delay(3000)
                val result = AnalysisResult(
                    score = 85,
                    feedback = "动作流畅，但在第3秒的膝关节角度可以更大一些。",
                    skeletonImageUrl = "https://example.com/skeleton.gif"
                )
                _uiState.update { it.copy(isUploading = false, analysisResult = result) }
            } catch (e: Exception) {
                _uiState.update { it.copy(isUploading = false, errorMessage = "分析失败: ${e.message}") }
            }
        }
    }
}