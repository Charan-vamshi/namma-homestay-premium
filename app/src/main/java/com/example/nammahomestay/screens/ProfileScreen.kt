package com.example.nammahomestay.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nammahomestay.components.BottomNav
import com.example.nammahomestay.ui.theme.*

@Composable
fun ProfileScreen(navController: NavController) {
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
                        listOf(Color(0xFF050505), Color(0xFF0D0D0D), Color(0xFF181818))
                    )
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(
                        start = 20.dp, end = 20.dp,
                        top = 36.dp, bottom = padding.calculateBottomPadding() + 16.dp
                    ),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(tween(600)) + scaleIn(tween(600, easing = EaseOutCubic), initialScale = 0.8f)
                ) {
                    Box(
                        modifier = Modifier
                            .size(96.dp)
                            .clip(CircleShape)
                            .background(
                                Brush.radialGradient(
                                    listOf(EmeraldGreen.copy(alpha = 0.25f), Color(0xFF1C1C1C))
                                )
                            )
                            .border(2.dp, EmeraldGreen.copy(alpha = 0.5f), CircleShape),
                        contentAlignment = Alignment.Center
                    ) {
                        Text("S", fontSize = 36.sp, color = EmeraldGreen, fontWeight = FontWeight.Bold)
                    }
                }

                Spacer(Modifier.height(16.dp))

                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(tween(700)) + slideInVertically(tween(700)) { 20 }
                ) {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text("Sai Charan", style = MaterialTheme.typography.headlineSmall, color = White)
                        Spacer(Modifier.height(4.dp))
                        Text("saicharan@email.com", color = GrayText, style = MaterialTheme.typography.bodyMedium)
                    }
                }

                Spacer(Modifier.height(28.dp))

                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(tween(800)) + slideInVertically(tween(800)) { 30 }
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(20.dp))
                            .background(SurfaceBlack)
                            .padding(vertical = 20.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        StatItem("3", "Bookings")
                        HorizontalDivider(modifier = Modifier.width(1.dp).height(40.dp), color = GrayText.copy(alpha = 0.2f))
                        StatItem("2", "Wishlist")
                        HorizontalDivider(modifier = Modifier.width(1.dp).height(40.dp), color = GrayText.copy(alpha = 0.2f))
                        StatItem("4.9", "Rating")
                    }
                }

                Spacer(Modifier.height(28.dp))

                val menuItems = listOf(
                    Triple(Icons.Filled.BookOnline, "My Bookings", "View all your stays"),
                    Triple(Icons.Filled.Favorite, "Wishlist", "Saved properties"),
                    Triple(Icons.Filled.Notifications, "Notifications", "Manage alerts"),
                    Triple(Icons.Filled.Settings, "Settings", "App preferences"),
                    Triple(Icons.Filled.HelpOutline, "Help & Support", "FAQs and contact")
                )

                menuItems.forEachIndexed { index, (icon, title, subtitle) ->
                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn(tween(600 + index * 80)) +
                                slideInHorizontally(tween(600 + index * 80, easing = EaseOutCubic)) { -40 }
                    ) {
                        ProfileMenuItem(icon, title, subtitle)
                    }
                }

                Spacer(Modifier.height(20.dp))

                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(tween(1100)) + slideInVertically(tween(1100)) { 20 }
                ) {
                    OutlinedButton(
                        onClick = {},
                        modifier = Modifier.fillMaxWidth().height(52.dp),
                        shape = RoundedCornerShape(16.dp),
                        colors = ButtonDefaults.outlinedButtonColors(contentColor = Color.Red.copy(alpha = 0.7f)),
                        border = androidx.compose.foundation.BorderStroke(1.dp, Color.Red.copy(alpha = 0.3f))
                    ) {
                        Text("Sign Out", fontWeight = FontWeight.SemiBold)
                    }
                }
            }
        }
    }
}

@Composable
fun StatItem(value: String, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Text(value, color = EmeraldGreen, style = MaterialTheme.typography.titleLarge)
        Spacer(Modifier.height(4.dp))
        Text(label, color = GrayText, style = MaterialTheme.typography.labelSmall)
    }
}

@Composable
fun ProfileMenuItem(icon: ImageVector, title: String, subtitle: String) {
    Card(
        modifier = Modifier.fillMaxWidth().padding(bottom = 10.dp),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBlack)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(EmeraldGreen.copy(alpha = 0.12f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(icon, contentDescription = null, tint = EmeraldGreen, modifier = Modifier.size(20.dp))
            }
            Spacer(Modifier.width(14.dp))
            Column(modifier = Modifier.weight(1f)) {
                Text(title, color = White, style = MaterialTheme.typography.titleMedium)
                Text(subtitle, color = GrayText, style = MaterialTheme.typography.bodyMedium)
            }
            Icon(Icons.Filled.ChevronRight, contentDescription = null, tint = GrayText, modifier = Modifier.size(18.dp))
        }
    }
}