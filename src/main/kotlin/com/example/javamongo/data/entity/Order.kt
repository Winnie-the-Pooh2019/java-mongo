package com.example.javamongo.data.entity

import com.example.javamongo.data.entity.emuns.OrderStatus
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDate

@Document(collection = "orders")
data class Order(
    @Id
    val id: ObjectId,
    @DBRef
    val client: Client,
    @Field(name = "date_picked")
    val datePicked: LocalDate,
    val medicines: List<OrderMedicine>,
    val status: OrderStatus
)
