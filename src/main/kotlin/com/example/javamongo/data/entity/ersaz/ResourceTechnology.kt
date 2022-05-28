package com.example.javamongo.data.entity.ersaz

import com.example.javamongo.data.entity.Resource
import org.springframework.data.mongodb.core.mapping.DBRef

data class ResourceTechnology(
    @DBRef
    val resource: Resource?,
    val count: Int
)
