package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.ResourceShipping
import org.springframework.data.mongodb.repository.MongoRepository

interface ResourceShippingRepository : MongoRepository<ResourceShipping, String> {
}