package com.example.prismstage

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.*
import com.example.prismstage.ui.navigation.MainScreen
import com.example.prismstage.ui.screens.LoginScreen
import com.example.prismstage.ui.theme.PrismStageTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PrismStageTheme {
                // 用一个可变状态来控制显示哪个界面
                var isLoggedIn by remember { mutableStateOf(checkLoginStatus()) }

                if (isLoggedIn) {
                    // 如果已登录，显示主界面
                    MainScreen()
                } else {
                    // 如果未登录，显示登录界面
                    LoginScreen(
                        onLoginSuccess = {
                            // 登录成功后，更新状态并保存
                            isLoggedIn = true
                            saveLoginStatus(true)
                        }
                    )
                }
            }
        }
    }

    // 检查登录状态
    private fun checkLoginStatus(): Boolean {
        val sharedPref = getSharedPreferences("prism_stage_prefs", MODE_PRIVATE)
        return sharedPref.getBoolean("is_logged_in", false)
    }

    // 保存登录状态
    private fun saveLoginStatus(isLoggedIn: Boolean) {
        val sharedPref = getSharedPreferences("prism_stage_prefs", MODE_PRIVATE)
        with(sharedPref.edit()) {
            putBoolean("is_logged_in", isLoggedIn)
            apply()
        }
    }
}