package com.example.javamongo.controller.dto

data class MedicineShippingDto(
    val id: String = "",
    val medicineId: String = "",
    val medicineName: String = "",
    val price: String = "",
    val amount: String = "",
    val dateOrdered: String = "",
    val dateShipped: String? = null,
    val status: String = ""
) : UiDto
