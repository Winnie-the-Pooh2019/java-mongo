package com.example.javamongo.controller.dto

import com.example.javamongo.data.entity.ResourceShipping

data class ResourceShippingDto(
    val id: String,
    val resourceName: String,
    val amount: Int,
    val price: Double,
    val dateOrdered: String,
    val dateShipped: String,
    val status: String
)

fun List<ResourceShipping>.toResourceShippingDto(): List<ResourceShippingDto> = this.map {
    ResourceShippingDto(
        id = it.id.toString(),
        resourceName = it.resource.name,
        amount = it.amount,
        price = it.price,
        dateShipped = it.dateShipped.toString(),
        dateOrdered = it.dateOrdered.toString(),
        status = it.status.name
    )
}