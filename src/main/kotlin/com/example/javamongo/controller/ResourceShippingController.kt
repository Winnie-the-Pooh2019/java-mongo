package com.example.javamongo.controller

import com.example.javamongo.controller.dto.toResourceShippingDto
import com.example.javamongo.services.interfaces.ResourceShippingService
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller("resource-shipping")
class ResourceShippingController(
    @Autowired
    private val resourceShippingService: ResourceShippingService
) {
    @GetMapping("resource-shipping")
    fun resourceShipping(model: Model): String {
        val resourceShipping = runBlocking {
            resourceShippingService.findAll().toResourceShippingDto()
        }
        model.addAttribute("shipping", resourceShipping)

        return "shipping/r_shipping"
    }

    @GetMapping("resource-shipping/byId")
    fun getShipping(@RequestParam(name = "id") id: String, model: Model): String {
        val shipping = runBlocking {
            resourceShippingService.findById(id)
        }
        model.addAttribute("shipping", shipping)

        return "shipping/r_ship"
    }
}