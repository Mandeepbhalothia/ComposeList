package com.learn.composelist.ui.util

import android.util.Log
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.navArgument
import coil.annotation.ExperimentalCoilApi
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.learn.composelist.ui.composable.DetailsScreen
import com.learn.composelist.ui.composable.HomeScreen

@ExperimentalAnimationApi
@ExperimentalCoilApi
@ExperimentalMaterialApi
@Composable
fun Navigation() {

    val navController = rememberAnimatedNavController()

    AnimatedNavHost(navController = navController, startDestination = Screens.HomeScreen.route) {

        composable(route = Screens.HomeScreen.route,
            exitTransition = { _, _ ->
                slideOutHorizontally(
                    /*targetOffsetX = {-300},*/ // We can define any targetOffset else it will take half of screen by default
                    animationSpec = tween(AnimationDuration)
                ) + fadeOut(animationSpec = tween(AnimationDuration))
            },
            popEnterTransition = { _, _ ->
                slideInHorizontally(
                    animationSpec = tween(AnimationDuration)
                ) + fadeIn(animationSpec = tween(AnimationDuration))
            }) {
            HomeScreen(navController = navController)
        }

        composable(route = Screens.ItemDetailsScreen.route,
            enterTransition = { _, _ ->
                slideInHorizontally(
                    initialOffsetX = { 300 },
                    animationSpec = tween(AnimationDuration)
                ) + fadeIn(animationSpec = tween(AnimationDuration))
            },
            popExitTransition = { _, _ ->
                slideOutHorizontally(
                    targetOffsetX = { 300 },
                    animationSpec = tween(AnimationDuration)
                ) + fadeOut(animationSpec = tween(AnimationDuration))
            },
            arguments = listOf(
                navArgument("id") {
                    type = NavType.IntType
                }
            )) {
            val id = it.arguments?.getInt("id") ?: -1
            Log.d("Navigation", "id is: $id")
            DetailsScreen(navController = navController, id = id)
        }

    }

}

const val AnimationDuration = 500
