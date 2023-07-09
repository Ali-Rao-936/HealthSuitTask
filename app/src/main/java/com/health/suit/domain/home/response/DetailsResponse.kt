package com.health.suit.domain.home.response

data class DetailsResponse(
    val category: String,
    val description: String,
    val id: Int,
    val image: String,
    val price: Double,
    val rating: RatingX,
    val title: String
)