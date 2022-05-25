package com.example.javamongo.services.implementations

import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.ersaz.ResourceTechnology
import com.example.javamongo.data.entity.ersaz.Technology
import com.example.javamongo.data.repos.MedicineRepository
import com.example.javamongo.services.interfaces.MedicineService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MedicineServiceImpl(
    @Autowired
    private val medicineRepository: MedicineRepository
) : MedicineService {
    override suspend fun findAll(): List<Medicine> = withContext(Dispatchers.IO) {
        return@withContext medicineRepository.findAll()
    }

    override suspend fun findById(id: String): Medicine = withContext(Dispatchers.IO) {
        return@withContext medicineRepository.findById(ObjectId(id)).get()
    }

    override suspend fun getMedicinesTechByTypes(types: List<String>): List<Technology> = withContext(Dispatchers.IO) {
        return@withContext mutableSetOf<Technology>().apply {
            types.forEach { _ ->
                medicineRepository.findAll().filter { med -> med.type.name in types }
                    .forEach { med -> med.technology?.let { tech -> this.add(tech) } }
            }
        }.toList()
    }

    override suspend fun getMedicinesTechByMeds(meds: List<String>): List<Technology> = withContext(Dispatchers.IO) {
        return@withContext mutableSetOf<Technology>().apply {
            meds.forEach {
                medicineRepository.findByName(it).technology?.let { tech -> this.add(tech) }
            }
        }.toList()
    }

    override suspend fun getTechnologyResourceByMed(medicineName: String): List<ResourceTechnology> = withContext(Dispatchers.IO) {
        return@withContext medicineRepository.findByName(medicineName).technology?.resources ?: listOf()
    }
}