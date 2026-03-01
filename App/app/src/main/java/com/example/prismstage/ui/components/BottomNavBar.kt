package com.example.prismstage.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.VideoLibrary
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavBarScreen(val route: String, val title: String, val icon: ImageVector) {
    object Home : NavBarScreen("home", "首页", Icons.Default.Home)
    object Library : NavBarScreen("library", "舞谱库", Icons.Default.VideoLibrary)
    object Profile : NavBarScreen("profile", "我的", Icons.Default.Person)
}

@Composable
fun BottomNavBar(
    currentRoute: String?,
    onNavigate: (String) -> Unit
) {
    val items = listOf(
        NavBarScreen.Home,
        NavBarScreen.Library,
        NavBarScreen.Profile
    )
    NavigationBar {
        items.forEach { screen ->
            NavigationBarItem(
                icon = { Icon(screen.icon, contentDescription = screen.title) },
                label = { Text(screen.title) },
                selected = currentRoute == screen.route,
                onClick = { onNavigate(screen.route) }
            )
        }
    }
}