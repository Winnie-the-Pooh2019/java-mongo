package com.example.javamongo.controller.dto

data class ResourceDto(
    val id: String = "",
    val name: String = "",
    val criticalAmount: String = "",
    val expiration: String = "",
    val price: String = ""
) : UiDto

