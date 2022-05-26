package com.example.javamongo.controller.dto

data class OrderDto(
    val id: String,
    val clientId: String,
    val datePicking: String,
    val status: String
) : UiDto

