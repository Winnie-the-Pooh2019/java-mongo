package com.example.javamongo.services.implementations

import com.example.javamongo.data.entity.Client
import com.example.javamongo.data.repos.ClientRepository
import com.example.javamongo.services.interfaces.ClientService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientServiceImpl(
    @Autowired
    private val clientRepository: ClientRepository
) : ClientService {
    override suspend fun findAll(): List<Client> = withContext(Dispatchers.IO) {
        return@withContext clientRepository.findAll()
    }

    override suspend fun findById(id: String): Client = withContext(Dispatchers.IO) {
        return@withContext clientRepository.findById(ObjectId(id)).get()
    }

    override suspend fun deleteById(id: String): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            clientRepository.deleteById(ObjectId(id))
            true
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            false
        }
    }

    override suspend fun updateById(client: Client): Boolean = withContext(Dispatchers.IO) {
        return@withContext try {
            clientRepository.save(client)
            true
        } catch (e: IllegalArgumentException) {
            e.printStackTrace()
            false
        }
    }
}