import android.content.Context
import android.net.Uri
import com.example.prismstage.data.model.AnalysisResult
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

class CaptureRepository(
    private val apiService: ApiService,
    private val context: Context
) {
    suspend fun uploadAndAnalyze(videoUri: Uri): AnalysisResult {
        // 将视频文件Uri转换为可上传的格式
        val videoFilePart = context.contentResolver.openInputStream(videoUri)?.use { inputStream ->
            val fileBytes = inputStream.readBytes()
            val requestBody = fileBytes.toRequestBody("video/mp4".toMediaTypeOrNull())
            MultipartBody.Part.createFormData("video", "user_dance.mp4", requestBody)
        } ?: throw IllegalArgumentException("无法读取视频文件")

        // 调用API
        return apiService.analyzeDance(videoFilePart)
    }
}