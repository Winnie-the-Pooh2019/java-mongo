package com.example.javamongo.controller

import com.example.javamongo.data.entity.MedicineShipping
import com.example.javamongo.services.interfaces.MedicineShippingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("medicine-shipping")
class MedicineShippingController(
    @Autowired
    private val medicineShippingService: MedicineShippingService
) : CommonController<MedicineShipping>(medicineShippingService, MedicineShipping::class.java)