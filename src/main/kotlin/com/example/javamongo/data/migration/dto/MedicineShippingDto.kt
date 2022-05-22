package com.example.javamongo.data.migration.dto

import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.MedicineShipping
import com.example.javamongo.data.entity.emuns.ShippingStatus
import com.google.gson.annotations.SerializedName
import org.bson.types.ObjectId
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class MedicineShippingDto(
    @SerializedName(value = "medicine_id")
    val medicineId: Int,
    val amount: Int,
    val price: Double,
    @SerializedName(value = "date_ordered")
    val dateOrdered: String,
    @SerializedName(value = "date_shipped")
    val dateShipped: String? = null,
    val status: String
)

fun List<MedicineShippingDto>.toMedicineShippingList(medicines: List<Medicine>): List<MedicineShipping> = map {
    MedicineShipping(
        id = ObjectId(),
        medicine = medicines[it.medicineId - 1],
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