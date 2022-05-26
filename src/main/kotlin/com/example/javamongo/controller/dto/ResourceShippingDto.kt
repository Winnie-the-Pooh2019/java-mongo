package com.example.javamongo.controller.dto

data class ResourceShippingDto(
    val id: String,
    val resource: ResDto,
    val amount: Int,
    val price: Double,
    val dateOrdered: String,
    val dateShipped: String? = null,
    val status: String
) : UiDto

data class ResDto(
    val id: String,
    val name: String
)