package com.example.nammahomestay.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nammahomestay.ui.theme.*

@Composable
fun BookingScreen(navController: NavController) {
    var guests by remember { mutableIntStateOf(2) }
    var nights by remember { mutableIntStateOf(2) }
    var showConfirmation by remember { mutableStateOf(false) }
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    val pricePerNight = 4999
    val totalPrice = pricePerNight * nights
    val taxes = (totalPrice * 0.18).toInt()
    val grandTotal = totalPrice + taxes

    if (showConfirmation) {
        ConfirmationScreen(navController, grandTotal)
        return
    }

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
                .padding(bottom = 32.dp)
        ) {
            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(400)) + slideInVertically(tween(400)) { -20 }
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(start = 8.dp, top = 16.dp, end = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Box(
                            modifier = Modifier.size(40.dp).background(CardBlack, RoundedCornerShape(12.dp)),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Filled.ArrowBack, null, tint = White, modifier = Modifier.size(20.dp))
                        }
                    }
                    Spacer(Modifier.width(8.dp))
                    Column {
                        Text("Book Your Stay", style = MaterialTheme.typography.titleLarge, color = White)
                        Text("Forest Retreat, Coorg", color = GrayText, fontSize = 12.sp)
                    }
                }
            }

            Spacer(Modifier.height(24.dp))

            AnimatedVisibility(
                visible = visible,
                enter = fadeIn(tween(500)) + slideInVertically(tween(500, easing = EaseOutCubic)) { 40 }
            ) {
                Column(modifier = Modifier.padding(horizontal = 20.dp)) {

                    Text("Select Dates", style = MaterialTheme.typography.titleMedium, color = White)
                    Spacer(Modifier.height(12.dp))
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
                        DateCard("Check-in", "Jun 15, 2025", "📅", Modifier.weight(1f))
                        DateCard("Check-out", "Jun 17, 2025", "📅", Modifier.weight(1f))
                    }

                    Spacer(Modifier.height(24.dp))
                    Text("Duration", style = MaterialTheme.typography.titleMedium, color = White)
                    Spacer(Modifier.height(12.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = CardBlack)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("🌙", fontSize = 20.sp)
                                Spacer(Modifier.width(10.dp))
                                Column {
                                    Text("Nights", color = White, fontWeight = FontWeight.Medium)
                                    Text("Minimum 1 night", color = GrayText, fontSize = 11.sp)
                                }
                            }
                            CounterControl(nights, { if (nights > 1) nights-- }, { if (nights < 14) nights++ })
                        }
                    }

                    Spacer(Modifier.height(16.dp))
                    Text("Guests", style = MaterialTheme.typography.titleMedium, color = White)
                    Spacer(Modifier.height(12.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp),
                        colors = CardDefaults.cardColors(containerColor = CardBlack)
                    ) {
                        Row(
                            modifier = Modifier.fillMaxWidth().padding(16.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Row(verticalAlignment = Alignment.CenterVertically) {
                                Text("👥", fontSize = 20.sp)
                                Spacer(Modifier.width(10.dp))
                                Column {
                                    Text("Guests", color = White, fontWeight = FontWeight.Medium)
                                    Text("Max 6 guests", color = GrayText, fontSize = 11.sp)
                                }
                            }
                            CounterControl(guests, { if (guests > 1) guests-- }, { if (guests < 6) guests++ })
                        }
                    }

                    Spacer(Modifier.height(24.dp))
                    Text("Price Summary", style = MaterialTheme.typography.titleMedium, color = White)
                    Spacer(Modifier.height(12.dp))
                    Card(
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(20.dp),
                        colors = CardDefaults.cardColors(containerColor = CardBlack)
                    ) {
                        Column(modifier = Modifier.padding(20.dp)) {
                            PriceRow("₹4,999 × $nights nights", "₹$totalPrice")
                            Spacer(Modifier.height(10.dp))
                            PriceRow("Taxes & fees (18%)", "₹$taxes")
                            Spacer(Modifier.height(14.dp))
                            HorizontalDivider(color = SurfaceBlack)
                            Spacer(Modifier.height(14.dp))
                            Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
                                Text("Total", color = White, fontWeight = FontWeight.Bold,
                                    style = MaterialTheme.typography.titleMedium)
                                AnimatedContent(
                                    targetState = grandTotal,
                                    transitionSpec = {
                                        slideInVertically { -it } + fadeIn() togetherWith
                                        slideOutVertically { it } + fadeOut()
                                    }, label = ""
                                ) { total ->
                                    Text("₹$total", color = EmeraldGreen, fontWeight = FontWeight.Bold,
                                        style = MaterialTheme.typography.titleMedium)
                                }
                            }
                        }
                    }

                    Spacer(Modifier.height(28.dp))

                    Button(
                        onClick = { showConfirmation = true },
                        modifier = Modifier.fillMaxWidth().height(58.dp),
                        shape = RoundedCornerShape(18.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen)
                    ) {
                        Text("Confirm Booking — ₹$grandTotal", color = Color(0xFF050505),
                            fontWeight = FontWeight.Bold, fontSize = 15.sp)
                    }

                    Spacer(Modifier.height(12.dp))
                    Text(
                        "Free cancellation up to 48 hours before check-in",
                        color = GrayText, fontSize = 12.sp,
                        modifier = Modifier.align(Alignment.CenterHorizontally)
                    )
                }
            }
        }
    }
}

