package com.example.javamongo.services.implementations

import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.MedicineShipping
import com.example.javamongo.data.entity.emuns.IntervalEnum
import com.example.javamongo.data.repos.MedicineShippingRepository
import com.example.javamongo.services.interfaces.MedicineShippingService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class MedicineShippingServiceImpl(
    @Autowired
    private val medicineShippingRepository: MedicineShippingRepository
) : MedicineShippingService {
    override suspend fun findAll(): List<MedicineShipping> = withContext(Dispatchers.IO) {
        return@withContext medicineShippingRepository.findAll()
    }

    override suspend fun findById(id: String): MedicineShipping = withContext(Dispatchers.IO) {
        return@withContext medicineShippingRepository.findById(ObjectId(id)).get()
    }

    override suspend fun getSoldOutMedicines(): List<Medicine> = withContext(Dispatchers.IO) {
        return@withContext medicineShippingRepository.findAll().filter {
            it.dateShipped?.let { shipped ->
                it.medicine.expiration.onEach { (intervalEnum, i) ->
                    when (intervalEnum) {
                        IntervalEnum.YEARS -> shipped.plusYears(i.toLong())
                        IntervalEnum.MONTHS -> shipped.plusMonths(i.toLong())
                        IntervalEnum.DAYS -> shipped.plusDays(i.toLong())
                        else -> println()
                    }
                }
                println("5 QUERY DATE = $shipped")
                shipped < LocalDate.now()
            } ?: false
        }.map { it.medicine }.toSet().toList()
    }
}