package com.example.javamongo.controller

import com.example.javamongo.controller.dto.MedicineDto
import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.emuns.IntervalEnum
import com.example.javamongo.data.entity.ersaz.ResourceTechnology
import com.example.javamongo.data.entity.ersaz.Technology
import com.example.javamongo.services.interfaces.MedicineService
import com.example.javamongo.services.interfaces.ResourceService
import com.example.javamongo.services.interfaces.TypeService
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping

@Controller
@RequestMapping("medicine")
class MedicineController(
    @Autowired
    medicineService: MedicineService,
    @Autowired
    private val typeService: TypeService,
    @Autowired
    private val resourceService: ResourceService
) : CommonController<Medicine, MedicineDto>(medicineService, Medicine::class.java) {
    override suspend fun MedicineDto.toEntity(): Medicine = Medicine(
        id = ObjectId(this.id),
        name = this.name,
        criticalAmount = this.criticalAmount,
        expiration = this.expiration.map { (key, value) -> IntervalEnum.valueOf(key) to value }.toMap(),
        type = typeService.findById(this.id),
        price = this.price,
        technology = if (this.technology != null)
            Technology(
                this.technology.description,
                this.technology.prepareTime.map { (key, value) -> IntervalEnum.valueOf(key.uppercase()) to value }
                    .toMap(),
                this.technology.resources.map {
                    ResourceTechnology(resourceService.findById(it.resource.id), it.count)
                }
            ) else null
    )
}