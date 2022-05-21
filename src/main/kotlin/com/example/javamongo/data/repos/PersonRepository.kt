package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.Person
import org.springframework.data.mongodb.repository.MongoRepository

interface PersonRepository : MongoRepository<Person, String> {
}