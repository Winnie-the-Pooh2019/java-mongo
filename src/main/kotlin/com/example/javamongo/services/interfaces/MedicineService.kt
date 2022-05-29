package com.example.javamongo.services.interfaces

import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.Resource
import com.example.javamongo.data.entity.Type
import com.example.javamongo.data.entity.ersaz.ResourceTechnology
import com.example.javamongo.data.entity.ersaz.Technology
import com.example.javamongo.data.repos.MedicineRepository

abstract class MedicineService(repository: MedicineRepository) : MongoService<Medicine>(repository) {
    abstract suspend fun deleteAllByType(type: Type): List<Medicine>

    abstract suspend fun deleteAllByResource(resource: Resource): List<Medicine>
//    8
    abstract suspend fun getMedicinesTechByTypes(types: List<String>): List<Technology>

//    8
    abstract suspend fun getMedicinesTechByMeds(meds: List<String>): List<Technology>

//    9
    abstract suspend fun getTechnologyResourceByMed(medicineName: String): List<ResourceTechnology>
}