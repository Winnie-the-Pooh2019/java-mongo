package com.example.javamongo.services.interfaces

import com.example.javamongo.data.entity.ResourceShipping
import com.example.javamongo.data.repos.ResourceShippingRepository

abstract class ResourceShippingService(repository: ResourceShippingRepository) :
    MongoService<ResourceShipping>(repository)