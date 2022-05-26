package com.example.javamongo.services.interfaces

import com.example.javamongo.data.entity.Resource
import com.example.javamongo.data.repos.ResourceRepository

abstract class ResourceService(repository: ResourceRepository) : MongoService<Resource>(repository)