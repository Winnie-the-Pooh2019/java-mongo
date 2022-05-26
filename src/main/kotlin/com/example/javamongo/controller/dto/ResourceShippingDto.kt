package com.example.javamongo.controller.dto

data class ResourceShippingDto(
    val id: String,
    val resourceName: String,
    val amount: Int,
    val price: Double,
    val dateOrdered: String,
    val dateShipped: String,
    val status: String
) : UiDto

