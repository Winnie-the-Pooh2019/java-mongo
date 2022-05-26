package com.example.javamongo.services.implementations

import com.example.javamongo.data.repos.ClientRepository
import com.example.javamongo.services.interfaces.ClientService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ClientServiceImpl(
    @Autowired
    clientRepository: ClientRepository
) : ClientService(clientRepository)