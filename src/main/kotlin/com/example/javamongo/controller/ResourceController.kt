package com.example.javamongo.controller

import com.example.javamongo.data.entity.Resource
import com.example.javamongo.services.interfaces.ResourceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("resource")
class ResourceController(
    @Autowired
    private val resourceService: ResourceService
) : CommonController<Resource>(resourceService, Resource::class.java)