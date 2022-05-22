package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.Order
import org.springframework.data.mongodb.repository.MongoRepository

interface OrderRepository : MongoRepository<Order, String> {
}