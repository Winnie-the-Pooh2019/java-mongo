package com.example.javamongo.services

import com.example.javamongo.data.entity.ersaz.ResourceTechnology
import com.example.javamongo.data.entity.ersaz.Technology
import com.example.javamongo.data.repos.MedicineRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class MedicineServiceImpl(
    @Autowired
    private val medicineRepository: MedicineRepository
) : MedicineService {
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