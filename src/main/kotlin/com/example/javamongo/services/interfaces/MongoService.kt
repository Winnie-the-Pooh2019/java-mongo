package com.example.javamongo.services.interfaces

import com.example.javamongo.data.entity.Entity
import com.example.javamongo.data.repos.Repository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.types.ObjectId

abstract class MongoService<T : Entity>(val repository: Repository<T>) {
    suspend fun findAll(): List<T> = withContext(Dispatchers.IO) {
        return@withContext repository.findAll()
    }

    suspend fun findById(id: String): T = withContext(Dispatchers.IO) {
        return@withContext repository.findById(ObjectId(id)).get()
    }

    suspend fun deleteById(id: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            repository.deleteById(ObjectId(id))
            true
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            false
        }
    }

    suspend fun updateById(entity: T): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            repository.save(entity)
            true
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            false
        }
    }
}