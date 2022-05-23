package com.example.javamongo.services

import com.example.javamongo.data.entity.Medicine

interface MedicineShippingService {
    suspend fun getSoldOutMedicines(): List<Medicine>
}