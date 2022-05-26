package com.example.javamongo.controller

import com.example.javamongo.data.entity.Client
import com.example.javamongo.services.interfaces.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("client")
class ClientController(
    @Autowired
    private val clientService: ClientService
) : CommonController<Client>(clientService, Client::class.java)