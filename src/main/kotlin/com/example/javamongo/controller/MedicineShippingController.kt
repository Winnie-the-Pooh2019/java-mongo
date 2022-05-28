package com.example.javamongo.controller

import com.example.javamongo.controller.dto.MedicineShippingDto
import com.example.javamongo.data.entity.MedicineShipping
import com.example.javamongo.data.entity.emuns.ShippingStatus
import com.example.javamongo.services.interfaces.MedicineService
import com.example.javamongo.services.interfaces.MedicineShippingService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
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
    override suspend fun MedicineShippingDto.toEntity(): MedicineShipping = MedicineShipping(
        id = ObjectId(id),
        medicine = medicineService.findById(medicine.id),
        price = price,
        amount = amount,
        dateOrdered = LocalDate.parse(dateOrdered, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        dateShipped = if (dateShipped != null) LocalDate.parse(
            dateShipped,
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        ) else null,
        status = ShippingStatus.valueOf(status)
    )
}