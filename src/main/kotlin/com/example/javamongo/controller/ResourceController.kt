package com.example.javamongo.controller

import com.example.javamongo.controller.dto.toResourceDtoList
import com.example.javamongo.services.interfaces.ResourceService
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller("resources")
class ResourceController<T>(
    @Autowired
    private val resourceService: ResourceService
) {
    @GetMapping("resources")
    fun resources(model: Model): String {
        val resources = runBlocking {
            resourceService.findAll().toResourceDtoList()
        }
        model.addAttribute("resources", resources)

        return "resource/resources"
    }

    @GetMapping("resources/byId")
    fun getOrder(@RequestParam("id") id: String, model: Model): String {
        val resource = runBlocking {
            resourceService.findById(id)
        }
        model.addAttribute("resource", resource)

        return "resource/resource"
    }
}