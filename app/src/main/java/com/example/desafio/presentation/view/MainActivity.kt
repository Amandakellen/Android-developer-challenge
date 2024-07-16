package com.example.desafio.presentation.view

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.core.content.ContextCompat
import com.example.desafio.presentation.view.compose.VideoPlayerScreen
import com.example.desafio.presentation.view.viewmodel.VideoPlayerViewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationCallback
import com.google.android.gms.location.LocationRequest
import com.google.android.gms.location.LocationResult
import com.google.android.gms.location.LocationServices
import org.koin.android.ext.android.inject

class MainActivity : ComponentActivity() {
    private val videoPlayerViewModel: VideoPlayerViewModel by inject()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var latitude: Double = 0.00
    private var longitude: Double = 0.00

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted: Boolean ->
        if (isGranted) {
            requestLocationPermission()
        }
    }

    private lateinit var locationCallback: LocationCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult) {
                for (location in locationResult.locations) {
                    latitude = location.latitude
                    longitude = location.longitude
                    videoPlayerViewModel.updateLocation(latitude, longitude)
                }
            }
        }

        setContent {
            MaterialTheme {
                val videoUrl by videoPlayerViewModel.videoUrl.collectAsState()
                val isPlaying by videoPlayerViewModel.isPlaying.collectAsState()
                val currentPlayerPosition by videoPlayerViewModel.currentPlayerPosition

                VideoPlayerScreen(
                    videoUrl = videoUrl,
                    isPlaying = isPlaying,
                    currentPlayerPosition = currentPlayerPosition,
                    onPlay = { videoPlayerViewModel.setPlaying(true, latitude, longitude) },
                    onPause = { videoPlayerViewModel.setPlaying(false, latitude, longitude) },
                    onReset = { videoPlayerViewModel.resetPlayer() },
                )
            }
        }

        requestLocationPermission()
    }

    private fun requestLocationPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this, Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED -> {
                val locationRequest = LocationRequest.create().apply {
                    interval = 10000 // 10 segundos
                    fastestInterval = 5000 // 5 segundos
                    priority = LocationRequest.PRIORITY_HIGH_ACCURACY
                }

                fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
            }

            else -> {
                // Solicitar a permissão
                requestPermissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
            }
        }
    }


    override fun onStop() {
        super.onStop()
        // Parar as atualizações de localização para economizar bateria
        fusedLocationClient.removeLocationUpdates(locationCallback)
    }
}
