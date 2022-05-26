package com.example.javamongo.controller

import com.example.javamongo.data.entity.Order
import com.example.javamongo.services.interfaces.OrderService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("order")
class OrderController(
    @Autowired
    private val orderService: OrderService
) : CommonController<Order>(orderService, Order::class.java)