package com.learn.composelist.ui.util

sealed class Screens(val route: String) {
    object HomeScreen : Screens(Constants.ScreenEndPoints.HomeScreen_Root)
    object ItemDetailsScreen : Screens("${Constants.ScreenEndPoints.HomeScreen_Root}/{${Constants.ScreenEndPoints.DetailsScreen_ID}}")
}
