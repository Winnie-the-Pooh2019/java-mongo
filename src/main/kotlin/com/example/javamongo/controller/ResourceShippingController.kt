package com.example.javamongo.controller

import com.example.javamongo.controller.dto.ResourceShippingDto
import com.example.javamongo.data.entity.ResourceShipping
import com.example.javamongo.services.interfaces.ResourceShippingService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("resource-shipping")
class ResourceShippingController(
    @Autowired
    private val resourceShippingService: ResourceShippingService
) : CommonController<ResourceShipping, ResourceShippingDto>(resourceShippingService, ResourceShipping::class.java)