package com.example.javamongo.controller

import com.example.javamongo.controller.dto.ResourceShippingDto
import com.example.javamongo.data.entity.ResourceShipping
import com.example.javamongo.data.entity.emuns.ShippingStatus
import com.example.javamongo.services.interfaces.ResourceService
import com.example.javamongo.services.interfaces.ResourceShippingService
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Controller
@RequestMapping("resourceshipping")
class ResourceShippingController(
    @Autowired
    resourceShippingService: ResourceShippingService,
    @Autowired
    private val resourceService: ResourceService
) : CommonController<ResourceShipping, ResourceShippingDto>(resourceShippingService, ResourceShipping::class.java) {
    override fun getSingle(id: String, model: Model): String {
        runBlocking {
            model.addAttribute("statuses", ShippingStatus.values())
            model.addAttribute("resources", resourceService.findAll().map { it.toUi() })
        }

        return super.getSingle(id, model)
    }

    override fun create(model: Model): String {
        runBlocking {
            model.addAttribute("resourceshipping", ResourceShippingDto())
            model.addAttribute("statuses", ShippingStatus.values())
            model.addAttribute("resources", resourceService.findAll().map { it.toUi() })
        }

        return super.create(model)
    }

    override suspend fun ResourceShippingDto.toEntity(): ResourceShipping = ResourceShipping(
        id = if (id.isBlank()) ObjectId() else ObjectId(id),
        resource = resourceService.findById(id),
        amount = amount.toInt(),
        price = price.toDouble(),
        dateOrdered = LocalDate.parse(dateOrdered, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        dateShipped = if (dateShipped != null) LocalDate.parse(
            dateShipped,
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        ) else null,
        status = ShippingStatus.valueOf(status)
    )
}