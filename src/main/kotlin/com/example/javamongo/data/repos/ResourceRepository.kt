package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.Resource
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface ResourceRepository : MongoRepository<Resource, ObjectId> {
}