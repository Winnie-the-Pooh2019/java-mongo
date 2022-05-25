package com.example.javamongo.controller

import com.example.javamongo.controller.dto.toTypeDtoList
import com.example.javamongo.services.interfaces.TypeService
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller("types")
class TypeController<T>(
    @Autowired
    private val typeService: TypeService
) {
    var t: T? = null
    @GetMapping("types")
    fun types(model: Model): String {
        val types = runBlocking {
            typeService.findAll().toTypeDtoList()
        }
        model.addAttribute("types", types)

        return "type/types"
    }

    @GetMapping("types/byId")
    fun getOrder(@RequestParam("id") id: String, model: Model): String {
        t!!::class.java.name
        val type = runBlocking {
            typeService.findById(id)
        }
        model.addAttribute("type", type)

        return "type/type"
    }
}