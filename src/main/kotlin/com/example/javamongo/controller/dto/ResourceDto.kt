package com.example.javamongo.controller.dto

data class ResourceDto(
    val id: String,
    val name: String,
    val criticalAmount: Int,
    val expiration: Map<String, Int>,
    val price: Double
) : UiDto

