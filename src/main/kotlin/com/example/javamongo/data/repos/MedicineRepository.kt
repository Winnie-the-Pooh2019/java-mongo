package com.example.javamongo.data.repos

import com.example.javamongo.data.entity.Medicine

interface MedicineRepository : Repository<Medicine> {
    fun findByName(name: String): Medicine
}