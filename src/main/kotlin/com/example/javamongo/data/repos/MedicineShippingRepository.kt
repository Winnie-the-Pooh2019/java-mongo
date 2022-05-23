package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.MedicineShipping
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository

interface MedicineShippingRepository : MongoRepository<MedicineShipping, ObjectId> {
}