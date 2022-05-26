package com.example.javamongo.services.interfaces

import com.example.javamongo.data.entity.Client
import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.Order
import com.example.javamongo.data.entity.ersaz.Technology
import com.example.javamongo.data.repos.OrderRepository
import java.time.LocalDate

abstract class OrderService(repository: OrderRepository) : MongoService<Order>(repository) {
    //    1
    abstract suspend fun getNotPickedClients(): List<Client>

    //    2
    abstract suspend fun getAwaitingClients(): List<Client>

    abstract suspend fun getAwaitingClientsByMedicineType(typeName: String): List<Client>

    //    3
    abstract suspend fun getMostPopularMedicines(): Map<Medicine, Int>

    abstract suspend fun getMostPopularMedicinesByType(typeName: String): Map<Medicine, Int>

    //    4
    abstract suspend fun getClientsInIntervalByMedicine(
        startDate: LocalDate,
        endDate: LocalDate,
        medicineName: String
    ): List<Client>

    abstract suspend fun getClientsInIntervalByTypes(
        startDate: LocalDate,
        endDate: LocalDate,
        typeNames: List<String>
    ): List<Client>

//    6
    abstract suspend fun getOrdersInProgress(): List<Order>

//    7
    abstract suspend fun getMedicinesInProgress(): List<Medicine>

    abstract suspend fun getMedicinesTechInProgress(): List<Technology>

//    10
    abstract suspend fun getFavouriteClientsByMeds(medicines: List<String>): List<Client>

    abstract suspend fun getFavouriteClientsByTypes(types: List<String>): List<Client>
}
