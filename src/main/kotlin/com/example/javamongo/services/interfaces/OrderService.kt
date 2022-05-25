package com.example.javamongo.services.interfaces

import com.example.javamongo.data.entity.Client
import com.example.javamongo.data.entity.Medicine
import com.example.javamongo.data.entity.Order
import com.example.javamongo.data.entity.ersaz.Technology
import java.time.LocalDate

interface OrderService {
    suspend fun findById(id: String): Order

    suspend fun findAll(): List<Order>

    //    1
    suspend fun getNotPickedClients(): List<Client>

    //    2
    suspend fun getAwaitingClients(): List<Client>

    suspend fun getAwaitingClientsByMedicineType(typeName: String): List<Client>

    //    3
    suspend fun getMostPopularMedicines(): Map<Medicine, Int>

    suspend fun getMostPopularMedicinesByType(typeName: String): Map<Medicine, Int>

    //    4
    suspend fun getClientsInIntervalByMedicine(
        startDate: LocalDate,
        endDate: LocalDate,
        medicineName: String
    ): List<Client>

    suspend fun getClientsInIntervalByTypes(
        startDate: LocalDate,
        endDate: LocalDate,
        typeNames: List<String>
    ): List<Client>

//    6
    suspend fun getOrdersInProgress(): List<Order>

//    7
    suspend fun getMedicinesInProgress(): List<Medicine>

    suspend fun getMedicinesTechInProgress(): List<Technology>

//    9
//    suspend fun getTechnologyResourceByMed(medicineName: String): List<ResourceTechnology>

//    10
    suspend fun getFavouriteClientsByMeds(medicines: List<String>): List<Client>

    suspend fun getFavouriteClientsByTypes(types: List<String>): List<Client>
}
