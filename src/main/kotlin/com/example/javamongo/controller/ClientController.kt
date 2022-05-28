package com.example.javamongo.controller

import com.example.javamongo.controller.dto.ClientDto
import com.example.javamongo.data.entity.Client
import com.example.javamongo.services.interfaces.ClientService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("client")
class ClientController(
    @Autowired
    private val clientService: ClientService
) : CommonController<Client, ClientDto>(clientService, Client::class.java) {
    override fun create(model: Model): String {
        model.addAttribute("client", ClientDto())
        return super.create(model)
    }

    override suspend fun ClientDto.toEntity(): Client = Client(
        id = ObjectId(id),
        lastName = lastName,
        firstName = firstName,
        patronymic = if (patronymic.isNullOrBlank()) null else patronymic,
        phone = phone,
        address = address
    )
}