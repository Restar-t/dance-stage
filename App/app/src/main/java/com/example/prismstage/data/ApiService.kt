import com.example.prismstage.data.model.AnalysisResult
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

// 定义与后端通信的API接口
interface ApiService {
    @Multipart
    @POST("/api/analyze") // 假设这是后端接收视频的API端点
    suspend fun analyzeDance(
        @Part video: MultipartBody.Part
    ): AnalysisResult // 返回结果
}