package com.example.nammahomestay

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.nammahomestay.screens.*
import com.example.nammahomestay.ui.theme.NammaHomeStayTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NammaHomeStayTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = "explore",
                    enterTransition = {
                        fadeIn(tween(400, easing = EaseInOutCubic)) +
                        slideInHorizontally(tween(400, easing = EaseInOutCubic)) { it / 6 }
                    },
                    exitTransition = {
                        fadeOut(tween(300, easing = EaseInOutCubic)) +
                        slideOutHorizontally(tween(300, easing = EaseInOutCubic)) { -it / 6 }
                    },
                    popEnterTransition = {
                        fadeIn(tween(400, easing = EaseInOutCubic)) +
                        slideInHorizontally(tween(400, easing = EaseInOutCubic)) { -it / 6 }
                    },
                    popExitTransition = {
                        fadeOut(tween(300, easing = EaseInOutCubic)) +
                        slideOutHorizontally(tween(300, easing = EaseInOutCubic)) { it / 6 }
                    }
                ) {
                    composable("explore") { HomeScreen(navController) }
                    composable("search") { SearchScreen(navController) }
                    composable("profile") { ProfileScreen(navController) }
                    composable("detail") { DetailScreen(navController) }
                    composable("booking") { BookingScreen(navController) }
                }
            }
        }
    }
}