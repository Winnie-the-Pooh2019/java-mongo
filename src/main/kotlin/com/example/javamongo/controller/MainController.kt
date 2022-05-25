package com.example.javamongo.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {
    @GetMapping
    fun getMain(model: Model): String {
        val map = mutableMapOf(
            "Clients" to "clients",
            "Medicine Shipping" to "medicine_shipping",
            "Medicines" to "medicines",
            "Orders" to "orders",
            "Resource Shipping" to "resource_shipping",
            "Resources" to "resources",
            "Types" to "types"
        )

        model.addAttribute("pages", map)

        return "main"
    }
}