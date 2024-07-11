package com.example.desafio.helper

import android.content.Context
import com.google.android.exoplayer2.ExoPlayer
import com.google.android.exoplayer2.MediaItem

object ExoPlayerHelper {
    fun createExoPlayer(context: Context, videoUrl: String): ExoPlayer {
        val player = ExoPlayer.Builder(context).build()
        val mediaItem = MediaItem.fromUri(videoUrl)
        player.setMediaItem(mediaItem)
        player.prepare()
        return player
    }
}