package com.example.javamongo.services.interfaces

import com.example.javamongo.data.entity.Client
import com.example.javamongo.data.repos.ClientRepository

abstract class ClientService(repository: ClientRepository) : MongoService<Client>(repository) {
}