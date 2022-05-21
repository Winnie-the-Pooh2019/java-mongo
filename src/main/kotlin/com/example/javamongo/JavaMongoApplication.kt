package com.example.javamongo

import com.example.javamongo.data.entity.Client
import com.example.javamongo.data.entity.ClientRepository
import org.bson.types.ObjectId
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@EnableMongoRepositories
class JavaMongoApplication(
    @Autowired
    val clientRepository: ClientRepository
) : CommandLineRunner {
    val listClients = listOf(
        Client(
            ObjectId(),
            "Duvanov",
            "Ivan",
            "Olegovich",
            "8(905)948-96-92",
            "Lenina avenue",
            hashMapOf(
                "cat_name" to "Shupistick",
                "gaming_pc" to "Pirozhochek"
            )
        ),
        Client(
            ObjectId(),
            "Popovich",
            "Alesha",
            "Sergeevich",
            "8(905)948-96-92",
            "Octabrskiy avenue",
            hashMapOf(
                "dog_name" to "Kakyosya"
            )
        ),
        Client(
            ObjectId(),
            "Romanov",
            "Ivan",
            "Aleksandrovich",
            "8(905)948-96-92",
            "Pertograd",
            hashMapOf(
                "gaming_pc" to "Russian Empire"
            )
        ),
        Client(
            ObjectId(),
            "Shmadko",
            "Alesha",
            "Ivanovich",
            "8(905)948-96-92",
            "Arshloch avenue",
            hashMapOf(
                "salo" to "many"
            )
        ),
        Client(
            ObjectId(),
            "Riviyski",
            "Geralt",
            "Ivanovich",
            "8(905)948-96-92",
            "Rivia avenue",
            hashMapOf(
                "favourite_witch" to "Triss"
            )
        )
    )

    override fun run(vararg args: String?) {
        println("Saving clients into base")
        clientRepository.saveAll(listClients)
        println("Clients are saved")

        println("Echo clients")
        println(clientRepository.findAll())
    }
}

fun main(args: Array<String>) {
    runApplication<JavaMongoApplication>(*args)
}
