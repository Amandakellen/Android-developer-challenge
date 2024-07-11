package com.example.desafio.presentation.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.fragment.app.Fragment
import com.example.desafio.presentation.view.compose.VideoPlayerScreen
import com.example.desafio.presentation.view.viewmodel.VideoPlayerViewModel
import org.koin.android.ext.android.inject

class VideoPlayerFragment : Fragment() {

    private val videoPlayerViewModel: VideoPlayerViewModel by inject()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {

                val videoUrl by videoPlayerViewModel.videoUrl.collectAsState()
                val isPlaying by videoPlayerViewModel.isPlaying.collectAsState()
                val currentPlayerPosition by videoPlayerViewModel.currentPlayerPosition

//                VideoPlayerScreen(
//                    videoUrl = videoUrl,
//                    isPlaying = isPlaying,
//                    currentPlayerPosition = currentPlayerPosition,
//                    onPlay = { videoPlayerViewModel.setPlaying(true) },
//                    onPause = { videoPlayerViewModel.setPlaying(false) },
//                    onReset = { videoPlayerViewModel.resetPlayer() }
//                )
            }
        }
    }
}
