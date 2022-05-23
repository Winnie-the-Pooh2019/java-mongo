package com.example.javamongo

import com.example.javamongo.data.migration.Migration
import com.example.javamongo.data.migration.Strategy
import com.example.javamongo.services.OrderService
import kotlinx.coroutines.runBlocking
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.Environment
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories
import java.time.LocalDate

@SpringBootApplication
@EnableMongoRepositories
class JavaMongoApplication(
    @Autowired
    val migration: Migration,
    @Autowired
    val environment: Environment,
    @Autowired
    val orderService: OrderService
) : CommandLineRunner {
    override fun run(vararg args: String?) {
        when (Strategy.valueOf(environment.getProperty("migration.strategy")!!)) {
            Strategy.NONE -> println("No migration ... skipping")
            Strategy.SOFT -> {
                println("Soft migration ...")
                migration.insertData()
                println("Data inserted ...")
            }
            Strategy.FLUSH -> {
                println("Deleting all data ...")
                migration.flush()
                migration.insertData()
                println("Data inserted ...")
            }
        }

        runBlocking {
            println("Processing first query ...")
            orderService.getNotPickedClients().forEach(::println)
            println("Processing 2.1 query ...")
            orderService.getAwaitingClients().forEach(::println)
            println("Processing 2.2 query ...")
            orderService.getAwaitingClientsByMedicineType("krem").forEach(::println)
            println("Processing 3.1 query ...")
            orderService.getMostPopularMedicines().forEach(::println)
            println("Processing 3.2 query ...")
            orderService.getMostPopularMedicinesByType("krem").forEach(::println)
            println("Processing 4.1 query ...")
            orderService.getClientsInIntervalByMedicine(
                LocalDate.parse("2002-01-01"),
                LocalDate.parse("2021-01-01"),
                "Spasmolgon"
            ).forEach(::println)
            println("Processing 4.2 query ...")
            orderService.getClientsInIntervalByTypes(
                LocalDate.parse("2002-01-01"),
                LocalDate.parse("2021-01-01"),
                listOf("maz", "krem")
            ).forEach(::println)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<JavaMongoApplication>(*args)
}
