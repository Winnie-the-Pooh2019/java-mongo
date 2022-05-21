package com.example.javamongo.data.entity

import com.fasterxml.jackson.annotation.JsonProperty
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("clients")
data class Client(
    @Id
    val id: ObjectId,
    @Field(value = "last_name")
    val lastName: String,
    @Field(value = "first_name")
    val firstName: String,
    val patronymic: String? = null,
    val phone: String,
    val address: String
)
