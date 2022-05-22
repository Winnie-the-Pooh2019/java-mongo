package com.example.javamongo.data.migration.dto

import com.example.javamongo.data.entity.Type
import org.bson.types.ObjectId

data class TypeDto(
    val name: String,
    val attributes: Map<String, String>? = null
)

fun List<TypeDto>.toTypeList(): List<Type> = map {
    Type(
        id = ObjectId(),
        name = it.name,
        attributes = it.attributes
    )
}