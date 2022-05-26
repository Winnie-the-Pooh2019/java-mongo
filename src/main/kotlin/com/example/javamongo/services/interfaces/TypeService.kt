package com.example.javamongo.services.interfaces

import com.example.javamongo.data.entity.Type
import com.example.javamongo.data.repos.TypeRepository

abstract class TypeService(repository: TypeRepository): MongoService<Type>(repository)