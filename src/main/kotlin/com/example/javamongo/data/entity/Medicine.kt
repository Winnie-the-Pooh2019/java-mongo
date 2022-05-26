package com.example.javamongo.data.entity

import com.example.javamongo.controller.dto.*
import com.example.javamongo.data.entity.emuns.IntervalEnum
import com.example.javamongo.data.entity.ersaz.Technology
import com.google.gson.annotations.SerializedName
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "medicines")
data class Medicine(
    @Id
    override val id: ObjectId,
    val name: String,
    @Field(name = "critical_amount")
    @SerializedName(value = "critical_amount")
    val criticalAmount: Int,
    val expiration: Map<IntervalEnum, Int>,
    @DBRef
    val type: Type,
    val price: Double,
    val technology: Technology?
) : Entity {
    override fun toUi(): UiDto = MedicineDto(
        id = id.toString(),
        name = name,
        criticalAmount = criticalAmount,
        expiration = expiration.map { (key, value) -> key.name to value }.toMap(),
        typeName = TypeDto(type.id.toString(), type.name),
        price = price,
        technology = if (technology != null) TechnologyDto(
            description = technology.description,
            prepareTime = technology.prepareTime.map { (key, value) -> key.name to value }.toMap(),
            resources = technology.resources.map {
                ResourceTechDto(ResDto(it.resource.id.toString(), it.resource.name), it.count)
            }
        ) else null
    )
}