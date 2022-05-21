package com.example.javamongo.data.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("clients")
data class Client(
    @Id
    val id: ObjectId,
    @JsonProperty("last_name")
    val lastName: String,
    @JsonProperty("first_name")
    val firstName: String,
    val patronymic: String,
    val phone: String,
    val address: String,
    val attributes: Map<String, String>
)
