package com.example.javamongo.controller

import com.example.javamongo.controller.dto.TypeDto
import com.example.javamongo.data.entity.Type
import com.example.javamongo.services.interfaces.TypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("type")
class TypeController(
    @Autowired
    private val typeService: TypeService
) : CommonController<Type, TypeDto>(typeService, Type::class.java)