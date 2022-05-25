package com.example.javamongo.services.interfaces

import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.MedicineShipping

interface MedicineShippingService {
    suspend fun findAll(): List<MedicineShipping>

    suspend fun findById(id: String): MedicineShipping

    suspend fun getSoldOutMedicines(): List<Medicine>
}