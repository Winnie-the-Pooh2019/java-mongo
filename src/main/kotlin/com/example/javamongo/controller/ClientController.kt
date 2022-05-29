package com.example.javamongo.controller

import com.example.javamongo.controller.dto.ClientDto
import com.example.javamongo.data.entity.Client
import com.example.javamongo.services.interfaces.ClientService
import com.example.javamongo.services.interfaces.OrderService
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("client")
class ClientController(
    @Autowired
    clientService: ClientService,
    @Autowired
    private val orderService: OrderService
) : CommonController<Client, ClientDto>(clientService, Client::class.java) {
    override fun delete(id: String?, model: Model): String {
        runBlocking {
            val clients = if (id != null) listOf(service.findById(id)) else service.findAll()

            clients.forEach { orderService.deleteAllByClient(it) }
        }

        return super.delete(id, model)
    }

    override fun create(model: Model): String {
        model.addAttribute("client", ClientDto())
        return super.create(model)
    }

    override suspend fun ClientDto.toEntity(): Client = Client(
        id = if (id.isBlank()) ObjectId() else ObjectId(id),
        lastName = lastName,
        firstName = firstName,
        patronymic = if (patronymic.isNullOrBlank()) null else patronymic,
        phone = phone,
        address = address
    )
}