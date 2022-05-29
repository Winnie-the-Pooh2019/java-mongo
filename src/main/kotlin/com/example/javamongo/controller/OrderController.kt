package com.example.javamongo.controller

import com.example.javamongo.controller.dto.OrderDto
import com.example.javamongo.controller.dto.OrderMedicineDto
import com.example.javamongo.data.entity.Order
import com.example.javamongo.data.entity.emuns.OrderMedicineStatus
import com.example.javamongo.data.entity.emuns.OrderStatus
import com.example.javamongo.data.entity.ersaz.OrderMedicine
import com.example.javamongo.services.interfaces.ClientService
import com.example.javamongo.services.interfaces.MedicineService
import com.example.javamongo.services.interfaces.OrderService
import com.google.gson.Gson
import kotlinx.coroutines.runBlocking
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.RequestMapping
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Controller
@RequestMapping("order")
class OrderController(
    @Autowired
    orderService: OrderService,
    @Autowired
    private val clientService: ClientService,
    @Autowired
    private val medicineService: MedicineService
) : CommonController<Order, OrderDto>(orderService, Order::class.java) {
    override fun insert(ui: OrderDto): String {
        println(ui)
        return "redirect:/order"
    }

    override fun getSingle(id: String, model: Model): String {
        runBlocking {
            model.addAttribute("statuses", OrderStatus.values())
            model.addAttribute("stats", OrderMedicineStatus.values())
            model.addAttribute("meds", medicineService.findAll().map { it.toUi() })
            model.addAttribute("clients", clientService.findAll().map { it.toUi() })
        }

        return super.getSingle(id, model)
    }

    override fun create(model: Model): String {
        runBlocking {
            model.addAttribute("order")
            model.addAttribute("meds", medicineService.findAll().map { it.toUi() })
            model.addAttribute("statuses", OrderStatus.values())
            model.addAttribute("clients", clientService.findAll().map { it.toUi() })
        }

        return super.create(model)
    }

    override suspend fun OrderDto.toEntity(): Order = Order(
        id = if (id.isBlank()) ObjectId() else ObjectId(id),
        client = clientService.findById(clientId),
        datePicked = LocalDate.parse(datePicking, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        medicines = Gson().fromJson(medicines, Array<OrderMedicineDto>::class.java).let {
            it.map { orMedDto ->
                OrderMedicine(
                    medicine = medicineService.findById(orMedDto.medicineId),
                    amount = orMedDto.amount,
                    price = orMedDto.price,
                    status = OrderMedicineStatus.valueOf(orMedDto.status)
                )
            }
        },
        status = OrderStatus.valueOf(status)
    )
}