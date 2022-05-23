package com.example.javamongo.services

import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.emuns.IntervalEnum
import com.example.javamongo.data.repos.MedicineShippingRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import java.time.LocalDate

class MedicineShippingServiceImpl(
    @Autowired
    private val medicineShippingRepository: MedicineShippingRepository
) : MedicineShippingService {
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
                shipped < LocalDate.now()
            } ?: false
        }.map { it.medicine }.toSet().toList()
    }
}