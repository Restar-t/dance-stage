package com.example.prismstage.ui.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.*
import com.example.prismstage.ui.components.BottomNavBar
import com.example.prismstage.ui.components.NavBarScreen
import com.example.prismstage.ui.screens.HomeScreen
import com.example.prismstage.ui.screens.LibraryScreen
// 确保导入路径正确
import com.example.prismstage.ui.screens.ProfileScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onLogout: () -> Unit // 从MainActivity接收退出回调
) {
    val navController = rememberNavController()
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Scaffold(
        bottomBar = {
            BottomNavBar(currentRoute = currentRoute) { route ->
                navController.navigate(route) {
                    launchSingleTop = true
                    restoreState = true
                }
            }
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* TODO: 替换为CaptureScreen的导航 */ }) {
                Icon(Icons.Default.Add, contentDescription = "开始分析")
            }
        },
        floatingActionButtonPosition = FabPosition.Center
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            NavHost(navController = navController, startDestination = NavBarScreen.Home.route) {
                composable(NavBarScreen.Home.route) { HomeScreen() }
                composable(NavBarScreen.Library.route) { LibraryScreen() }
                // 把回调传递给ProfileScreen
                composable(NavBarScreen.Profile.route) { ProfileScreen(onLogout = onLogout) }
            }
        }
    }
}