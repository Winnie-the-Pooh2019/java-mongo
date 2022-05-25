package com.example.javamongo.services.interfaces

import com.example.javamongo.data.entity.Type

interface TypeService {
    suspend fun findById(id: String): Type

    suspend fun findAll(): List<Type>
}