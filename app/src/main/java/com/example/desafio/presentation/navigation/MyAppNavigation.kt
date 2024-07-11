package com.example.desafio.presentation.navigation

sealed class MyAppNavigation(val route: String) {
    object Home: MyAppNavigation(route = "home")
    object Details : MyAppNavigation("details/{itemId}") {
        fun createRoute(itemId: String) = "details/$itemId"
    }
}