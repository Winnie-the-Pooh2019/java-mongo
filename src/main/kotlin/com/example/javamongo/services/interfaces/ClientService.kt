package com.example.javamongo.services.interfaces

import com.example.javamongo.data.entity.Client

interface ClientService {
    suspend fun findAll(): List<Client>

    suspend fun findById(id: String): Client

    suspend fun deleteById(id: String): Boolean

    suspend fun updateById(client: Client): Boolean
}