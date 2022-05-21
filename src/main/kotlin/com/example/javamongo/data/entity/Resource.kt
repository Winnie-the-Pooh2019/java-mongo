package com.example.javamongo.data.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document(collection = "resources")
data class Resource(
    @Id
    val id: ObjectId,
    val name: String,
    @Field(name = "critical_amount")
    val criticalAmount: Int,
    val expiration: String,
    val price: Double
)
