package com.example.javamongo.controller.dto


data class MedicineDto(
    val id: String,
    val name: String,
    val criticalAmount: Int,
    val expiration: String,
    val typeName: String = "",
    val typeId: String,
    val price: Double,
    val description: String?,
    val prepareTime: String?,
    val resources: String?
) : UiDto

data class ResourceTechDto(
    val resource: ResDto,
    val count: Int
)