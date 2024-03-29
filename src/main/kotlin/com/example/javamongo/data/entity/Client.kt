package com.example.javamongo.data.entity

import com.example.javamongo.controller.dto.ClientDto
import com.example.javamongo.controller.dto.UiDto
import com.google.gson.annotations.SerializedName
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field

@Document("clients")
data class Client(
    @Id
    override val id: ObjectId = ObjectId(),
    @Field(value = "last_name")
    @SerializedName(value = "last_name")
    val lastName: String,
    @Field(value = "first_name")
    @SerializedName(value = "first_name")
    val firstName: String,
    val patronymic: String? = null,
    val phone: String,
    val address: String
) : Entity {
    override fun toUi(): UiDto = ClientDto(id.toString(), lastName, firstName, patronymic, phone, address)
}
