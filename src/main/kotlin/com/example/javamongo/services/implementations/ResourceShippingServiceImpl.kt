package com.example.javamongo.services.implementations

import com.example.javamongo.data.entity.ResourceShipping
import com.example.javamongo.data.repos.ResourceShippingRepository
import com.example.javamongo.services.interfaces.ResourceShippingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ResourceShippingServiceImpl(
    @Autowired
    private val resourceShippingRepository: ResourceShippingRepository
) : ResourceShippingService {
    override suspend fun findById(id: String): ResourceShipping = withContext(Dispatchers.IO) {
        return@withContext resourceShippingRepository.findById(ObjectId(id)).get()
    }

    override suspend fun findAll(): List<ResourceShipping> = withContext(Dispatchers.IO) {
        return@withContext resourceShippingRepository.findAll()
    }
}