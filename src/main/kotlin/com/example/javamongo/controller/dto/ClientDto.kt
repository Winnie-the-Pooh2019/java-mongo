package com.example.javamongo.controller.dto

data class ClientDto(
    val id: String,
    val lastName: String,
    val firstName: String,
    val phone: String
) : UiDto

