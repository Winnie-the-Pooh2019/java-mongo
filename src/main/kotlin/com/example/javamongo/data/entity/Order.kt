package com.example.javamongo.data.entity

import com.example.javamongo.controller.dto.CliDto
import com.example.javamongo.controller.dto.OrderDto
import com.example.javamongo.controller.dto.OrderMedicineDto
import com.example.javamongo.controller.dto.UiDto
import com.example.javamongo.data.entity.emuns.OrderStatus
import com.example.javamongo.data.entity.ersaz.OrderMedicine
import com.google.gson.annotations.SerializedName
import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.DBRef
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDate

@Document(collection = "orders")
data class Order(
    @Id
    override val id: ObjectId = ObjectId(),
    @DBRef
    val client: Client,
    @Field(name = "date_picked")
    @SerializedName(value = "date_picked")
    val datePicked: LocalDate,
    val medicines: List<OrderMedicine>,
    val status: OrderStatus
) : Entity {
    override fun toUi(): UiDto = OrderDto(
        id = id.toString(),
        clientSurname = CliDto(client.id.toString(), client.lastName),
        datePicking = datePicked.toString(),
        status = status.name,
        medicines = medicines.map {
            OrderMedicineDto(
                medicineName = it.medicine.name,
                amount = it.amount,
                price = it.price,
                status = it.status.name,
                medicineId = it.medicine.id.toString()
            )
        }
    )
}
