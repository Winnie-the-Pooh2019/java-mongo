package com.example.javamongo.controller

import com.example.javamongo.controller.dto.toClientDtoList
import com.example.javamongo.services.interfaces.ClientService
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller("clients")
class ClientController(
    @Autowired
    private val clientService: ClientService
) {
    @GetMapping("clients")
    fun getAllClients(model: Model): String {
        val clients = runBlocking {
            clientService.findAll().toClientDtoList()
        }
        model.addAttribute("clients", clients)

        return "client/clients"
    }

    @GetMapping("clients/byId")
    fun getSingleClient(@RequestParam(name = "id", required = true) id: String, model: Model): String {
        val client = runBlocking {
            clientService.findById(id)
        }
        model.addAttribute("client", client)

        return "client/client"
    }
}