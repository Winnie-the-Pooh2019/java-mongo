package com.example.javamongo.controller.dto

import com.example.javamongo.data.entity.Resource

data class ResourceDto(
    val id: String,
    val name: String,
    val price: Double
)

fun List<Resource>.toResourceDtoList(): List<ResourceDto> = this.map {
    ResourceDto(
        id = it.id.toString(),
        name = it.name,
        price = it.price
    )
}