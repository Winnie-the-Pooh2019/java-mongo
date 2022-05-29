package com.example.javamongo.services.implementations

import com.example.javamongo.data.entity.Resource
import com.example.javamongo.data.repos.ResourceShippingRepository
import com.example.javamongo.services.interfaces.ResourceShippingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ResourceShippingServiceImpl(
    @Autowired
    private val resourceShippingRepository: ResourceShippingRepository
) : ResourceShippingService(resourceShippingRepository) {
    override suspend fun deleteAllByResource(resource: Resource) = withContext(Dispatchers.IO) {
        resourceShippingRepository.deleteAllByResource(resource)
    }
}