package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.Order
import com.example.javamongo.data.entity.emuns.OrderStatus
import org.bson.types.ObjectId
import org.springframework.data.mongodb.repository.MongoRepository
import java.time.LocalDate

interface OrderRepository : MongoRepository<Order, ObjectId> {
//    1
    fun findAllByDatePickedBeforeAndStatus(date: LocalDate, status: OrderStatus): List<Order>

//    2, 6
    fun findAllByStatus(status: OrderStatus): List<Order>

//    4
    fun findAllByDatePickedBetween(startDate: LocalDate, endDate: LocalDate): List<Order>
}
