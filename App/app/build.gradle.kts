// 这是文件的最顶部，首先声明插件
plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    // ✅ 1. 新增：在这里添加 Compose 编译器插件
    id("org.jetbrains.kotlin.plugin.compose")
}

// 接着是 android { ... } 配置块
android {
    namespace = "com.example.prismstage" // 确保这里是你自己的包名
    compileSdk = 34 // 编译用的SDK版本

    defaultConfig {
        applicationId = "com.example.prismstage" // 同样，确保是你的包名
        minSdk = 24 // 最低支持的安卓版本
        targetSdk = 34 // 目标SDK版本
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    // 这个配置块对于 Jetpack Compose 至关重要
    buildFeatures {
        compose = true
    }

    // ✅ 2. 删除：旧的 composeOptions 代码块已被完全删除

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

// 最后，是 dependencies { ... } 依赖块，它和 android { ... } 是同级的
dependencies {

    // Android 核心库
    implementation("androidx.core:core-ktx:1.12.0")
    implementation("androidx.lifecycle:lifecycle-runtime-ktx:2.7.0")
    implementation("androidx.activity:activity-compose:1.8.2")

    // Jetpack Compose - 用于构建UI
    implementation(platform("androidx.compose:compose-bom:2024.02.01"))
    implementation("androidx.compose.ui:ui")
    implementation("androidx.compose.ui:ui-graphics")
    implementation("androidx.compose.ui:ui-tooling-preview")
    implementation("androidx.compose.material3:material3") // Material Design 3 UI组件
    implementation("androidx.compose.material:material-icons-extended") // 引入更多图标

    // ViewModel - MVVM架构的核心，用于管理UI状态
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.7.0")

    // Navigation - 用于在App的不同屏幕之间导航
    implementation("androidx.navigation:navigation-compose:2.7.7")

    // Retrofit & Gson - 用于和你的后端服务器进行网络通信
    implementation("com.squareup.retrofit2:retrofit:2.9.0")
    implementation("com.squareup.retrofit2:converter-gson:2.9.0")

    // ✅ 新增：日志拦截器
    implementation("com.squareup.okhttp3:logging-interceptor:4.9.3")

    // Coil - 用于从网络加载和显示图片（比如后端返回的骨骼图）
    implementation("io.coil-kt:coil-compose:2.6.0")

    // CameraX - 用于实现应用内视频录制功能
    val cameraxVersion = "1.3.2"
    implementation("androidx.camera:camera-core:${cameraxVersion}")
    implementation("androidx.camera:camera-camera2:${cameraxVersion}")
    implementation("androidx.camera:camera-lifecycle:${cameraxVersion}")
    implementation("androidx.camera:camera-video:${cameraxVersion}")
    implementation("androidx.camera:camera-view:${cameraxVersion}")

    // 测试相关的库（通常默认就有）
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    androidTestImplementation(platform("androidx.compose:compose-bom:2024.02.01"))
    androidTestImplementation("androidx.compose.ui:ui-test-junit4")
    debugImplementation("androidx.compose.ui:ui-tooling")
    debugImplementation("androidx.compose.ui:ui-test-manifest")
}