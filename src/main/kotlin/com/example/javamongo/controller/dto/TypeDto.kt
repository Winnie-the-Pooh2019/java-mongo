package com.example.javamongo.controller.dto

import com.example.javamongo.data.entity.Type

data class TypeDto(
    val id: String,
    val name: String
)

fun List<Type>.toTypeDtoList(): List<TypeDto> = this.map {
    TypeDto(
        id = it.id.toString(),
        name = it.name
    )
}