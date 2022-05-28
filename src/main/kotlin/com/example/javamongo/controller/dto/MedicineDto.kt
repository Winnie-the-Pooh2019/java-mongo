package com.example.javamongo.controller.dto


data class MedicineDto(
    val id: String,
    val name: String,
    val criticalAmount: Int,
    val expiration: String,
    val typeName: TypeDto,
    val price: Double,
    val technology: TechnologyDto?
) : UiDto

data class TechnologyDto(
    val description: String,
    val prepareTime: String,
    val resources: List<ResourceTechDto>
)

data class ResourceTechDto(
    val resource: ResDto,
    val count: Int
)