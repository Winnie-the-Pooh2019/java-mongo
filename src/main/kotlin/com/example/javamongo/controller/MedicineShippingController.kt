package com.example.javamongo.controller

import com.example.javamongo.controller.dto.toMedicineShippingDto
import com.example.javamongo.services.interfaces.MedicineShippingService
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller("medicine-shippings")
class MedicineShippingController(
    @Autowired
    private val medicineShippingService: MedicineShippingService
) {
    @GetMapping("medicine-shippings")
    fun medicineShipping(model: Model): String {
        val medicineShipping = runBlocking {
            medicineShippingService.findAll().toMedicineShippingDto()
        }
        model.addAttribute("shipping", medicineShipping)

        return "shipping/m_shipping"
    }

    @GetMapping("medicine-shippings/byId")
    fun getMedicineShipping(@RequestParam(name = "id") id: String, model: Model): String {
        val shipping = runBlocking {
            medicineShippingService.findById(id)
        }
        model.addAttribute("shipping", shipping)

        return "shipping/m_ship"
    }
}