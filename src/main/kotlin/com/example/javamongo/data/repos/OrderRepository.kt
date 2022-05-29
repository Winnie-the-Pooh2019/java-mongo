package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.Client
import com.example.javamongo.data.entity.Order
import com.example.javamongo.data.entity.emuns.OrderStatus
import java.time.LocalDate

interface OrderRepository : Repository<Order> {
    fun deleteAllByClient(client: Client)

//    1
    fun findAllByDatePickedBeforeAndStatus(date: LocalDate, status: OrderStatus): List<Order>

//    2, 6
    fun findAllByStatus(status: OrderStatus): List<Order>

//    4
    fun findAllByDatePickedBetween(startDate: LocalDate, endDate: LocalDate): List<Order>
}
