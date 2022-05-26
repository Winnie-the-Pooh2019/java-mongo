package com.example.javamongo.services.implementations

import com.example.javamongo.data.repos.TypeRepository
import com.example.javamongo.services.interfaces.TypeService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class TypeServiceImpl(
    @Autowired
    private val typeRepository: TypeRepository
) : TypeService(typeRepository)