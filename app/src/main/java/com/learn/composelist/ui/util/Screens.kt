package com.learn.composelist.ui.util

sealed class Screens(val route: String) {
    object HomeScreen : Screens("home_screen")
    object ItemDetailsScreen : Screens("item_details_screen/{id}")
}
