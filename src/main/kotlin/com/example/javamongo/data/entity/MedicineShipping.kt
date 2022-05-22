package com.example.javamongo.data.entity

import com.example.javamongo.data.entity.emuns.ShippingStatus
import com.google.gson.annotations.SerializedName
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDate

@Document(collection = "medicine_shipping")
data class MedicineShipping(
    @Id
    val id: ObjectId,
    @DBRef
    val medicine: Medicine,
    val amount: Int,
    val price: Double,
    @Field(name = "date_ordered")
    @SerializedName(value = "date_ordered")
    val dateOrdered: LocalDate,
    @Field(name = "date_picked")
    @SerializedName(value = "date_shipped")
    val dateShipped: LocalDate? = null,
    val status: ShippingStatus
)
