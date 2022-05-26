package com.example.javamongo.controller.dto

data class MedicineShippingDto(
    val id: String,
    val medicine: MedDto,
    val price: Double,
    val amount: Int,
    val dateOrdered: String,
    val dateShipped: String? = null,
    val status: String
) : UiDto

data class MedDto(
    val id: String,
    val name: String
)