package com.example.nammahomestay.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nammahomestay.ui.theme.*

@Composable
fun DetailScreen(navController: NavController) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(Color(0xFF050505), Color(0xFF121212), Color(0xFF1A1A1A))
                )
            )
    ) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .padding(bottom = 40.dp)
        ) {
            // Hero
            Box(modifier = Modifier.fillMaxWidth().height(300.dp)) {
                Box(
                    modifier = Modifier.fillMaxSize().background(
                        Brush.linearGradient(listOf(Color(0xFF1B5E20), Color(0xFF2E7D32), Color(0xFF0A0A0A)))
                    )
                )
                Box(
                    modifier = Modifier.fillMaxSize().background(
                        Brush.verticalGradient(
                            listOf(Color.Transparent, Color(0xFF050505)),
                            startY = 120f
                        )
                    )
                )

                IconButton(
                    onClick = { navController.popBackStack() },
                    modifier = Modifier.padding(16.dp).align(Alignment.TopStart)
                ) {
                    Box(
                        modifier = Modifier.size(40.dp).background(Color.Black.copy(alpha = 0.5f), RoundedCornerShape(12.dp)),
                        contentAlignment = Alignment.Center
                    ) {
                        // FIX 2: Use AutoMirrored version of ArrowBack
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, null, tint = White, modifier = Modifier.size(20.dp))
                    }
                }

                // FIX 1: Wrap AnimatedVisibility in a Column to provide explicit ColumnScope
                Column(modifier = Modifier.align(Alignment.BottomStart)) {
                    AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn(tween(700)) + slideInVertically(tween(700, easing = EaseOutCubic)) { 40 },
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            Text("Forest Retreat", style = MaterialTheme.typography.headlineLarge,
                                color = White, fontWeight = FontWeight.Bold)
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.LocationOn, null, tint = EmeraldGreen, modifier = Modifier.size(14.dp))
                                Spacer(Modifier.width(4.dp))
                                Text("Coorg, Karnataka", color = LightGrayText, fontSize = 13.sp)
                            }
                        }
                    }
                }
            }

            Spacer(Modifier.height(20.dp))

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(800)) + slideInVertically(tween(800, easing = EaseOutCubic)) { 30 }
            ) {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {

                    Row(horizontalArrangement = Arrangement.spacedBy(10.dp), verticalAlignment = Alignment.CenterVertically) {
                        Surface(shape = RoundedCornerShape(10.dp), color = GoldYellow.copy(alpha = 0.12f)) {
                            Row(modifier = Modifier.padding(horizontal = 12.dp, vertical = 6.dp),
                                verticalAlignment = Alignment.CenterVertically) {
                                Icon(Icons.Filled.Star, null, tint = GoldYellow, modifier = Modifier.size(14.dp))
                                Spacer(Modifier.width(4.dp))
                                Text("4.9", color = GoldYellow, fontSize = 13.sp, fontWeight = FontWeight.SemiBold)
                            }
                        }
                        listOf("WiFi", "Mountain View", "Private Pool").forEach { tag ->
                            Surface(shape = RoundedCornerShape(10.dp), color = EmeraldGreen.copy(alpha = 0.10f)) {
                                Text(tag, color = EmeraldGreen, fontSize = 11.sp,
                                    modifier = Modifier.padding(horizontal = 10.dp, vertical = 6.dp))
                            }
                        }
                    }

                    Spacer(Modifier.height(24.dp))
                    Text("About", style = MaterialTheme.typography.titleLarge, color = White)
                    Spacer(Modifier.height(10.dp))
                    Text(
                        "A luxury nature retreat surrounded by dense forests, waterfalls and fog-covered mountains. " +
                                "Experience authentic rural Karnataka with world-class amenities and personalized hospitality.",
                        color = LightGrayText, style = MaterialTheme.typography.bodyLarge, lineHeight = 26.sp
                    )

                    Spacer(Modifier.height(24.dp))
                    Text("Highlights", style = MaterialTheme.typography.titleLarge, color = White)
                    Spacer(Modifier.height(12.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                        HighlightCard("🌿", "Nature", "Dense forest", Modifier.weight(1f))
                        HighlightCard("🌊", "Waterfall", "2km trail", Modifier.weight(1f))
                        HighlightCard("🍃", "Organic", "Farm meals", Modifier.weight(1f))
                    }

                    Spacer(Modifier.height(24.dp))

                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = Color(0xFF0D1F0D))
                    ) {
                        Row(modifier = Modifier.padding(18.dp)) {
                            Text("✨", fontSize = 24.sp)
                            Spacer(Modifier.width(12.dp))
                            Column {
                                Text("AI Travel Insight", color = EmeraldGreen, style = MaterialTheme.typography.titleMedium)
                                Spacer(Modifier.height(6.dp))
                                Text(
                                    "Best for couples, monsoon travel and nature photography. Book 2+ nights for the full experience.",
                                    color = LightGrayText, style = MaterialTheme.typography.bodyMedium
                                )
                            }
                        }
                    }

                    Spacer(Modifier.height(28.dp))

                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text("₹4,999", color = SoftGreen, style = MaterialTheme.typography.headlineSmall,
                                fontWeight = FontWeight.Bold)
                            Text("per night", color = GrayText, fontSize = 12.sp)
                        }
                        Button(
                            onClick = { navController.navigate("booking") },
                            modifier = Modifier.height(52.dp),
                            shape = RoundedCornerShape(16.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen),
                            contentPadding = PaddingValues(horizontal = 32.dp)
                        ) {
                            Text("Book Now", color = Color(0xFF050505), fontWeight = FontWeight.Bold, fontSize = 15.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun HighlightCard(emoji: String, title: String, subtitle: String, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBlack)
    ) {
        Column(modifier = Modifier.padding(12.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(emoji, fontSize = 24.sp)
            Spacer(Modifier.height(6.dp))
            Text(title, color = White, fontSize = 12.sp, fontWeight = FontWeight.SemiBold)
            Spacer(Modifier.height(2.dp))
            Text(subtitle, color = GrayText, fontSize = 10.sp)
        }
    }
}