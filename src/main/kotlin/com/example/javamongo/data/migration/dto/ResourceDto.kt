package com.example.javamongo.data.migration.dto

import com.example.javamongo.data.entity.Resource
import com.example.javamongo.data.entity.emuns.IntervalEnum
import com.google.gson.annotations.SerializedName
import org.bson.types.ObjectId

data class ResourceDto(
    val name: String,
    @SerializedName(value = "critical_amount")
    val criticalAmount: Int,
    val expiration: Map<String, Int>,
    val price: Double
)

fun List<ResourceDto>.toResourcesList(): List<Resource> = this.map {
    Resource(
        id = ObjectId(),
        name = it.name,
        criticalAmount = it.criticalAmount,
        expiration = it.expiration.map { (key, value) -> IntervalEnum.valueOf(key.uppercase()) to value }.toMap(),
        price = it.price
    )
}