package com.example.javamongo.data.entity.emuns

enum class OrderMedicineStatus {
    OK,
    IN_PROGRESS,
    NO_COMPONENTS;

    override fun toString(): String = this.name
}