@Composable
fun DateCard(label: String, date: String, icon: String, modifier: Modifier = Modifier) {
    Card(modifier = modifier, shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = CardBlack)) {
        Column(modifier = Modifier.padding(14.dp)) {
            Text(label, color = GrayText, fontSize = 11.sp)
            Spacer(Modifier.height(6.dp))
            Text(icon, fontSize = 18.sp)
            Spacer(Modifier.height(4.dp))
            Text(date, color = White, fontWeight = FontWeight.SemiBold, fontSize = 13.sp)
        }
    }
}

@Composable
fun CounterControl(value: Int, onDecrement: () -> Unit, onIncrement: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Box(
            modifier = Modifier.size(34.dp).clip(RoundedCornerShape(10.dp)).background(SurfaceBlack)
                .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null, onClick = onDecrement),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Filled.Remove, null, tint = White, modifier = Modifier.size(16.dp))
        }
        AnimatedContent(
            targetState = value,
            transitionSpec = {
                slideInVertically { -it } + fadeIn() togetherWith slideOutVertically { it } + fadeOut()
            }, label = ""
        ) { v ->
            Text(v.toString(), color = White, fontWeight = FontWeight.Bold, fontSize = 16.sp,
                modifier = Modifier.padding(horizontal = 16.dp))
        }
        Box(
            modifier = Modifier.size(34.dp).clip(RoundedCornerShape(10.dp)).background(EmeraldGreen.copy(alpha = 0.2f))
                .clickable(interactionSource = remember { MutableInteractionSource() }, indication = null, onClick = onIncrement),
            contentAlignment = Alignment.Center
        ) {
            Icon(Icons.Filled.Add, null, tint = EmeraldGreen, modifier = Modifier.size(16.dp))
        }
    }
}

@Composable
fun PriceRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = GrayText, style = MaterialTheme.typography.bodyMedium)
        Text(value, color = LightGrayText, style = MaterialTheme.typography.bodyMedium)
    }
}

@Composable
fun ConfirmationScreen(navController: NavController, total: Int) {
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    Box(
        modifier = Modifier.fillMaxSize().background(
            Brush.verticalGradient(listOf(Color(0xFF050505), Color(0xFF0A1A0A), Color(0xFF050505)))
        ),
        contentAlignment = Alignment.Center
    ) {
        AnimatedVisibility(
            visible = visible,
            enter = fadeIn(tween(600)) + scaleIn(tween(600, easing = EaseOutCubic), initialScale = 0.85f)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.padding(32.dp)) {
                Box(
                    modifier = Modifier.size(100.dp).background(EmeraldGreen.copy(alpha = 0.15f), RoundedCornerShape(50.dp)),
                    contentAlignment = Alignment.Center
                ) {
                    Text("✅", fontSize = 48.sp)
                }

                Spacer(Modifier.height(24.dp))
                Text("Booking Confirmed!", style = MaterialTheme.typography.headlineMedium,
                    color = White, fontWeight = FontWeight.Bold)
                Spacer(Modifier.height(8.dp))
                Text("Forest Retreat, Coorg", color = GrayText)
                Spacer(Modifier.height(24.dp))

                Card(modifier = Modifier.fillMaxWidth(), shape = RoundedCornerShape(20.dp),
                    colors = CardDefaults.cardColors(containerColor = CardBlack)) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        ConfirmRow("📅 Check-in", "Jun 15, 2025")
                        Spacer(Modifier.height(12.dp))
                        ConfirmRow("📅 Check-out", "Jun 17, 2025")
                        Spacer(Modifier.height(12.dp))
                        ConfirmRow("💰 Total Paid", "₹$total")
                        Spacer(Modifier.height(12.dp))
                        ConfirmRow("🎫 Booking ID", "#NHS${(10000..99999).random()}")
                    }
                }

                Spacer(Modifier.height(32.dp))

                Button(
                    onClick = { navController.navigate("explore") { popUpTo("explore") { inclusive = true } } },
                    modifier = Modifier.fillMaxWidth().height(56.dp),
                    shape = RoundedCornerShape(16.dp),
                    colors = ButtonDefaults.buttonColors(containerColor = EmeraldGreen)
                ) {
                    Text("Back to Home", color = Color(0xFF050505), fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}

@Composable
fun ConfirmRow(label: String, value: String) {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
        Text(label, color = GrayText, style = MaterialTheme.typography.bodyMedium)
        Text(value, color = White, fontWeight = FontWeight.SemiBold, style = MaterialTheme.typography.bodyMedium)
    }
}