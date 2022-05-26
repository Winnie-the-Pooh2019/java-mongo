package com.example.javamongo.controller.dto


data class OrderDto(
    val id: String,
    val clientSurname: String,
    val datePicking: String,
    val status: String,
    val medicines: List<OrderMedicineDto>
) : UiDto

data class OrderMedicineDto(
    val medicineName: String,
    val amount: Int,
    val price: Double,
    val status: String
)