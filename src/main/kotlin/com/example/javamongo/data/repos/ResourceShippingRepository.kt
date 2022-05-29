package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.Resource
import com.example.javamongo.data.entity.ResourceShipping

interface ResourceShippingRepository : Repository<ResourceShipping> {
    fun deleteAllByResource(resource: Resource)
}