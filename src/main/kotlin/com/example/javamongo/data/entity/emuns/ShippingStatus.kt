package com.example.javamongo.data.entity.emuns

enum class ShippingStatus {
    IN_TRANSIT,
    DELIVERED;

    override fun toString(): String = this.name
}