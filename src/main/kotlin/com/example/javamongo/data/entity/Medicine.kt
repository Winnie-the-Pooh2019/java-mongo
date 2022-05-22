package com.example.javamongo.data.entity

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
    val id: ObjectId,
    val name: String,
    @Field(name = "critical_amount")
    @SerializedName(value = "critical_amount")
    val criticalAmount: Int,
    val expiration: Map<IntervalEnum, Int>,
    @DBRef
    val type: Type,
    val price: Double,
    val technology: Technology
)