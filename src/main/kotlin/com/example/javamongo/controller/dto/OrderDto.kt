package com.example.javamongo.controller.dto


data class OrderDto(
    val id: String = "",
    val clientId: String = "",
    val clientSurname: String = "",
    val datePicking: String = "",
    val status: String = "",
    val medicines: String = ""
) : UiDto

data class CliDto(
    val id: String = "",
    val lastName: String = ""
)

data class OrderMedicineDto(
    val medicineId: String,
    val medicineName: String,
    val amount: Int,
    val price: Double,
    val status: String
)