package com.example.javamongo.controller.dto

import com.example.javamongo.data.entity.Client

data class ClientDto(
    val id: String,
    val lastName: String,
    val firstName: String,
    val phone: String
)

fun List<Client>.toClientDtoList(): List<ClientDto> = this.map { ClientDto(
    id = it.id.toString(),
    lastName = it.lastName,
    firstName = it.firstName,
    phone = it.phone
) }