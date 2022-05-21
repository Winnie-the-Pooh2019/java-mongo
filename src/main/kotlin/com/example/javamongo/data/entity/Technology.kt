package com.example.javamongo.data.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDateTime

@Document(collection = "technologies")
data class Technology(
    @Id
    val id: ObjectId,
    val description: String,
    @Field(name = "prepare_time")
    val prepareTime: LocalDateTime,
    val resources: List<ResourceTechnology>
)
