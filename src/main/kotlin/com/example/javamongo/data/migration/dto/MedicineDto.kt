package com.example.javamongo.data.migration.dto

import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.Resource
import com.example.javamongo.data.entity.Type
import com.example.javamongo.data.entity.emuns.IntervalEnum
import com.example.javamongo.data.entity.ersaz.ResourceTechnology
import com.example.javamongo.data.entity.ersaz.Technology
import com.google.gson.annotations.SerializedName
import org.bson.types.ObjectId

data class MedicineDto(
    val name: String,
    @SerializedName(value = "critical_amount")
    val criticalAmount: Int,
    val expiration: Map<String, Int>,
    @SerializedName(value = "type_id")
    val typeId: Int,
    val price: Double,
    val technology: TechnologyDto
)

data class TechnologyDto(
    val description: String,
    @SerializedName(value = "prepare_time")
    val prepareTime: Map<String, Int>,
    val resources: List<ResourceTechDto>
)

data class ResourceTechDto(
    @SerializedName(value = "resource_id")
    val resourceId: Int,
    val count: Int
)

fun List<MedicineDto>.toMedicineList(types: List<Type>, resources: List<Resource>): List<Medicine> = this.map {
    Medicine(
        ObjectId(),
        name = it.name,
        criticalAmount = it.criticalAmount,
        expiration = it.expiration.map { (key, value) -> IntervalEnum.valueOf(key.uppercase()) to value }.toMap(),
        type = types[it.typeId - 1],
        price = it.price,
        technology = Technology(
            description = it.technology.description,
            prepareTime = it.technology.prepareTime.map { (key, value) -> IntervalEnum.valueOf(key.uppercase()) to value }.toMap(),
            resources = it.technology.resources.map { rt ->
                ResourceTechnology(
                    resource = resources[rt.resourceId - 1],
                    count = rt.count
                )
            }
        )
    )
}