package com.example.javamongo.controller

import com.example.javamongo.controller.dto.ClientDto
import com.example.javamongo.data.entity.Client
import com.example.javamongo.services.interfaces.ClientService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import kotlin.reflect.full.memberProperties

@Controller
@RequestMapping("client")
class ClientController(
    @Autowired
    private val clientService: ClientService
) : CommonController<Client, ClientDto>(clientService, Client::class.java) {
    override fun insert(ui: ClientDto): String {
        val newUi = if (ui.patronymic != null && ui.patronymic.isEmpty())
            ClientDto(
                ui.id,
                ui.lastName,
                ui.firstName,
                null,
                ui.phone,
                ui.address
            )
        else ui

        return super.insert(newUi)
    }

    override fun update(ui: ClientDto): String {
        val newUi = if (ui.patronymic != null && ui.patronymic.isEmpty())
            ClientDto(
                ui.id,
                ui.lastName,
                ui.firstName,
                null,
                ui.phone,
                ui.address
            )
        else ui

        return super.update(newUi)
    }

    override suspend fun ClientDto.toEntity(): Client = Client(
        id = ObjectId(id),
        lastName = lastName,
        firstName = firstName,
        patronymic = patronymic,
        phone = phone,
        address = address
    )
}