package com.example.desafio.presentation.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.example.desafio.presentation.di.MyApplication
import com.example.desafio.presentation.view.compose.VideoPlayerScreen
import com.example.desafio.presentation.view.viewmodel.VideoPlayerViewModel
import org.koin.android.ext.android.inject


class MainActivity : ComponentActivity() {
    private val videoPlayerViewModel: VideoPlayerViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                val videoUrl by videoPlayerViewModel.videoUrl.collectAsState()
                val isPlaying by videoPlayerViewModel.isPlaying.collectAsState()
                val currentPlayerPosition by videoPlayerViewModel.currentPlayerPosition

                 VideoPlayerScreen(
                    videoUrl = videoUrl,
                    isPlaying = isPlaying,
                    currentPlayerPosition = currentPlayerPosition,
                    onPlay = { videoPlayerViewModel.setPlaying(true) },
                    onPause = { videoPlayerViewModel.setPlaying(false) },
                    onReset = { videoPlayerViewModel.resetPlayer() }
                )
            }
        }
    }

}

