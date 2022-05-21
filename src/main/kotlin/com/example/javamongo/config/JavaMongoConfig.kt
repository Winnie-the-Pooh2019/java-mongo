package com.example.javamongo.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.Resource

@Configuration
class JavaMongoConfig {
    @Value("classpath:data/data.json")
    lateinit var resource: Resource
}