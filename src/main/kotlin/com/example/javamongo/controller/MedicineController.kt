package com.example.javamongo.controller

import com.example.javamongo.controller.dto.MedicineDto
import com.example.javamongo.controller.dto.ResourceTechDto
import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.emuns.IntervalEnum
import com.example.javamongo.data.entity.ersaz.ResourceTechnology
import com.example.javamongo.data.entity.ersaz.Technology
import com.example.javamongo.services.interfaces.*
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("medicine")
class MedicineController(
    @Autowired
    medicineService: MedicineService,
    @Autowired
    private val typeService: TypeService,
    @Autowired
    private val resourceService: ResourceService,
    @Autowired
    private val medicineShippingService: MedicineShippingService,
    @Autowired
    private val orderService: OrderService
) : CommonController<Medicine, MedicineDto>(medicineService, Medicine::class.java) {
    override fun delete(id: String?, model: Model): String {
        runBlocking {
            val medicines = if (id != null) listOf(service.findById(id)) else service.findAll()

            medicines.forEach {
                medicineShippingService.deleteAllByMedicine(it)
                orderService.deleteAllByMedicine(it)
            }
        }
        return super.delete(id, model)
    }

    override fun create(model: Model): String {
        runBlocking {
            val resources = resourceService.findAll().map { it.toUi() }
            val types = typeService.findAll().map { it.toUi() }

            model.addAttribute("medicine", MedicineDto())
            model.addAttribute("resources", resources)
            model.addAttribute("types", types)
            model.addAttribute("intervals", IntervalEnum.values().toList())
        }

        return super.create(model)
    }

    override fun getSingle(id: String, model: Model): String {
        runBlocking {
            val medicine = service.findById(id).toUi()
            val resources = resourceService.findAll().map { it.toUi() }
            val types = typeService.findAll().map { it.toUi() }

            model.addAttribute("medicine", medicine)
            model.addAttribute("resources", resources)
            model.addAttribute("types", types)
            model.addAttribute("intervals", IntervalEnum.values().toList())
        }
        return "medicine/medicine"
    }

    override suspend fun MedicineDto.toEntity(): Medicine = Medicine(
        id = if (this.id.isBlank()) ObjectId() else ObjectId(this.id),
        name = this.name,
        criticalAmount = this.criticalAmount.toInt(),
        expiration = this.expiration.toMap(),
        type = typeService.findById(typeId),
        price = this.price.toDouble(),
        technology = if (!this.description.isNullOrBlank() && !this.prepareTime.isNullOrBlank() && !this.resources.isNullOrBlank())
            Technology(
                this.description,
                this.prepareTime.toMap(),
                this.resources.let {
                    Gson().fromJson(it, Array<ResourceTechDto>::class.java)
                }.map {
                    ResourceTechnology(resourceService.findById(it.resource.id), it.count)
                }
            ) else null
    )
}