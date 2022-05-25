package com.example.javamongo.services.implementations

import com.example.javamongo.data.entity.Type
import com.example.javamongo.data.repos.TypeRepository
import com.example.javamongo.services.interfaces.TypeService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TypeServiceImpl(
    @Autowired
    private val typeRepository: TypeRepository
) : TypeService {
    override suspend fun findById(id: String): Type = withContext(Dispatchers.IO) {
        return@withContext typeRepository.findById(ObjectId(id)).get()
    }

    override suspend fun findAll(): List<Type> = withContext(Dispatchers.IO) {
        return@withContext typeRepository.findAll()
    }
}