package com.example.javamongo.controller

import com.example.javamongo.controller.dto.TypeDto
import com.example.javamongo.data.entity.Type
import com.example.javamongo.services.interfaces.MedicineService
import com.example.javamongo.services.interfaces.MedicineShippingService
import com.example.javamongo.services.interfaces.OrderService
import com.example.javamongo.services.interfaces.TypeService
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("type")
class TypeController(
    @Autowired
    typeService: TypeService,
    @Autowired
    private val medicineService: MedicineService,
    @Autowired
    private val medicineShippingService: MedicineShippingService,
    @Autowired
    private val orderService: OrderService
) : CommonController<Type, TypeDto>(typeService, Type::class.java) {
    override fun delete(id: String?, model: Model): String {
        runBlocking {
            val types = if (id != null) listOf(service.findById(id)) else service.findAll()

            types.forEach {
                medicineService.deleteAllByType(it).forEach { med ->
                    medicineShippingService.deleteAllByMedicine(med)
                    orderService.deleteAllByMedicine(med)
                }
            }
        }

        return super.delete(id, model)
    }

    override fun create(model: Model): String {
        model.addAttribute("type", TypeDto())
        return super.create(model)
    }

    override suspend fun TypeDto.toEntity(): Type = Type(id = if (id.isBlank()) ObjectId() else ObjectId(id), name)
}