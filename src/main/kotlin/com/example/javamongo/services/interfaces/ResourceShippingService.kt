package com.example.javamongo.services.interfaces

import com.example.javamongo.data.entity.ResourceShipping
import java.util.ListResourceBundle

interface ResourceShippingService {
    suspend fun findById(id: String): ResourceShipping

    suspend fun findAll(): List<ResourceShipping>
}