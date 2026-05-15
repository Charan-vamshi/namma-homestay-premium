package com.example.nammahomestay.screens

import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.nammahomestay.components.BottomNav
import com.example.nammahomestay.components.StayCard
import com.example.nammahomestay.model.Stay
import com.example.nammahomestay.ui.theme.*

val allStays = listOf(
    Stay("Forest Retreat", "Coorg, Karnataka", "₹4,999", "4.9", "",
        tags = listOf("Forest", "Couples", "Monsoon"), accentColor = 0xFF2E7D32),
    Stay("Mountain Escape", "Chikmagalur, Karnataka", "₹6,499", "4.8", "",
        tags = listOf("Mountains", "Trekking", "Sunrise"), accentColor = 0xFF1565C0),
    Stay("River Stone Villa", "Sakleshpur, Karnataka", "₹7,299", "4.7", "",
        tags = listOf("Riverside", "Luxury", "Private"), accentColor = 0xFF00838F),
    Stay("Misty Highlands", "Ooty, Tamil Nadu", "₹5,499", "4.6", "",
        tags = listOf("Misty", "Scenic", "Heritage"), accentColor = 0xFF6A1B9A),
    Stay("Bamboo Bungalow", "Wayanad, Kerala", "₹3,999", "4.5", "",
        tags = listOf("Eco", "Wildlife", "Budget"), accentColor = 0xFF558B2F),
    Stay("Spice Garden Stay", "Munnar, Kerala", "₹5,199", "4.8", "",
        tags = listOf("Tea Gardens", "Romantic", "Peaceful"), accentColor = 0xFF00695C),
    Stay("Cliff Edge Cottage", "Kodaikanal, Tamil Nadu", "₹6,999", "4.9", "",
        tags = listOf("Cliff View", "Premium", "Misty"), accentColor = 0xFF4527A0),
    Stay("Paddy Field Home", "Alleppey, Kerala", "₹4,299", "4.6", "",
        tags = listOf("Backwaters", "Houseboat", "Serene"), accentColor = 0xFF00796B)
)

val locationFilters = listOf("All", "Karnataka", "Kerala", "Tamil Nadu")

@Composable
fun SearchScreen(navController: NavController) {
    var query by remember { mutableStateOf("") }
    var selectedLocation by remember { mutableStateOf("All") }
    var visible by remember { mutableStateOf(false) }
    LaunchedEffect(Unit) { visible = true }

    val filtered = allStays.filter { stay ->
        val matchesQuery = query.isBlank() ||
                stay.title.contains(query, true) ||
                stay.location.contains(query, true) ||
                stay.tags.any { it.contains(query, true) }
        val matchesLocation = selectedLocation == "All" ||
                stay.location.contains(selectedLocation, true)
        matchesQuery && matchesLocation
    }

    Scaffold(
        bottomBar = { BottomNav(navController) },
        containerColor = Color.Transparent
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(
                    Brush.verticalGradient(
                        listOf(Color(0xFF050505), Color(0xFF101010), Color(0xFF181818))
                    )
                )
        ) {
            Column(
                modifier = Modifier.padding(
                    top = 28.dp,
                    bottom = padding.calculateBottomPadding()
                )
            ) {
                AnimatedVisibility(
                    visible = visible,
                    enter = fadeIn(tween(500)) + slideInVertically(tween(500)) { -30 }
                ) {
                    Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                        Text("Find Your Escape", style = MaterialTheme.typography.headlineMedium, color = White)
                        Spacer(Modifier.height(4.dp))
                        Text("${allStays.size} properties across South India", color = GrayText, fontSize = 13.sp)
                        Spacer(Modifier.height(16.dp))

                        OutlinedTextField(
                            value = query,
                            onValueChange = { query = it },
                            placeholder = { Text("Search by name, location or vibe...", color = GrayText) },
                            leadingIcon = { Icon(Icons.Filled.Search, null, tint = EmeraldGreen) },
                            trailingIcon = {
                                if (query.isNotEmpty()) {
                                    IconButton(onClick = { query = "" }) {
                                        Icon(Icons.Filled.Close, null, tint = GrayText)
                                    }
                                }
                            },
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(16.dp),
                            colors = OutlinedTextFieldDefaults.colors(
                                focusedBorderColor = EmeraldGreen,
                                unfocusedBorderColor = SurfaceBlack,
                                cursorColor = EmeraldGreen,
                                focusedTextColor = White,
                                unfocusedTextColor = White,
                                focusedContainerColor = CardBlack,
                                unfocusedContainerColor = CardBlack
                            ),
                            singleLine = true
                        )
                        Spacer(Modifier.height(14.dp))
                    }
                }

                Row(
                    modifier = Modifier
                        .horizontalScroll(rememberScrollState())
                        .padding(horizontal = 20.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    locationFilters.forEach { loc ->
                        val selected = selectedLocation == loc
                        FilterChip(
                            selected = selected,
                            onClick = { selectedLocation = loc },
                            label = { Text(loc, fontSize = 12.sp) },
                            shape = RoundedCornerShape(12.dp),
                            colors = FilterChipDefaults.filterChipColors(
                                selectedContainerColor = EmeraldGreen,
                                selectedLabelColor = Color(0xFF050505),
                                containerColor = CardBlack,
                                labelColor = GrayText
                            )
                        )
                    }
                }

                Spacer(Modifier.height(14.dp))

                Row(
                    modifier = Modifier.padding(horizontal = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text("${filtered.size} result${if (filtered.size != 1) "s" else ""}", color = GrayText, fontSize = 13.sp)
                    if (filtered.size != allStays.size) {
                        Spacer(Modifier.width(8.dp))
                        Surface(shape = RoundedCornerShape(6.dp), color = EmeraldGreen.copy(alpha = 0.12f)) {
                            Text("filtered", color = EmeraldGreen, fontSize = 11.sp,
                                modifier = Modifier.padding(horizontal = 8.dp, vertical = 3.dp))
                        }
                    }
                }

                Spacer(Modifier.height(10.dp))

                LazyColumn(contentPadding = PaddingValues(horizontal = 20.dp, vertical = 4.dp)) {
                    itemsIndexed(filtered) { index, stay ->
                        AnimatedVisibility(
                            visible = visible,
                            enter = fadeIn(tween(300 + index * 60, easing = EaseOutCubic)) +
                                    slideInVertically(tween(300 + index * 60, easing = EaseOutCubic)) { 50 }
                        ) {
                            StayCard(stay = stay, onExploreClick = { navController.navigate("detail") })
                        }
                    }

                    if (filtered.isEmpty()) {
                        item {
                            Box(
                                modifier = Modifier.fillMaxWidth().padding(top = 60.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                                    Text("🌿", fontSize = 48.sp)
                                    Spacer(Modifier.height(12.dp))
                                    Text("No stays found", color = White, style = MaterialTheme.typography.titleMedium)
                                    Spacer(Modifier.height(4.dp))
                                    Text("Try a different search or location", color = GrayText, fontSize = 13.sp)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}