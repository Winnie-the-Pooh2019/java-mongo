package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.Medicine
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface MedicineRepository : MongoRepository<Medicine, ObjectId> {
    fun findByName(name: String): Medicine
}