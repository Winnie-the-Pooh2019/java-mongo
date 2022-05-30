package com.example.javamongo.controller

import com.example.javamongo.controller.dto.ResourceDto
import com.example.javamongo.data.entity.Resource
import com.example.javamongo.data.entity.emuns.IntervalEnum
import com.example.javamongo.services.interfaces.*
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("resource")
class ResourceController(
    @Autowired
    resourceService: ResourceService,
    @Autowired
    private val resourceShippingService: ResourceShippingService,
    @Autowired
    private val medicineService: MedicineService,
    @Autowired
    private val orderService: OrderService,
    @Autowired
    private val medicineShippingService: MedicineShippingService
) : CommonController<Resource, ResourceDto>(resourceService, Resource::class.java) {
    override fun delete(id: String?, model: Model): String {
        runBlocking {
            val resources = if (id != null) listOf(service.findById(id)) else service.findAll()

            resources.forEach {
                resourceShippingService.deleteAllByResource(it)
                medicineService.deleteAllByResource(it).forEach {med ->
                    println(med)
                    orderService.deleteAllByMedicine(med)
                    medicineShippingService.deleteAllByMedicine(med)
                }
            }
        }

        return super.delete(id, model)
    }

    override fun getSingle(id: String, model: Model): String {
        model.addAttribute("intervals", IntervalEnum.values())
        return super.getSingle(id, model)
    }

    override fun create(model: Model): String {
        model.addAttribute("resource", ResourceDto())
        model.addAttribute("intervals", IntervalEnum.values())
        return super.create(model)
    }

    override suspend fun ResourceDto.toEntity(): Resource = Resource(
        id = if (id.isBlank()) ObjectId() else ObjectId(id),
        name = name,
        criticalAmount = criticalAmount.toInt(),
        expiration = expiration.toMap(),
        price = price.toDouble()
    )
}