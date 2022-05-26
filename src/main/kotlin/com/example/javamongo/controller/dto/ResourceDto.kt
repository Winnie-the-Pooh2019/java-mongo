package com.example.javamongo.controller.dto

data class ResourceDto(
    val id: String,
    val name: String,
    val price: Double
) : UiDto

