package com.example.javamongo.controller.dto

data class MedicineShippingDto(
    val id: String,
    val medicineName: String,
    val price: Double,
    val amount: Int,
    val dateOrdered: String,
    val dateShipped: String? = null,
    val status: String
) : UiDto

