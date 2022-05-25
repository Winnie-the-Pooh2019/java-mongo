package com.example.javamongo.services.interfaces

import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.ersaz.ResourceTechnology
import com.example.javamongo.data.entity.ersaz.Technology

interface MedicineService {
    suspend fun findAll(): List<Medicine>

    suspend fun findById(id: String): Medicine

//    8
    suspend fun getMedicinesTechByTypes(types: List<String>): List<Technology>

//    8
    suspend fun getMedicinesTechByMeds(meds: List<String>): List<Technology>

//    9
    suspend fun getTechnologyResourceByMed(medicineName: String): List<ResourceTechnology>
}