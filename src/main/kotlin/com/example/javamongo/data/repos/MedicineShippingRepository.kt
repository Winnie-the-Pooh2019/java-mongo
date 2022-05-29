package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.MedicineShipping

interface MedicineShippingRepository : Repository<MedicineShipping> {
    fun deleteAllByMedicine(medicine: Medicine)
}