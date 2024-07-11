package com.example.desafio.domain.repository


private const val VIDEO_URL =
    "https://commondatastorage.googleapis.com/gtv-videos-bucket/sample/WeAreGoingOnBullrun.mp4"

class VideoURLRepository {
    fun getVideoURL(): String = VIDEO_URL

}