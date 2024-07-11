package com.example.desafio.presentation.view.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.desafio.domain.repository.VideoURLRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VideoPlayerViewModel(private val repository: VideoURLRepository) : ViewModel() {

    private val _videoUrl = MutableStateFlow<String?>(null)
    private val _currentPlayerPosition = mutableStateOf(0L)
    val currentPlayerPosition: State<Long> = _currentPlayerPosition
    val videoUrl: StateFlow<String?> get() = _videoUrl

    private val _isPlaying = MutableStateFlow(false)
    val isPlaying: StateFlow<Boolean> = _isPlaying

    init {
        getVideoUrl()
    }


    fun getVideoUrl() {
        _videoUrl.value = repository.getVideoURL()
    }

    fun setPlaying(playing: Boolean) {
        _isPlaying.value = playing

    }

    fun updatePlayerPosition(position: Long) {
        _currentPlayerPosition.value = position
    }

    fun resetPlayer() {
        _currentPlayerPosition.value = 0L
    }
}