package com.example.javamongo.data.migration.dto

import com.example.javamongo.data.entity.Client
import com.google.gson.annotations.SerializedName

data class ClientDto(
    @SerializedName(value = "last_name")
    val lastName: String,
    @SerializedName(value = "first_name")
    val firstName: String,
    val patronymic: String?,
    val phone: String,
    val address: String
)

fun List<ClientDto>.toClientList(): List<Client> = this.map {
    Client(
        lastName = it.lastName,
        firstName = it.firstName,
        patronymic = it.patronymic,
        phone = it.phone,
        address = it.address
    )
}