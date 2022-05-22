package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.Medicine
import org.springframework.data.mongodb.repository.MongoRepository

interface MedicineRepository : MongoRepository<Medicine, String> {
}