package com.example.javamongo.controller.dto

data class ResourceShippingDto(
    val id: String = "",
    val resourceId: String = "",
    val resourceName: String = "",
    val amount: String = "",
    val price: String = "",
    val dateOrdered: String = "",
    val dateShipped: String? = null,
    val status: String = ""
) : UiDto

data class ResDto(
    val id: String,
    val name: String
)