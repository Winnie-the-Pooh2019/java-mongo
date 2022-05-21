package com.example.javamongo.data.entity

import org.springframework.data.mongodb.core.mapping.DBRef

data class Pet(
    val name: String,
    @DBRef
    val medicine: Medicine
)
