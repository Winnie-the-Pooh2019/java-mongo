package com.example.javamongo.data.migration

import com.example.javamongo.data.migration.dto.*
import com.example.javamongo.data.repos.*
import com.google.gson.Gson
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component
import java.io.File

@Component
class Migration(
    @Autowired
    val clientRepository: ClientRepository,
    @Autowired
    val medicineRepository: MedicineRepository,
    @Autowired
    val medicineShippingRepository: MedicineShippingRepository,
    @Autowired
    val orderRepository: OrderRepository,
    @Autowired
    val resourceRepository: ResourceRepository,
    @Autowired
    val resourceShippingRepository: ResourceShippingRepository,
    @Autowired
    val typeRepository: TypeRepository
) {
    @Value("classpath:data/")
    lateinit var resource: Resource

    fun insertData(): Boolean = try {
        val gson = Gson()

        val types = gson.fromJson(getData("types"), Array<TypeDto>::class.java).toList().toTypeList()
        val resources = gson.fromJson(getData("resources"), Array<ResourceDto>::class.java).toList().toResourcesList()
        val resourceShipping =
            gson.fromJson(getData("resource_shipping"), Array<ResourceShippingDto>::class.java).toList()
                .toResourceShippingList(resources)
        val medicines = gson.fromJson(getData("medicines"), Array<MedicineDto>::class.java).toList()
            .toMedicineList(types, resources)
        val medicineShipping =
            gson.fromJson(getData("medicine_shipping"), Array<MedicineShippingDto>::class.java).toList()
                .toMedicineShippingList(medicines)
        val clients = gson.fromJson(getData("clients"), Array<ClientDto>::class.java).toList().toClientList()
        val orders =
            gson.fromJson(getData("orders"), Array<OrderDto>::class.java).toList().toOrderList(medicines, clients)

        orders.forEach {
            it.medicines.forEach { ti -> println(ti.medicine.name) }
        }

        typeRepository.saveAll(types)
        resourceRepository.saveAll(resources)
        resourceShippingRepository.saveAll(resourceShipping)
        medicineRepository.saveAll(medicines)
        medicineShippingRepository.saveAll(medicineShipping)
        clientRepository.saveAll(clients)
        orderRepository.saveAll(orders)

        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }

    fun getData(filePath: String) =
        File("${resource.file.absolutePath}/$filePath.json").inputStream().bufferedReader().readText()

    fun flush() {
        clientRepository.deleteAll()
        medicineRepository.deleteAll()
        medicineShippingRepository.deleteAll()
        orderRepository.deleteAll()
        resourceRepository.deleteAll()
        resourceShippingRepository.deleteAll()
        typeRepository.deleteAll()
    }
}