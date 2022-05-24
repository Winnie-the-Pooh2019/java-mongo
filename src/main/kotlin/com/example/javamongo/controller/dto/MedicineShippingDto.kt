package com.example.javamongo.controller.dto

import com.example.javamongo.data.entity.MedicineShipping

data class MedicineShippingDto(
    val id: String,
    val medicineName: String,
    val price: Double,
    val amount: Int,
    val dateOrdered: String,
    val dateShipped: String? = null,
    val status: String
)

fun List<MedicineShipping>.toMedicineShippingDto(): List<MedicineShippingDto> = this.map {
    MedicineShippingDto(
        id = it.id.toString(),
        medicineName = it.medicine.name,
        amount = it.amount,
        dateOrdered = it.dateOrdered.toString(),
        dateShipped = it.dateShipped.toString(),
        status = it.status.toString(),
        price = it.price
    )
}