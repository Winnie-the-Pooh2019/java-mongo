package com.example.javamongo.controller.dto

data class MedicineDto(
    val id: String,
    val name: String,
    val typeName: String,
    val price: Double
) : UiDto

