package com.example.javamongo

import com.example.javamongo.data.migration.Migration
import com.example.javamongo.data.migration.Strategy
import com.example.javamongo.data.repos.ClientRepository
import com.example.javamongo.data.repos.MedicineRepository
import com.example.javamongo.data.repos.TypeRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.env.Environment
import org.springframework.core.io.Resource
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class JavaMongoApplication(
    @Autowired
    val clientRepository: ClientRepository,
    @Autowired
    val typeRepository: TypeRepository,
    @Autowired
    val medicineRepository: MedicineRepository,
    @Autowired
    val migration: Migration,
    @Autowired
    val environment: Environment
) : CommandLineRunner {
    @Value("classpath:data/data.json")
    lateinit var resource: Resource

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
    }
}

fun main(args: Array<String>) {
    runApplication<JavaMongoApplication>(*args)
}
