package com.example.javamongo.controller

import com.example.javamongo.controller.dto.toOrderDtoList
import com.example.javamongo.services.interfaces.OrderService
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam

@Controller("orders")
class OrderController(
    @Autowired
    private val orderService: OrderService
) {
    @GetMapping("orders")
    fun orders(model: Model): String {
        val orders = runBlocking {
            orderService.findAll().toOrderDtoList()
        }
        model.addAttribute("orders", orders)

        return "order/orders"
    }

    @GetMapping("orders/byId")
    fun getOrder(@RequestParam("id") id: String, model: Model): String {
        val order = runBlocking {
            orderService.findById(id)
        }
        model.addAttribute("order", order)

        return "order/order"
    }
}