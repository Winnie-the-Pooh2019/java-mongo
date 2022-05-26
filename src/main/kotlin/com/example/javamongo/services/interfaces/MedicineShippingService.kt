package com.example.javamongo.services.interfaces

import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.MedicineShipping
import com.example.javamongo.data.repos.MedicineShippingRepository

abstract class MedicineShippingService(repository: MedicineShippingRepository) : MongoService<MedicineShipping>(repository) {
    abstract suspend fun getSoldOutMedicines(): List<Medicine>
}