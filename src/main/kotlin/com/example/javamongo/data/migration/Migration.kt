package com.example.javamongo.data.migration

import com.example.javamongo.data.entity.Person
import com.example.javamongo.data.repos.PersonRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Component
class Migration(
    @Autowired
    private val personRepository: PersonRepository
) {
    @Value("classpath:data/data.json")
    lateinit var resource: Resource

    fun isEmpty(): Boolean = personRepository.count() == 0L

    fun insertData(): Boolean = try {
        val data = resource.inputStream.bufferedReader().readText()
        personRepository.saveAll(ObjectMapper().readValue(data, Array<Person>::class.java).toList())
        true
    } catch (e: Exception) {
        e.printStackTrace()
        false
    }

    fun flush() {
        personRepository.deleteAll()
    }
}