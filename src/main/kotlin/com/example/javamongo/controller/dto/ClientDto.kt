package com.example.javamongo.controller.dto

data class ClientDto(
    val id: String = "",
    val lastName: String = "",
    val firstName: String = "",
    val patronymic: String? = null,
    val phone: String = "",
    val address: String = ""
) : UiDto

