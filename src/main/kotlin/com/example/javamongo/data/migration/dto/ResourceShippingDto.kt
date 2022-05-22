package com.example.javamongo.data.migration.dto

import com.example.javamongo.data.entity.Resource
import com.example.javamongo.data.entity.ResourceShipping
import com.example.javamongo.data.entity.emuns.ShippingStatus
import com.google.gson.annotations.SerializedName
import org.bson.types.ObjectId
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class ResourceShippingDto(
    @SerializedName(value = "resource_id")
    val resourceId: Int,
    val amount: Int,
    val price: Double,
    @SerializedName(value = "date_ordered")
    val dateOrdered: String,
    @SerializedName(value = "date_shipped")
    val dateShipped: String? = null,
    val status: String
)

fun List<ResourceShippingDto>.toResourceShippingList(resources: List<Resource>): List<ResourceShipping> = map {
    ResourceShipping(
        id = ObjectId(),
        resource = resources[it.resourceId - 1],
        amount = it.amount,
        price = it.price,
        dateOrdered = LocalDate.parse(it.dateOrdered, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        dateShipped = if (it.dateShipped != null) LocalDate.parse(
            it.dateShipped,
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        ) else null,
        status = ShippingStatus.valueOf(it.status)
    )
}