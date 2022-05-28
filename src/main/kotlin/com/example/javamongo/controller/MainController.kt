package com.example.javamongo.controller

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller
class MainController {
    @GetMapping
    fun getMain(model: Model): String {
        val map = mutableMapOf(
            "Clients" to "client",
            "Medicine Shipping" to "medicineshipping",
            "Medicines" to "medicine",
            "Orders" to "order",
            "Resource Shipping" to "resourceshipping",
            "Resources" to "resource",
            "Types" to "type"
        )

        model.addAttribute("map", map)

        return "main"
    }
}