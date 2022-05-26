package com.example.javamongo.controller

import com.example.javamongo.controller.dto.MedicineDto
import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.services.interfaces.MedicineService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("medicine")
class MedicineController(
    @Autowired
    private val medicineService: MedicineService
) : CommonController<Medicine, MedicineDto>(medicineService, Medicine::class.java)