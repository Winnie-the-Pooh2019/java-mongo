package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.Type
import org.springframework.data.mongodb.repository.MongoRepository

interface TypeRepository : MongoRepository<Type, String> {
}