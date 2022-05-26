package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.Entity
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.repository.NoRepositoryBean

@NoRepositoryBean
interface Repository<T : Entity> : MongoRepository<T, ObjectId> {
}