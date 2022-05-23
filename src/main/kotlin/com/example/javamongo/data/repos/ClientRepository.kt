package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.Client
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ClientRepository : MongoRepository<Client, ObjectId> {
}