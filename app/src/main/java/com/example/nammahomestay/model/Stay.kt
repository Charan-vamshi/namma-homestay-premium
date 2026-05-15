package com.example.nammahomestay.model

data class Stay(
    val title: String,
    val location: String,
    val price: String,
    val rating: String,
    val image: String,
    val tags: List<String> = emptyList(),
    val accentColor: Long = 0xFF1B5E20
)