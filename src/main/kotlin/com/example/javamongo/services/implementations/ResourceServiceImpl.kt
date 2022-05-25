package com.example.javamongo.services.implementations

import com.example.javamongo.data.entity.Resource
import com.example.javamongo.data.repos.ResourceRepository
import com.example.javamongo.services.interfaces.ResourceService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ResourceServiceImpl(
    @Autowired
    private val resourceRepository: ResourceRepository
) : ResourceService {
    override suspend fun findById(id: String): Resource = withContext(Dispatchers.IO) {
        return@withContext resourceRepository.findById(ObjectId(id)).get()
    }

    override suspend fun findAll(): List<Resource> = withContext(Dispatchers.IO) {
        return@withContext resourceRepository.findAll()
    }
}