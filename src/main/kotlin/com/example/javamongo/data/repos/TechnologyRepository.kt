package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.ersaz.Technology
import org.springframework.data.mongodb.repository.MongoRepository

interface TechnologyRepository : MongoRepository<Technology, String> {
}