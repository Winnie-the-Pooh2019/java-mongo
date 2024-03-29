package com.example.javamongo.data.entity.ersaz

import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.emuns.OrderMedicineStatus
import org.springframework.data.mongodb.core.mapping.DBRef

data class OrderMedicine(
    @DBRef
    val medicine: Medicine?,
    val amount: Int,
    val price: Double,
    val status: OrderMedicineStatus
)
