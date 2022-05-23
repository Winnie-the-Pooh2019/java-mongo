package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.Type
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface TypeRepository : MongoRepository<Type, ObjectId> {
}