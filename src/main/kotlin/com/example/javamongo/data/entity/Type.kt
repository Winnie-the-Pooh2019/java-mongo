package com.example.javamongo.data.entity

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.lang.Nullable

@Document(value = "types")
data class Type(
    @Id
    @Nullable
    val id: ObjectId,
    val name: String,
    val attributes: Map<String, String>? = null
)
