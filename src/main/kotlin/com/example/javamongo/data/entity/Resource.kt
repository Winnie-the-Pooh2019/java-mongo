package com.example.javamongo.data.entity

import com.example.javamongo.controller.dto.ResourceDto
import com.example.javamongo.controller.dto.UiDto
import com.example.javamongo.data.entity.emuns.IntervalEnum
import com.google.gson.annotations.SerializedName
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "resources")
data class Resource(
    @Id
    override val id: ObjectId,
    val name: String,
    @Field(name = "critical_amount")
    @SerializedName(value = "critical_amount")
    val criticalAmount: Int,
    val expiration: Map<IntervalEnum, Int>,
    val price: Double
) : Entity {
    override fun toUi(): UiDto = ResourceDto(
        id = id.toString(),
        name = name,
        price = price,
        criticalAmount = criticalAmount,
        expiration = expiration.map { (key, value) -> key.name to value }.toMap()
    )
}
