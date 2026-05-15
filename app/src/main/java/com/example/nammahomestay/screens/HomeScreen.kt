package com.example.nammahomestay.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.nammahomestay.components.BottomNav
import com.example.nammahomestay.components.StayCard
import com.example.nammahomestay.ui.theme.*

@Composable
fun HomeScreen(navController: NavController) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Scaffold(
        bottomBar = { BottomNav(navController) },
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF050505), Color(0xFF0E0E0E), Color(0xFF181818))
                    )
                )
        ) {
            LazyColumn(
                contentPadding = PaddingValues(
                    start = 20.dp, end = 20.dp,
                    top = 28.dp, bottom = padding.calculateBottomPadding() + 8.dp
                )
            ) {
                item {
                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn(tween(600)) + slideInVertically(tween(600)) { -30 }
                    ) {
                        Column {
                            Text("Good Evening 🌿", color = GrayText, style = MaterialTheme.typography.bodyMedium)
                            Spacer(Modifier.height(6.dp))
                            Text("Namma HomeStay", style = MaterialTheme.typography.headlineLarge, color = White)
                            Spacer(Modifier.height(4.dp))
                            Text("Luxury rural escapes across South India", color = GrayText)
                            Spacer(Modifier.height(20.dp))
                            Surface(
                                shape = RoundedCornerShape(12.dp),
                                color = EmeraldGreen.copy(alpha = 0.10f),
                                modifier = Modifier.padding(bottom = 24.dp)
                            ) {
                                Text(
                                    "✦  Top picks for you",
                                    color = EmeraldGreen,
                                    modifier = Modifier.padding(horizontal = 14.dp, vertical = 8.dp),
                                    style = MaterialTheme.typography.labelSmall
                                )
                            }
                        }
                    }
                }

                itemsIndexed(allStays) { index, stay ->
                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn(tween(400 + index * 80)) +
                                slideInVertically(tween(400 + index * 80, easing = EaseOutCubic)) { 60 }
                    ) {
                        StayCard(stay = stay, onExploreClick = { navController.navigate("detail") })
                    }
                }
            }
        }
    }
}