package com.example.javamongo.controller.dto


data class MedicineDto(
    val id: String = "",
    val name: String = "",
    val criticalAmount: String = "",
    val expiration: String = "",
    val typeName: String = "",
    val typeId: String = "",
    val price: String = "",
    val description: String? = null,
    val prepareTime: String? = null,
    val resources: String? = null
) : UiDto

data class ResourceTechDto(
    val resource: ResDto,
    val count: Int
)