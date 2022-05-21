package com.example.javamongo.data.entity.emuns

enum class OrderMedicineStatus {
    DONE,
    PREPARING,
    NO_COMPONENTS;

    override fun toString(): String = this.name
}