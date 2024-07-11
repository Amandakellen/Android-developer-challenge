package com.example.desafio.presentation.navigation

sealed class Screen(val route: String) {
    object Home: Screen(route = "home")
    object Details : Screen("details/{itemId}") {
        fun createRoute(itemId: String) = "details/$itemId"
    }
}