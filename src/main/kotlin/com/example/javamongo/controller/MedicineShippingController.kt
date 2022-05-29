package com.example.javamongo.controller

import com.example.javamongo.controller.dto.MedicineShippingDto
import com.example.javamongo.data.entity.MedicineShipping
import com.example.javamongo.data.entity.emuns.ShippingStatus
import com.example.javamongo.services.interfaces.MedicineService
import com.example.javamongo.services.interfaces.MedicineShippingService
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Controller
@RequestMapping("medicineshipping")
class MedicineShippingController(
    @Autowired
    medicineShippingService: MedicineShippingService,
    @Autowired
    private val medicineService: MedicineService
) : CommonController<MedicineShipping, MedicineShippingDto>(medicineShippingService, MedicineShipping::class.java) {
    override fun getSingle(id: String, model: Model): String {
        runBlocking {
            model.addAttribute("statuses", ShippingStatus.values())
            model.addAttribute("medicines", medicineService.findAll().map { it.toUi() })
        }

        return super.getSingle(id, model)
    }

    override fun create(model: Model): String {
        runBlocking {
            model.addAttribute("medicineshipping", MedicineShippingDto())
            model.addAttribute("statuses", ShippingStatus.values())
            model.addAttribute("medicines", medicineService.findAll().map { it.toUi() })
        }

        return super.create(model)
    }

    override suspend fun MedicineShippingDto.toEntity(): MedicineShipping = MedicineShipping(
        id = if (id.isBlank()) ObjectId() else ObjectId(id),
        medicine = medicineService.findById(medicineId),
        price = price.toDouble(),
        amount = amount.toInt(),
        dateOrdered = LocalDate.parse(dateOrdered, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        dateShipped = if (!dateShipped.isNullOrBlank()) LocalDate.parse(
            dateShipped,
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        ) else null,
        status = ShippingStatus.valueOf(status)
    )
}