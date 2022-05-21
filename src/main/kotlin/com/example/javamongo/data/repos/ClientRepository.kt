package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.Client
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface ClientRepository : MongoRepository<Client, String> {
    @Query(value = "{lastName: '?0'}")
    fun findClientByFirstName(name: String)

    @Query(value = "{firstName : ?0}", fields = "{lastName :  1, phone : 1, id : 0}")
    fun findAllByFirstName(): List<Client>
}