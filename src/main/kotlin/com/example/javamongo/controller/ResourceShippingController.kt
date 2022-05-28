package com.example.javamongo.controller

import com.example.javamongo.controller.dto.ResourceShippingDto
import com.example.javamongo.data.entity.ResourceShipping
import com.example.javamongo.data.entity.emuns.ShippingStatus
import com.example.javamongo.services.interfaces.ResourceService
import com.example.javamongo.services.interfaces.ResourceShippingService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
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
    override suspend fun ResourceShippingDto.toEntity(): ResourceShipping = ResourceShipping(
        id = ObjectId(id),
        resource = resourceService.findById(resource.id),
        amount = amount,
        price = price,
        dateOrdered = LocalDate.parse(dateOrdered, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        dateShipped = if (dateShipped != null) LocalDate.parse(
            dateShipped,
            DateTimeFormatter.ofPattern("yyyy-MM-dd")
        ) else null,
        status = ShippingStatus.valueOf(status)
    )
}