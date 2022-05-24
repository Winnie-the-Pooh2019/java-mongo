package com.example.javamongo.controller.dto

import com.example.javamongo.data.entity.Medicine

data class MedicineDto(
    val id: String,
    val name: String,
    val typeName: String,
    val price: Double
)

fun List<Medicine>.toMedicineDtoList(): List<MedicineDto> = this.map {
    MedicineDto(
        id = it.id.toString(),
        name = it.name,
        typeName = it.type.name,
        price = it.price
    )
}