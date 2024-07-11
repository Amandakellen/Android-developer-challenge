package com.example.desafio.presentation.view.compose

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.desafio.presentation.navigation.Screen
import com.example.desafio.presentation.view.viewmodel.VideoPlayerViewModel
import org.koin.android.ext.android.inject
import org.koin.androidx.compose.koinViewModel
import org.koin.java.KoinJavaComponent.inject

@Composable
fun MyApp() {
    val navController = rememberNavController()
    Scaffold{
        NavHost(
            navController = navController,
            startDestination = Screen.Home.route,
            modifier = Modifier.padding(it)
        ) {
            composable(Screen.Home.route) {
                HomeScreen(navController)
            }
        }
    }

}

@Composable
fun HomeScreen(navController: NavHostController){
    val videoPlayerViewModel: VideoPlayerViewModel = koinViewModel()

    val videoUrl by videoPlayerViewModel.videoUrl.collectAsState()
    val isPlaying by videoPlayerViewModel.isPlaying.collectAsState()
    val currentPlayerPosition by videoPlayerViewModel.currentPlayerPosition

    VideoPlayerScreen(
        videoUrl = videoUrl,
        isPlaying = isPlaying,
        currentPlayerPosition = currentPlayerPosition,
        onPlay = { videoPlayerViewModel.setPlaying(true) },
        onPause = { videoPlayerViewModel.setPlaying(false) },
        onReset = { videoPlayerViewModel.resetPlayer() },
    )
}