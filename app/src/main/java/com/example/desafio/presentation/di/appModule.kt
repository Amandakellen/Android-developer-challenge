package com.example.desafio.presentation.di

import com.example.desafio.domain.repository.VideoURLRepository
import com.example.desafio.presentation.view.viewmodel.VideoPlayerViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val myModule = module {
    single { VideoURLRepository() }
    viewModel {
        VideoPlayerViewModel(repository = get())
    }
}