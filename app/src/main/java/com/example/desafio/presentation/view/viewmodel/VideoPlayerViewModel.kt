package com.example.desafio.presentation.view.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.desafio.domain.repository.VideoURLRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VideoPlayerViewModel(private val repository: VideoURLRepository) : ViewModel() {

    private val _videoUrl = MutableStateFlow<String?>(null)
    private val _currentPlayerPosition = mutableStateOf(0L)
    private val _currentLatitude = MutableLiveData<Double>()
    private val _currentLongitude = MutableLiveData<Double>()
    private val _initLatitude = MutableLiveData<Double>()
    private val _initLongitude = MutableLiveData<Double>()
    private val _isPlaying = MutableStateFlow(false)

    val currentPlayerPosition: State<Long> = _currentPlayerPosition
    val videoUrl: StateFlow<String?> get() = _videoUrl
    val isPlaying: StateFlow<Boolean> = _isPlaying

    init {
        getVideoUrl()
    }

    fun getVideoUrl() {
        _videoUrl.value = repository.getVideoURL()
    }

    fun setPlaying(playing: Boolean, latitude: Double, longitude: Double) {
        _initLatitude.value = latitude
        _initLongitude.value = longitude
        _isPlaying.value = playing

    }

    fun updateLocation(latitude: Double, longitude: Double) {
        _currentLatitude.value = latitude
        _currentLongitude.value = longitude
    }

    fun resetPlayer() {
        _currentPlayerPosition.value = 0L
    }
}