package com.example.javamongo.services

import com.example.javamongo.data.entity.Client
import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.Order
import com.example.javamongo.data.entity.emuns.OrderStatus
import com.example.javamongo.data.entity.ersaz.Technology
import com.example.javamongo.data.repos.OrderRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service
class OrderServiceImpl(
    @Autowired
    val orderRepository: OrderRepository
) : OrderService {
    //    1
    override suspend fun getNotPickedClients(): List<Client> = withContext(Dispatchers.IO) {
        orderRepository.findAllByDatePickedBeforeAndStatus(
            date = LocalDate.now(),
            status = OrderStatus.DONE
        ).map { it.client }
    }

    //    2
    override suspend fun getAwaitingClients(): List<Client> = withContext(Dispatchers.IO) {
        val orders = orderRepository.findAllByStatus(OrderStatus.IN_PROGRESS)

        return@withContext orders.filter { it.status == OrderStatus.IN_PROGRESS }
            .map { it.client }.toSet().toList()
    }

    //    2
    override suspend fun getAwaitingClientsByMedicineType(typeName: String): List<Client> =
        withContext(Dispatchers.IO) {
            val orders = orderRepository.findAllByStatus(OrderStatus.IN_PROGRESS)

            return@withContext orders.asSequence().filter { it.status == OrderStatus.IN_PROGRESS }
                .filter { it.medicines.any { med -> med.medicine.type.name == typeName } }
                .map { it.client }.toSet().toList()
        }

    //    3
    override suspend fun getMostPopularMedicines(): Map<Medicine, Int> = withContext(Dispatchers.IO) {
        val orders = orderRepository.findAll()

        return@withContext orders.asSequence().flatMap { it.medicines }.groupingBy { it.medicine }
            .eachCount().entries.sortedBy { it.value }.reversed().associate { it.key to it.value }
    }

    override suspend fun getMostPopularMedicinesByType(typeName: String): Map<Medicine, Int> =
        getMostPopularMedicines().filter { it.key.type.name == typeName }

    //    4.1
    override suspend fun getClientsInIntervalByMedicine(
        startDate: LocalDate,
        endDate: LocalDate,
        medicineName: String
    ): List<Client> = withContext(Dispatchers.IO) {
        return@withContext orderRepository.findAllByDatePickedBetween(startDate, endDate).asSequence()
            .filter { it.medicines.any { med -> med.medicine.name == medicineName } }.map { it.client }.toSet().toList()
    }

    //    4.2
    override suspend fun getClientsInIntervalByTypes(
        startDate: LocalDate,
        endDate: LocalDate,
        typeNames: List<String>
    ): List<Client> = withContext(Dispatchers.IO) {
        return@withContext orderRepository.findAllByDatePickedBetween(startDate, endDate).asSequence()
            .filter { it.medicines.any { med -> med.medicine.type.name in typeNames } }.map { it.client }.toSet()
            .toList()
    }

    override suspend fun getOrdersInProgress(): List<Order> = withContext(Dispatchers.IO) {
        return@withContext orderRepository.findAllByStatus(OrderStatus.IN_PROGRESS)
    }

    override suspend fun getMedicinesInProgress(): List<Medicine> = withContext(Dispatchers.IO) {
        return@withContext orderRepository.findAllByStatus(OrderStatus.IN_PROGRESS).asSequence()
            .flatMap { it.medicines }
            .map { it.medicine }.toSet().toList()
    }

    override suspend fun getMedicinesTechInProgress(): List<Technology> = withContext(Dispatchers.IO) {
        return@withContext orderRepository.findAllByStatus(OrderStatus.IN_PROGRESS).asSequence()
            .flatMap { it.medicines }.mapNotNull { it.medicine.technology }.toSet().toList()
    }

    override suspend fun getFavouriteClientsByMeds(medicines: List<String>): List<Client> =
        withContext(Dispatchers.IO) {
            return@withContext orderRepository.findAll()
                .filter { it.medicines.any { med -> med.medicine.name in medicines } }.groupingBy { it.client }
                .eachCount().entries.sortedBy { it.value }.reversed()
                .associate { it.key to it.value }.entries.map { it.key }.toSet().toList()
        }

    override suspend fun getFavouriteClientsByTypes(types: List<String>): List<Client> = withContext(Dispatchers.IO) {
        return@withContext orderRepository.findAll()
            .filter { it.medicines.any { med -> med.medicine.type.name in types } }.groupingBy { it.client }
            .eachCount().entries.sortedBy { it.value }.reversed()
            .associate { it.key to it.value }.entries.map { it.key }.toSet().toList()
    }
}