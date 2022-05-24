package com.example.javamongo.controller.dto

import com.example.javamongo.data.entity.Order

data class OrderDto(
    val id: String,
    val clientId: String,
    val datePicking: String,
    val status: String
)

fun List<Order>.toOrderDtoList(): List<OrderDto> = this.map {
    OrderDto(
        id = it.id.toString(),
        clientId = it.client.id.toString(),
        datePicking = it.datePicked.toString(),
        status = it.status.name
    )
}