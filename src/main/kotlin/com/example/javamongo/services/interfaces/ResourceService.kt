package com.example.javamongo.services.interfaces

import com.example.javamongo.data.entity.Resource
import org.springframework.stereotype.Service

@Service
interface ResourceService {
    suspend fun findById(id: String): Resource

    suspend fun findAll(): List<Resource>
}