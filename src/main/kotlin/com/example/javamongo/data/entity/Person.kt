package com.example.javamongo.data.entity

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "people")
data class Person(
    val name: String = "",
    val surname: String = "",
    val pet: String = ""
)
