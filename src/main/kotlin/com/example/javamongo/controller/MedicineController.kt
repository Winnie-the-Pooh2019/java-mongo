package com.example.javamongo.controller

import com.example.javamongo.controller.dto.toMedicineDtoList
import com.example.javamongo.services.interfaces.MedicineService
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller("medicines")
class MedicineController(
    @Autowired
    private val medicineService: MedicineService
) {
    @GetMapping("medicines")
    fun medicines(model: Model): String {
        val medicines = runBlocking {
            medicineService.findAll().toMedicineDtoList()
        }
        model.addAttribute("medicines", medicines)

        return "medicine/medicines"
    }

    @GetMapping("medicines/byId")
    fun getMedicine(@RequestParam(name = "id") id: String, model: Model): String {
        val medicine = runBlocking {
            medicineService.findById(id)
        }
        model.addAttribute("medicine", medicine)

        return "medicine/medicine"
    }
}