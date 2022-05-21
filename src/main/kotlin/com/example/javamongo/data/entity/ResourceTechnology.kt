package com.example.javamongo.data.entity

import org.springframework.data.mongodb.core.mapping.DBRef

data class ResourceTechnology(
    @DBRef
    val resource: Resource,
    val count: Int
)
