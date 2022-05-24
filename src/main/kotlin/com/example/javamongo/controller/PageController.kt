package com.example.javamongo.controller

import com.example.javamongo.data.repos.ClientRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping

@Controller("/")
class PageController(
    @Autowired
    private val clientRepository: ClientRepository
) {
    @GetMapping("/")
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

    @GetMapping("/clients")
    fun clients(model: Model): String {
        val clients = clientRepository.findAll()

        return ""
    }
}