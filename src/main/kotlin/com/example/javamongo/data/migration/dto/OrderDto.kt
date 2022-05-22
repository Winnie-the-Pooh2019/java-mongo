package com.example.javamongo.data.migration.dto

import com.example.javamongo.data.entity.Client
import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.Order
import com.example.javamongo.data.entity.emuns.OrderMedicineStatus
import com.example.javamongo.data.entity.emuns.OrderStatus
import com.example.javamongo.data.entity.ersaz.OrderMedicine
import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.time.format.DateTimeFormatter

data class OrderDto(
    val status: String,
    @SerializedName(value = "date_picked")
    val datePicked: String,
    @SerializedName(value = "client_id")
    val clientId: Int,
    val medicines: List<OrderMedicineDto>
)

data class OrderMedicineDto(
    @SerializedName(value = "medicine_id")
    val medicineId: Int,
    val amount: Int,
    val price: Double,
    val status: String
)

fun List<OrderDto>.toOrderList(medicines: List<Medicine>, clients: List<Client>): List<Order> = this.map { orderDto ->
    Order(
        client = clients[orderDto.clientId - 1],
        datePicked = LocalDate.parse(orderDto.datePicked, DateTimeFormatter.ofPattern("yyyy-MM-dd")),
        medicines = orderDto.medicines.map { orMedDto ->
            OrderMedicine(
                medicine = medicines[orMedDto.medicineId - 1],
                amount = orMedDto.amount,
                price = orMedDto.price,
                status = OrderMedicineStatus.valueOf(orMedDto.status)
            )
        },
        status = OrderStatus.valueOf(orderDto.status)
    )
}