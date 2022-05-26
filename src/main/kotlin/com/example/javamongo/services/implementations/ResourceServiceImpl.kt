package com.example.javamongo.services.implementations

import com.example.javamongo.data.repos.ResourceRepository
import com.example.javamongo.services.interfaces.ResourceService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ResourceServiceImpl(
    @Autowired
    private val resourceRepository: ResourceRepository
) : ResourceService(resourceRepository)