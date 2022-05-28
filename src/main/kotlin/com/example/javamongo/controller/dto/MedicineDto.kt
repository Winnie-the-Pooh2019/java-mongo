package com.example.javamongo.controller.dto


data class MedicineDto(
    val id: String = "",
    val name: String = "",
    val criticalAmount: Int = 0,
    val expiration: String = "",
    val typeName: String = "",
    val typeId: String = "",
    val price: Double = 0.0,
    val description: String? = null,
    val prepareTime: String? = null,
    val resources: String? = null
) : UiDto

data class ResourceTechDto(
    val resource: ResDto,
    val count: Int
)